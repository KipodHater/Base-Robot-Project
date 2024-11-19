// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.pivotConstants.*;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkAbsoluteEncoder.Type;

import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj.drive.RobotDriveBase.MotorType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class PivotSubsystem extends SubsystemBase {
  /** Creates a new PivotSubsystem. */
  private final CANSparkMax pivotMotor;
  private final ProfiledPIDController pivotPIDController;
  private final ElevatorFeedforward pivotFeedforwardController;
  private final AbsoluteEncoder pivotAbsoluteEncoder;

  public PivotSubsystem() {
    pivotMotor = new CANSparkMax(PIVOT_MOTOR_ID, com.revrobotics.CANSparkLowLevel.MotorType.kBrushless);
    pivotMotor.restoreFactoryDefaults();

    pivotPIDController = new ProfiledPIDController(PIVOT_MOTOR_KP, PIVOT_MOTOR_KI, PIVOT_MOTOR_KD, 
    new TrapezoidProfile.Constraints(PIVOT_MOTOR_MAX_SPEED, PIVOT_MOTOR_MAX_ACCELERATION), 0.02);

    pivotFeedforwardController = new ElevatorFeedforward(PIVOT_MOTOR_KS, PIVOT_MOTOR_KG, PIVOT_MOTOR_KV);

    pivotAbsoluteEncoder = pivotMotor.getAbsoluteEncoder(Type.kDutyCycle);
    pivotAbsoluteEncoder.setZeroOffset(PIVOT_MOTOR_ENCODER_OFFSET);
    pivotAbsoluteEncoder.setPositionConversionFactor(360);//changes the values from -1,1 to -360,360
    
    pivotMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, (float)PIVOT_MOTOR_MAX_ANGLE);
    pivotMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, (float)PIVOT_MOTOR_MIN_ANGLE);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  private void setMotorsControlLoop(double desiredPosition /*m/s*/){
    pivotMotor.set(
      pivotPIDController.calculate(
        pivotAbsoluteEncoder.getPosition(), desiredPosition)

      + pivotFeedforwardController.calculate(
        pivotAbsoluteEncoder.getPosition(), desiredPosition)
    );
  }

  public Command setPivotPosition(double desiredAngle){
    return new RunCommand(() -> setMotorsControlLoop(desiredAngle), this);
  }
}
