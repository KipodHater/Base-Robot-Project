package frc.lib.subsystems.flywheelMotorIO;

import static frc.lib.math.Conversions.RPSToMPS;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.VelocityDutyCycle;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.motorcontrol.Talon;

public class FlywheelTalonSRXIO implements FlywheelMotorIO {

    private final TalonSRX flywheelTalonSRXMotor;
    private boolean isOpenLoopGlobal;
    private final double maxSpeed;
    private final double flywheelWheelDiameter;
    private SimpleMotorFeedforward feedForwardController;
    private VelocityDutyCycle velocityControl;
    private final DutyCycleOut driveDutyCycle;

    public FlywheelTalonSRXIO(int motorPort, double wheelDiameter, String motorNameLocator,
            /* for example "shooter/upperFlywheelMotor */
            double kMaxSpeedMetersPerSecond) {

        flywheelTalonSRXMotor = new TalonSRX(motorPort);
        flywheelTalonSRXMotor.configAllSettings(new TalonSRXConfiguration());
        flywheelWheelDiameter = wheelDiameter;
        isOpenLoopGlobal = true;
        maxSpeed = kMaxSpeedMetersPerSecond;
        driveDutyCycle = new DutyCycleOut(0);
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

    public void setVelocityMPS(double velocitySetpoint) {
        if (!isOpenLoopGlobal) {
            double wantedRPM = RPSToMPS(velocitySetpoint / 60.0, flywheelWheelDiameter * Math.PI);
            velocityControl = new VelocityDutyCycle(wantedRPM);
            flywheelTalonSRXMotor.set(TalonSRXControlMode.Velocity, wantedRPM);;
        } else {
            driveDutyCycle.Output = velocitySetpoint / maxSpeed;
            flywheelTalonSRXMotor.set(ControlMode.Velocity, driveDutyCycle.Output);
        }
    }


    @Override
    public double getVelocityMPS() {
        return RPSToMPS( flywheelTalonSRXMotor.getSelectedSensorVelocity()/4096 / 60.0, flywheelWheelDiameter * Math.PI);
    }

    @Override
    public void setMotorLoopMode(boolean isOpenLoop) {
        isOpenLoopGlobal = isOpenLoop;
    }

    @Override
    public void stopMotor() {
        flywheelTalonSRXMotor.set(ControlMode.Velocity, 0);
    }

    @Override
    public void periodic() {
        
    }
}
