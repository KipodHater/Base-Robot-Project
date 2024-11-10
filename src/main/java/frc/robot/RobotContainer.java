// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.controllers.XboxDriveController;

public class RobotContainer {
  private final XboxDriveController controller = new XboxDriveController(0);
  private final SuperStructure structure = new SuperStructure();
  public RobotContainer() {
    configureBindings();
  }


  public void periodic(){
      SmartDashboard.putNumber("Desired Angle", SmartDashboard.getNumber("Desired Angle", 30));
      SmartDashboard.putNumber("Desired Speed", SmartDashboard.getNumber("Desired Speed", 0));
  }
  private void configureBindings() {
    controller.getPivotTrigger()
      .onTrue(structure.setPivotAngle(SmartDashboard.getNumber("Desired Angle", 30)));
    controller.getShootingTrigger()
      .onTrue(structure.shootingSequenceCommand(SmartDashboard.getNumber("Desired Speed", 0)));
    controller.getIntakingTrigger().onTrue(structure.intakingSequenceCommand());
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
