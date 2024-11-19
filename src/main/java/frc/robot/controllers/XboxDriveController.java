package frc.robot.controllers;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import java.util.function.DoubleSupplier;

public class XboxDriveController implements DriverControllerInterface {
    private GenericHID controller;

    public XboxDriveController(int id){
        controller = new GenericHID(id);
    }

    @Override
    public Trigger getShootingTrigger() {
        return new Trigger(()->controller.getRawButton(6));
    }
    @Override
    public Trigger getPivotTrigger(){
        return new Trigger(()->controller.getRawButton(1));
    }
    @Override
    public Trigger getIntakingTrigger(){
        return new Trigger(()->controller.getRawButton(5));
    }
    @Override
    public DoubleSupplier getXSpeed(){
        return (()->controller.getRawAxis(1));
    }
    @Override
    public DoubleSupplier getYSpeed(){
        return (() ->controller.getRawAxis(2));
    }
    @Override
    public DoubleSupplier getAngularSpeed(){
        return (() -> controller.getRawAxis(0));
    }

    @Override
    public Trigger getResetGyroButton(){
        return new Trigger(() -> controller.getRawButton(3));
    }
}
