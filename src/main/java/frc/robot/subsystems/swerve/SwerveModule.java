package frc.robot.subsystems.swerve;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.AnalogInput;
import static frc.robot.Constants.swerveModuleConstants.*;

public class SwerveModule {
    
    private final TalonFX driveFXMotor;
    private final TalonFX rotateFXMotor;

    private final CANcoder driveEncoder;
    private final CANcoder rotateEncoder;

    private final PIDController rotatePIDController;
    
    private final AnalogInput absoluteEncoder;
    private final boolean absoluteEncoderReversed;
    private final double absoluteEncoderOffsetRad;

    public swerveModule(int driveMotorID, int rotateMotorID, boolean driveMotorReversed, boolean rotateMotorReversed,
            int absoluteEncoderID, double absoluteEncoderOffset, boolean absoluteEncoderReversed){
                
                this.absoluteEncoderOffsetRad = absoluteEncoderOffset;
                this.absoluteEncoderReversed = absoluteEncoderReversed;
                absoluteEncoder = new AnalogInput(absoluteEncoderID);

                driveFXMotor = new TalonFX(driveMotorID);
                rotateFXMotor = new TalonFX(rotateMotorID);

                driveFXMotor.setInverted(driveMotorReversed);
                rotateFXMotor.setInverted(rotateMotorReversed);

                //set encoder somehow and offset???

                rotatePIDController = new PIDController(SWERVE_ROTATION_KP, SWERVE_ROTATION_KI, SWERVE_ROTATION_KD);
                rotatePIDController.enableContinuousInput(-Math.PI, Math.PI);

                resetEncoders();
    }

    public double getDrivePosition(){
        return driveFXMotor.getSelectedSensorPosition();
    }

    public double getDriveVelocity(){
        return driveFXMotor.getSelectedSensorVelocity();
    }

    public double getAnglePosition(){

    }

    public double getAngleVelocity(){
        
    }

    private void resetEncoders(){

    }

    public SwerveModuleState getState(){
        return new SwerveModuleState(getDriveVelocity(), new Rotation2d(getTurningPosition()));
    }

    public void stop(){
        driveFXMotor.set(0);
        rotateFXMotor.set(0);
    }
}
