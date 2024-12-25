package frc.lib.subsystems.flywheelMotorIO;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

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

    public void setVelocityMPS(double velocitySetpoint)
}
