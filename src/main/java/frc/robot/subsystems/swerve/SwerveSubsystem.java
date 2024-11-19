// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.swerve;

import static frc.robot.Constants.swerveContants.FRONT_LEFT_ANGLE_MOTOR_ID;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveSubsystem extends SubsystemBase {
  /** Creates a new SwerveSubsystem. */

  private final SwerveModule frontLeft = new SwerveModule(
    FRONT_LEFT_DRIVE_MOTOR_ID,
    FRONT_LEFT_ANGLE_MOTOR_ID,
    FRONT_LEFT_DRIVE_ENCODER_REVERSED,
    FRONT_LEFT_TURNING_ENCODER_REVERSED,
    FRONT_LEFT_DRIVE_ABSOLUTE_ENODER_ID,
    FRONT_LEFT_DRIVE_ABSOLUTE_ENCODER_OFFSET,
    FRONT_LEFT_DRIVE_ABSOLUTE_ENCODER_REVERSED
  );
  public SwerveSubsystem() {}

  public void stopModules(){
    frontLeft.stop();

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
