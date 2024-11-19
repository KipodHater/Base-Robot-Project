// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import static frc.robot.Constants.shooterConstants.*;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkAnalogSensor;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.BangBangController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ShooterSubsystem. */
  private final CANSparkMax highShooterMotor;
  private final CANSparkMax lowShooterMotor;

  private final BangBangController highShootBangBangController;
  private final BangBangController lowShootBangBangController;

  private final SparkAnalogSensor highShooterAnalogSensor;
  private final SparkAnalogSensor lowShooterAnalogSensor;

  public ShooterSubsystem() {
    highShooterMotor = new CANSparkMax(HIGH_SHOOTER_MOTOR_ID, MotorType.kBrushless);
    lowShooterMotor = new CANSparkMax(LOW_SHOOTER_MOTOR_ID, MotorType.kBrushless);

    highShooterMotor.restoreFactoryDefaults();
    lowShooterMotor.restoreFactoryDefaults();

    highShooterMotor.setIdleMode(IdleMode.kCoast);
    lowShooterMotor.setIdleMode(IdleMode.kCoast);

    highShooterMotor.setInverted(false);
    lowShooterMotor.setInverted(false);

    highShooterAnalogSensor = highShooterMotor.getAnalog();
    lowShooterAnalogSensor = lowShooterMotor.getAnalog();

    highShootBangBangController = new BangBangController();
    lowShootBangBangController = new BangBangController();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public Command intakingCommand(){
    return runEnd(()->setBangBangSpeed(-0.25, -0.25), () -> stopShooterMotors());
  }

  public Command shootingCommand(double desiredHighVelocity, double desiredLowVelocity){ //velocities in m/s!!!!
    return new RunCommand(() -> setBangBangSpeed(desiredHighVelocity, desiredLowVelocity), this);
    };

  public Command shootingCommand(double desiredVelocity){
    return new RunCommand(
      () -> setBangBangSpeed(desiredVelocity, desiredVelocity),
      this);
  }
  
  public Command shootingCommand(){
    return new RunCommand(() -> stopShooterMotors(), this);
  }

    private double highShooterRPMToMetersPerSecond(double highShooterRPMVelocity){
    return HIGH_SHOOTER_ROLLER_PERIMETER * highShooterRPMVelocity / 60.0;
  }

  private double lowShooterRPMToMetersPerSecond(double lowShooterRPMVelocity){
    return LOW_SHOOTER_ROLLER_PERIMETER * lowShooterRPMVelocity / 60.0;
  }

  private void setBangBangSpeed(double desiredHighMotorSpeed, double desiredLowMotorSpeed){// m/s
    highShooterMotor.set(
      highShootBangBangController.calculate(desiredHighMotorSpeed, 
      highShooterRPMToMetersPerSecond(highShooterAnalogSensor.getVelocity()
      )));

    lowShooterMotor.set(
      lowShootBangBangController.calculate(
        desiredLowMotorSpeed, 
        lowShooterRPMToMetersPerSecond(lowShooterAnalogSensor.getVelocity()
        )));
  }

  private void stopShooterMotors(){
    highShooterMotor.set(0);
    lowShooterMotor.set(0);
  }
}