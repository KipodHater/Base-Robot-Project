package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class SuperStructure {
    private PivotSubsystem pivot;
    private ShooterSubsystem shooter;
    public SuperStructure(){
        pivot = new PivotSubsystem();
        shooter = new ShooterSubsystem();
    }

    public Command setPivotAngle(double pivotAngle){
        return pivot.setPivotPosition(pivotAngle).raceWith(new WaitCommand(2));
    }

    public Command shootingSequenceCommand(double shootingSpeed){
        return shooter.shootingCommand(shootingSpeed, 0)
        .raceWith(new WaitCommand(1).andThen(
        shooter.shootingCommand(shootingSpeed, shootingSpeed)).raceWith(new WaitCommand(1))
        .andThen(shooter.shootingCommand()));
    }

    public Command intakingSequenceCommand(){
        return shooter.intakingCommand().raceWith(new WaitCommand(2));
    }
    // public Command shootingSequence(double pivotAngle, double shootingSpeed){
    //     return pivot.setPivotPosition(pivotAngle).alongwith(shooter.setHighShooterSpeed(shootingSpeed)).racewith(2)
    // }
}
