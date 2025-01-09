package frc.lib.subsystems.flywheelMotorIO;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.VelocityDutyCycle;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.MathUtil;

import static frc.lib.math.Conversions.*;

public class FlywheelTalonFXIO implements FlywheelMotorIO {
    private final TalonFX flywheelTalonFXMotor;
    private final double flywheelWheelDiameter;
    private VelocityDutyCycle velocityControl;
    private boolean isOpenLoopGlobal;
    private final double maxSpeed;
    private final DutyCycleOut driveDutyCycle;
    private final TalonFXConfiguration talonFXConfigs;

    public FlywheelTalonFXIO(int motorPort, double wheelDiameter, String motorNameLocator,
    /*for example "shooter/upperFlywheelMotor */
            double kMaxSpeedMetersPerSecond) {
        maxSpeed = kMaxSpeedMetersPerSecond;
        isOpenLoopGlobal = false;
        flywheelWheelDiameter = wheelDiameter;
        flywheelTalonFXMotor = new TalonFX(motorPort);
        talonFXConfigs = new TalonFXConfiguration();
        flywheelTalonFXMotor.getConfigurator().apply(talonFXConfigs);
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

        var slot0Configs = talonFXConfigs.Slot0;

        slot0Configs.kP = kp;
        slot0Configs.kI = ki;
        slot0Configs.kD = kd;

        flywheelTalonFXMotor.getConfigurator().apply(talonFXConfigs, 0.050);
    }

    @Override
    public void setFeedforwardCoefficients(double kS, double kV, double kA) {
        var slot0Configs = talonFXConfigs.Slot0;

        slot0Configs.kS = kS;
        slot0Configs.kV = kV;
        slot0Configs.kA = kA;
        slot0Configs.kG = 0;

        flywheelTalonFXMotor.getConfigurator().apply(talonFXConfigs, 0.050);
    }

    @Override
    public void setNeutralMode(boolean neutralModeCoast) {
        if (neutralModeCoast)
            flywheelTalonFXMotor.setNeutralMode(NeutralModeValue.Coast);
        else
            flywheelTalonFXMotor.setNeutralMode(NeutralModeValue.Brake);
    }

    @Override
    public void set(double percentIn) {

        flywheelTalonFXMotor.set(MathUtil.clamp(percentIn, -1, 1));
    }

    @Override
    public void set(InputType type, double input) {
        switch(type){
            case MPS:
                if (!isOpenLoopGlobal) {
                    double wantedRPM = RPSToMPS(input / 60.0, flywheelWheelDiameter * Math.PI);
                    velocityControl = new VelocityDutyCycle(wantedRPM);
                    flywheelTalonFXMotor.setControl(velocityControl);
                } else {
                    driveDutyCycle.Output = input / maxSpeed;
                    flywheelTalonFXMotor.setControl(driveDutyCycle);
            }
            case Percent:
                set(input);
            case Voltage:
                VoltageOut voltage = new VoltageOut(MathUtil.clamp(input, 0, 12)); 
                flywheelTalonFXMotor.setControl(voltage);
            
            
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
