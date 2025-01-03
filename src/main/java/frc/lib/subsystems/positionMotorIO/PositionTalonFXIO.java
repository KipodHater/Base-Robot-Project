package frc.lib.subsystems.positionMotorIO;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class PositionTalonFXIO implements PositionMotorIO {
    private final TalonFX talonFXMotor;
    private final TalonFXConfiguration motorConfigs;
    private boolean isOpenLoopGlobal;
    private final double maxAngularVelocity;
    final MotionMagicVoltage motorRequest;

    public PositionTalonFXIO(int motorPort, String motorNameLocator, double kMaxAngularVelocityRadsPerSecond) {
        talonFXMotor = new TalonFX(motorPort);
        maxAngularVelocity = kMaxAngularVelocityRadsPerSecond;
        motorConfigs = new TalonFXConfiguration();
        var motionMagicConfigs = motorConfigs.MotionMagic;
        motionMagicConfigs.MotionMagicCruiseVelocity = 80; // Target cruise velocity of 80 rps
        motionMagicConfigs.MotionMagicAcceleration = 160; // Target acceleration of 160 rps/s (0.5 seconds)
        motionMagicConfigs.MotionMagicJerk = 1600; // Target jerk of 1600 rps/s/s (0.1 seconds)
        talonFXMotor.getConfigurator().apply(motorConfigs);
        motorRequest = new MotionMagicVoltage(0);
        zeroMotor();
        
    }

    @Override
    public void invertMotor(boolean isInverted) {

        talonFXMotor.setInverted(isInverted);
    }

    @Override
    public void setPIDCoefficients(double kP, double kI, double kD, double kIz, double kFF, double kMaxOutput,
            double kMinOutput, double maxRPM, double maxVel, double minVel, double maxAcc, double allowedErr) {
        var Slot0Configs  = motorConfigs.Slot0;
        Slot0Configs.kP = kP;
        Slot0Configs.kI = kI;
        Slot0Configs.kD = kD;
        Slot0Configs.kI = kIz;
        talonFXMotor.getConfigurator().apply(motorConfigs);
        
    }

    @Override
    public void setFeedforwardCoefficients(double kS, double kV, double kA) {

        var Slot0Configs  = motorConfigs.Slot0;
        Slot0Configs.kS = kS;
        Slot0Configs.kV = kV;
        Slot0Configs.kA = kA;
        talonFXMotor.getConfigurator().apply(motorConfigs);
    }

    @Override
    public void setNeutralMode(boolean neutralModeCoast) {

        talonFXMotor.setNeutralMode(
            neutralModeCoast ? NeutralModeValue.Coast :
            NeutralModeValue.Brake);
    }
    

    @Override
    public void setPositionRadian(double radians) {

        double wantedRotation = radians/Math.PI*2;

        talonFXMotor.setControl(motorRequest.withPosition(wantedRotation));
        
    }

    @Override
    public double getPositionRadian() {

        return talonFXMotor.getPosition().getValueAsDouble()/Math.PI*2;
    }

    @Override
    public void setMotorLoopMode(boolean isOpenLoop) {

        isOpenLoopGlobal = isOpenLoop;
    }

    @Override
    public void zeroMotor() {

        talonFXMotor.setControl(motorRequest.withPosition(0));
    }

    @Override
    public void periodic() {

        
    }
}
