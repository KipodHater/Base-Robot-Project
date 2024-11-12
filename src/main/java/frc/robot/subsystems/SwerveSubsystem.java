// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.swerveContants.*;
public class SwerveSubsystem extends SubsystemBase {
  /** Creates a new SwerveSubsystem. */
  private final TalonFX frontRightDriveMotor;
  private final TalonFX frontRightAngleMotor;

  private final TalonFX frontLeftDriveMotor;
  private final TalonFX frontLeftAngleMotor;

  private final TalonFX backRightDriveMotor;
  private final TalonFX backRightAngleMotor;

  private final TalonFX backLeftDriveMotor;
  private final TalonFX backLeftAngleMotor;

  private final Translation2d m_frontRightLocation;
  private final Translation2d m_frontLeftLocation;

  private final Translation2d m_backRightLocation;
  private final Translation2d m_backLeftLocation;

  private final SwerveDriveKinematics m_kinematics;

  private ChassisSpeeds speed;

  public SwerveSubsystem() {
    frontRightDriveMotor = new TalonFX(FRONT_RIGHT_DRIVE_MOTOR_ID);
    frontRightAngleMotor = new TalonFX(FRONT_RIGHT_ANGLE_MOTOR_ID);

    frontLeftDriveMotor = new TalonFX(FRONT_LEFT_DRIVE_MOTOR_ID);
    frontLeftAngleMotor = new TalonFX(FRONT_LEFT_ANGLE_MOTOR_ID);

    backRightDriveMotor = new TalonFX(BACK_RIGHT_DRIVE_MOTOR_ID);
    backRightAngleMotor = new TalonFX(BACK_RIGHT_ANGLE_MOTOR_ID);

    backLeftDriveMotor = new TalonFX(BACK_LEFT_DRIVE_MOTOR_ID);
    backLeftAngleMotor = new TalonFX(BACK_LEFT_ANGLE_MOTOR_ID);

    m_frontRightLocation = new Translation2d(DISTANCE_FROM_CENTER, -DISTANCE_FROM_CENTER);
    m_frontLeftLocation = new Translation2d(DISTANCE_FROM_CENTER, DISTANCE_FROM_CENTER);
    m_backRightLocation = new Translation2d(-DISTANCE_FROM_CENTER, -DISTANCE_FROM_CENTER);
    m_backLeftLocation = new Translation2d(-DISTANCE_FROM_CENTER, DISTANCE_FROM_CENTER);

    m_kinematics = new SwerveDriveKinematics(
  m_frontLeftLocation, m_frontRightLocation, m_backLeftLocation, m_backRightLocation
  );
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
