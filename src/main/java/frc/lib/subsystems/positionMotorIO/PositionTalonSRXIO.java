package frc.lib.subsystems.positionMotorIO;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

public class PositionTalonSRXIO implements PositionMotorIO {
    private final TalonSRX talonSRXMotor;
    private SimpleMotorFeedforward feedForwardController;
    private boolean isOpenLoopGlobal;
    private final double maxAngularVelocity;
    private final TalonSRXConfiguration motorConfigs;
    final MotionMagicVoltage motorRequest;

    public PositionTalonSRXIO(int motorPort, String motorNameLocator, double kMaxAngularVelocityRadsPerSecond) {
        talonSRXMotor = new TalonSRX(motorPort);
        talonSRXMotor.configFactoryDefault();
        maxAngularVelocity = kMaxAngularVelocityRadsPerSecond;
        motorRequest = new MotionMagicVoltage(0);
        motorConfigs = new TalonSRXConfiguration();
        motorConfigs.motionCruiseVelocity = 80;
        motorConfigs.motionAcceleration = 160;
        
        zeroMotor();
    }

    @Override
    public void invertMotor(boolean isInverted) {

        talonSRXMotor.setInverted(isInverted);
    }

    @Override
    public void setPIDCoefficients(double kP, double kI, double kD, double kIz, double kFF, double kMaxOutput,
            double kMinOutput, double maxRPM, double maxVel, double minVel, double maxAcc, double allowedErr) {
            
        var slot0Configs = motorConfigs.slot0;
        slot0Configs.kP = kP;
        slot0Configs.kI = kI;
        slot0Configs.kD = kD;
        slot0Configs.kF = kFF;
        slot0Configs.integralZone = kIz;

        talonSRXMotor.config_kP(0, kP);
        talonSRXMotor.config_kI(0, kI);
        talonSRXMotor.config_kD(0, kD);
        talonSRXMotor.config_kF(0, kFF);
        talonSRXMotor.config_IntegralZone(0, kIz);

        talonSRXMotor.configAllSettings(motorConfigs);
        
    }

    @Override
    public void setFeedforwardCoefficients(double kS, double kV, double kA) {
        feedForwardController = new SimpleMotorFeedforward(kS, kV, kA);
    }

    @Override
    public void setNeutralMode(boolean neutralModeCoast) {
        talonSRXMotor.setNeutralMode(
            neutralModeCoast ? NeutralMode.Coast :
            NeutralMode.Brake);
    }
    

    @Override
    public void setPositionRadian(double radians) {

        double wantedRotation = radians/Math.PI*2;
        
        talonSRXMotor.set(TalonSRXControlMode.MotionMagic, wantedRotation);
        
    }

    @Override
    public double getPositionRadian() {

        return (talonSRXMotor.getSelectedSensorPosition()/4096)/Math.PI*2;
    }

    @Override
    public void setMotorLoopMode(boolean isOpenLoop) {

        isOpenLoopGlobal = isOpenLoop;
    }

    @Override
    public void zeroMotor() {
     
        talonSRXMotor.set(TalonSRXControlMode.Position, 0);
    }

    @Override
    public void periodic() {
       
        
    }
}
