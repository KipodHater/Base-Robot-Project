package frc.lib.subsystems.flywheelMotorIO;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.VelocityDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import static frc.lib.math.Conversions.*;

public class FlywheelTalonFXIO implements FlywheelMotorIO {
    private final TalonFX flywheelTalonFXMotor;
    private final double flywheelWheelDiameter;
    private VelocityDutyCycle velocityControl;
    private boolean isOpenLoopGlobal;
    private final double maxSpeed;
    private final DutyCycleOut driveDutyCycle;

    public FlywheelTalonFXIO(int motorPort, double wheelDiameter, String motorNameLocator,
    /*for example "shooter/upperFlywheelMotor */
            double kMaxSpeedMetersPerSecond) {
        maxSpeed = kMaxSpeedMetersPerSecond;
        isOpenLoopGlobal = false;
        flywheelWheelDiameter = wheelDiameter;
        flywheelTalonFXMotor = new TalonFX(motorPort);
        flywheelTalonFXMotor.getConfigurator().apply(new TalonFXConfiguration());
        // flywheelTalonFXMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        driveDutyCycle = new DutyCycleOut(0);
    }

    @Override
    public void invertMotor(boolean isInverted) {
        flywheelTalonFXMotor.setInverted(isInverted);
    }

    @Override
    public void setPIDCoefficients(double kp, double ki, double kd/*
                                                                   * , double kIzone, double kFeedForwardConstant,
                                                                   * double kMinOutput, double kMaxOutput
                                                                   */) {
        var talonFXConfig = new TalonFXConfiguration();
        var slot0Configs = talonFXConfig.Slot0;

        slot0Configs.kP = kp;
        slot0Configs.kI = ki;
        slot0Configs.kD = kd;

        flywheelTalonFXMotor.getConfigurator().apply(talonFXConfig, 0.050);
    }

    @Override
    public void setFeedforwardCoefficients(double kS, double kV, double kA) {
        var talonFXConfig = new TalonFXConfiguration();
        var slot0Configs = talonFXConfig.Slot0;

        slot0Configs.kS = kS;
        slot0Configs.kV = kV;
        slot0Configs.kA = kA;
        slot0Configs.kG = 0;
    }

    @Override
    public void setNeutralMode(boolean neutralModeCoast) {
        if (neutralModeCoast)
            flywheelTalonFXMotor.setNeutralMode(NeutralModeValue.Coast);
        else
            flywheelTalonFXMotor.setNeutralMode(NeutralModeValue.Brake);
    }

    @Override
    public void setVelocityMPS(double velocitySetpoint) {

        if (!isOpenLoopGlobal) {
            double wantedRPM = RPSToMPS(velocitySetpoint / 60.0, flywheelWheelDiameter * Math.PI);
            velocityControl = new VelocityDutyCycle(wantedRPM);
            flywheelTalonFXMotor.setControl(velocityControl);
        } else {
            driveDutyCycle.Output = velocitySetpoint / maxSpeed;
            flywheelTalonFXMotor.setControl(driveDutyCycle);
        }
    }

    @Override
    public double getVelocityMPS() {
        return RPSToMPS(flywheelTalonFXMotor.getVelocity().getValue() / 60.0, flywheelWheelDiameter * Math.PI);
    }

    @Override
    public void setMotorLoopMode(boolean isOpenLoop) {
        isOpenLoopGlobal = isOpenLoop;
    }

    @Override
    public void stopMotor(){
        flywheelTalonFXMotor.set(0);
    }

    @Override
    public void periodic(){}
}
