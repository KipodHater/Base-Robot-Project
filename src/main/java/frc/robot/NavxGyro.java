package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.SerialPort;

import static frc.robot.Constants.GyroConstants.*;

public class NavxGyro extends AHRS{
    private static AHRS gyro;
    
    public static void NavxGyro(){
        gyro = new AHRS(GYRO_PORT);

        setGyroOffset(GYRO_OFFSET);
    }

    public Rotation2d getGyroRotationYaw() {
        return Rotation2d.fromDegrees(-(double) gyro.getYaw());
    }
    
    public Rotation2d getGyroRotationAngle() {
        return Rotation2d.fromDegrees(-(double) gyro.getAngle());
    }

    public static void setGyroOffset(double offsetAngle){
        gyro.setAngleAdjustment(offsetAngle);
    }


}

