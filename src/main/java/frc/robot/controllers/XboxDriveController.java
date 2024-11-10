package frc.robot.controllers;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class XboxDriveController implements DriverControllerInterface {
    private GenericHID controller;

    public XboxDriveController(int id){
        controller = new GenericHID(id);
    }

    @Override
    public Trigger getShootingTrigger() {
        return new Trigger(()->controller.getRawButton(6));
    }
}
