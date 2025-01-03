// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.lib.subsystems.positionMotorIO;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

/** Add your docs here. */
public class PositionSparkMaxIO implements PositionMotorIO {

    private final CANSparkMax sparkMaxMotor;
    private final SparkPIDController motorPIDController;
    private SimpleMotorFeedforward feedForwardController;
    private boolean isOpenLoopGlobal;
    private final double maxAngularVelocity;
    private double allowedError;


    public PositionSparkMaxIO(int motorPort, String motorNameLocator, double kMaxAngularVelocityRadsPerSecond) {
        sparkMaxMotor = new CANSparkMax(motorPort, MotorType.kBrushless);
        sparkMaxMotor.restoreFactoryDefaults();
        motorPIDController = sparkMaxMotor.getPIDController();
        maxAngularVelocity = kMaxAngularVelocityRadsPerSecond;
        isOpenLoopGlobal = true;
        zeroMotor();
    }

    @Override
    public void invertMotor(boolean isInverted) {
    
        sparkMaxMotor.setInverted(isInverted);
    }

    @Override
    public void setPIDCoefficients(double kP,double kI,double kD, double kIz,
    double kFF, double kMaxOutput, double kMinOutput, double maxRPM, double maxVel,
    double minVel, double maxAcc,double allowedErr) {
      
        motorPIDController.setP(kP);
        motorPIDController.setI(kI);
        motorPIDController.setD(kD);
        motorPIDController.setIZone(kIz);
        motorPIDController.setFF(kFF);
        motorPIDController.setOutputRange(kMinOutput, kMaxOutput);
        motorPIDController.setSmartMotionMaxVelocity(maxAngularVelocity, 0);
        motorPIDController.setSmartMotionMinOutputVelocity(minVel, 0);
        motorPIDController.setSmartMotionMaxAccel(maxAcc, 0);
        motorPIDController.setSmartMotionAllowedClosedLoopError(allowedError, 0);
    }

    @Override
    public void setFeedforwardCoefficients(double kS, double kV, double kA) {
      
        feedForwardController = new SimpleMotorFeedforward(kS, kV, kA);
    }

    @Override
    public void setNeutralMode(boolean neutralModeCoast) {
       
       if (neutralModeCoast) {
            sparkMaxMotor.setIdleMode(IdleMode.kCoast);
        } else {
            sparkMaxMotor.setIdleMode(IdleMode.kBrake);
        }
    }

    @Override
    public void setPositionRadian(double radians) {
        // SparkAbsoluteEncoder encoder = sparkMaxMotor.getAbsoluteEncoder();
        // double currentRotation = encoder.getPosition()/Math.PI*2;
        double wantedRotation = radians/Math.PI*2;
        // double rotateAmount = wantedRotation-currentRotation;
    
        motorPIDController.setReference(wantedRotation, ControlType.kSmartMotion, 0);
    }
    

    @Override
    public double getPositionRadian() {
        return  sparkMaxMotor.getAbsoluteEncoder().getPosition()/Math.PI*2;
    }

    @Override
    public void setMotorLoopMode(boolean isOpenLoop) {
      
        isOpenLoopGlobal = isOpenLoop;
    }

    @Override
    public void zeroMotor() {
      
        motorPIDController.setReference(0, ControlType.kPosition);
    }

    @Override
    public void periodic() {
        
    }
}
