package frc.lib.subsystems.flywheelMotorIO;

import static frc.robot.Constants.shooterConstants.HIGH_SHOOTER_MOTOR_ID;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;
import static frc.lib.math.Conversions.*;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;


public class FlywheelSparkMaxIO implements FlywheelMotorIO {
    private final CANSparkMax sparkMaxMotor;
    private final SparkPIDController motorPIDController;
    private SimpleMotorFeedforward feedForwardController;
    private final double flywheelWheelDiameter;
    public FlywheelSparkMaxIO(int motorPort, double wheelDiameter){
        flywheelWheelDiameter = wheelDiameter;
        sparkMaxMotor = new CANSparkMax(HIGH_SHOOTER_MOTOR_ID, CANSparkLowLevel.MotorType.kBrushless);
        motorPIDController = sparkMaxMotor.getPIDController();
    }

    @Override
    public void invertMotor(boolean isInverted){
        sparkMaxMotor.setInverted(isInverted);
    }

    @Override
    public void setPIDCoefficients(double kp, double ki, double kd, double kIzone, double kFeedForwardConstant, double kMinOutput, double kMaxOutput){
        motorPIDController.setP(kp);
        motorPIDController.setI(ki);
        motorPIDController.setP(kd);
        motorPIDController.setIZone(kIzone);
        motorPIDController.setFF(kFeedForwardConstant);
        motorPIDController.setOutputRange(kMinOutput, kMaxOutput);
    }

    @Override
    public void setFeedforwardCoefficients(double kS, double kV, double kA){
        feedForwardController = new SimpleMotorFeedforward(kS, kV, kA);
    }

    @Override
    public void setNeutralMode(motorNeutralMode neutralMode){
        if(neutralMode == motorNeutralMode.Coast){
            sparkMaxMotor.setIdleMode(IdleMode.kCoast);
        } else{
            sparkMaxMotor.setIdleMode(IdleMode.kBrake);
        }
    }

    @Override
    public void updateInputs(MotorIOInputs inputs){

    }

    @Override
    public void setVelocityMPS(double velocitySetpoint){
        double wantedRPM = RPSToMPS(velocitySetpoint/60.0, flywheelWheelDiameter * Math.PI);
        double velocityRadPerSec = wantedRPM * (2 * Math.PI) / 60.0;
        double feedForwardVoltage = feedForwardController.calculate(velocityRadPerSec); // feedforward gets speed in rad/s
        motorPIDController.setReference(wantedRPM, ControlType.kVelocity, 0, feedForwardVoltage);
    }

    @Override
    public double getVelocityMPS(){
        return RPSToMPS(sparkMaxMotor.getEncoder().getVelocity()/60, flywheelWheelDiameter * Math.PI);
    }

    @Override
    public void setMotorClosedLoop(motorMode desiredMotorMode){
        return;
    }

    @Override
    public void stopMotor(){
        sparkMaxMotor.set(0);
    }
}
