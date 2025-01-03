package frc.lib.subsystems.flywheelMotorIO;

import static frc.lib.math.Conversions.RPSToMPS;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkBase.ControlType;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;

public class FlywheelTalonSRXIO implements FlywheelMotorIO {

    private final TalonSRX flywheelTalonSRXMotor;
    private boolean isOpenLoopGlobal;
    private final double maxSpeed;
    private final double flywheelWheelDiameter;
    private SimpleMotorFeedforward feedForwardController;

    public FlywheelTalonSRXIO(int motorPort, double wheelDiameter, String motorNameLocator,
            /* for example "shooter/upperFlywheelMotor */
            double kMaxSpeedMetersPerSecond) {

        flywheelTalonSRXMotor = new TalonSRX(motorPort);
        flywheelTalonSRXMotor.configAllSettings(new TalonSRXConfiguration());
        flywheelWheelDiameter = wheelDiameter;
        isOpenLoopGlobal = true;
        maxSpeed = kMaxSpeedMetersPerSecond;
    }

    @Override
    public void invertMotor(boolean isInverted) {
        flywheelTalonSRXMotor.setInverted(isInverted);
    }

    @Override
    public void setPIDCoefficients(double kp, double ki, double kd) {
        flywheelTalonSRXMotor.config_kP(0, kp);
        flywheelTalonSRXMotor.config_kI(0, ki);
        flywheelTalonSRXMotor.config_kD(0, kd);
    }

    @Override
    public void setFeedforwardCoefficients(double kS, double kV, double kA) {
        feedForwardController = new SimpleMotorFeedforward(kS, kV, kA);
    }

    @Override
    public void setNeutralMode(boolean neutralModeCoast) {
        if (neutralModeCoast)
            flywheelTalonSRXMotor.setNeutralMode(NeutralMode.Coast);
        else
            flywheelTalonSRXMotor.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    public void setVelocityMPS(double velocitySetpoint) {
        if (!isOpenLoopGlobal) {
            double wantedRPM = RPSToMPS(velocitySetpoint / 60.0, flywheelWheelDiameter * Math.PI);
            double velocityRadPerSec = wantedRPM * (2 * Math.PI) / 60.0;
            double feedForwardVoltage = feedForwardController.calculate(velocityRadPerSec); // feedforward gets speed in
                                                                                            // rad/s
            flywheelTalonSRXMotor.set(TalonSRXControlMode.Velocity, feedForwardVoltage);
        } else {
            flywheelTalonSRXMotor.set(TalonSRXControlMode.PercentOutput, velocitySetpoint / maxSpeed);
        }
    }

    @Override
    public double getVelocityMPS(){
        return RPSToMPS(flywheelTalonSRXMotor.getSelectedSensorVelocity() / 60.0, flywheelWheelDiameter * Math.PI);
    }

    
    @Override
    public void setMotorLoopMode(boolean isOpenLoop) {
        isOpenLoopGlobal = isOpenLoop;
    }

    @Override
    public void stopMotor(){
        flywheelTalonSRXMotor.set(TalonSRXControlMode.PercentOutput, 0);
    }
}
