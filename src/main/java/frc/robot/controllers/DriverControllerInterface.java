package frc.robot.controllers;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.button.Trigger;

public interface DriverControllerInterface {
    public Trigger getShootingTrigger();   

    public Trigger getPivotTrigger();

    public Trigger getIntakingTrigger();

    public DoubleSupplier getXSpeed();

    public DoubleSupplier getYSpeed();

    public DoubleSupplier getAngularSpeed();

    public Trigger getResetGyroButton();
}
