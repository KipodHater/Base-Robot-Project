package frc.robot;

import com.ctre.phoenix6.signals.NeutralModeValue;

public class Constants {
    public static final class pivotConstants{
        public static final int PIVOT_MOTOR_ID = 15; // no units

        public static final double PIVOT_MOTOR_KP = 0.01; // no units
        public static final double PIVOT_MOTOR_KI = 0.00; // no units
        public static final double PIVOT_MOTOR_KD = 0.05; // no units

        public static final double PIVOT_MOTOR_KS = 0.17; // no units
        public static final double PIVOT_MOTOR_KG = 3.11; // no units
        public static final double PIVOT_MOTOR_KV = 0.00; //no units

        public static final double PIVOT_MOTOR_MAX_SPEED = 3; //m/s
        public static final double PIVOT_MOTOR_MAX_ACCELERATION = 3; //m/s^2

        public static final double PIVOT_MOTOR_ENCODER_OFFSET = 355.0; // deg

        public static final double PIVOT_MOTOR_MAX_ANGLE = 70.0; // deg
        public static final double PIVOT_MOTOR_MIN_ANGLE = 5.0; // deg
    }

    public static final class shooterConstants{
        public static final int HIGH_SHOOTER_MOTOR_ID = 25;
        public static final int LOW_SHOOTER_MOTOR_ID = 26;

        public static final double LOW_SHOOTER_ROLLER_PERIMETER = 0.32;
        public static final double HIGH_SHOOTER_ROLLER_PERIMETER = 0.32;//not accurate
    }

    public static final class swerveContants{
        public static final int FRONT_LEFT_DRIVE_MOTOR_ID = ;
        public static final int FRONT_LEFT_ANGLE_MOTOR_ID = ;
        public static final boolean FRONT_LEFT_DRIVE_ENCODER_REVERSED = ;
        public static final boolean FRONT_LEFT_TURNING_ENCODER_REVERSED = ;
        public static final int FRONT_LEFT_DRIVE_ABSOLUTE_ENODER_ID = ;
        public static final double FRONT_LEFT_DRIVE_ABSOLUTE_ENCODER_OFFSET = ;
        public static final boolean FRONT_LEFT_DRIVE_ABSOLUTE_ENCODER_REVERSED = ;

        public static final int FRONT_RIGHT_DRIVE_MOTOR_ID = ;
        public static final int FRONT_RIGHT_ANGLE_MOTOR_ID = ;
        public static final boolean FRONT_RIGHT_DRIVE_ENCODER_REVERSED = ;
        public static final boolean FRONT_RIGHT_TURNING_ENCODER_REVERSED = ;
        public static final int FRONT_RIGHT_DRIVE_ABSOLUTE_ENODER_ID = ;
        public static final double FRONT_RIGHT_DRIVE_ABSOLUTE_ENCODER_OFFSET = ;
        public static final boolean FRONT_RIGHT_DRIVE_ABSOLUTE_ENCODER_REVERSED = ;

        public static final int BACK_LEFT_DRIVE_MOTOR_ID = ;
        public static final int BACK_LEFT_ANGLE_MOTOR_ID = ;
        public static final boolean BACK_LEFT_DRIVE_ENCODER_REVERSED = ;
        public static final boolean BACK_LEFT_TURNING_ENCODER_REVERSED = ;
        public static final int BACK_LEFT_DRIVE_ABSOLUTE_ENODER_ID = ;
        public static final double BACK_LEFT_DRIVE_ABSOLUTE_ENCODER_OFFSET = ;
        public static final boolean BACK_LEFT_DRIVE_ABSOLUTE_ENCODER_REVERSED = ;

        public static final int BACK_RIGHT_DRIVE_MOTOR_ID = ;
        public static final int BACK_RIGHT_ANGLE_MOTOR_ID = ;
        public static final boolean BACK_RIGHT_DRIVE_ENCODER_REVERSED = ;
        public static final boolean BACK_RIGHT_TURNING_ENCODER_REVERSED = ;
        public static final int BACK_RIGHT_DRIVE_ABSOLUTE_ENODER_ID = ;
        public static final double BACK_RIGHT_DRIVE_ABSOLUTE_ENCODER_OFFSET = ;
        public static final boolean BACK_RIGHT_DRIVE_ABSOLUTE_ENCODER_REVERSED = ;
        
    }
    public static final class swerveModuleConstants{
        public static final double SWERVE_ROTATION_KP = 1.0;
        public static final double SWERVE_ROTATION_KI = 0.0;
        public static final double SWERVE_ROTATION_KD = 0.0;

        public static final double ANGLE_GEAR_RATIO = (396.0/35.0)/1.0;

        public static final double TRACK_WIDTH = 0.596;
        public static final double WHEELBASE = 0.596;
    }

    public static final class ctreConstants{
        public static final double SWERVE_ANGLE_MOTOR_KP = 0.0;
        public static final double SWERVE_ANGLE_MOTOR_KI = 0.0;
        public static final double SWERVE_ANGLE_MOTOR_KD = 0.0;
        public static final double SWERVE_ANGLE_MOTOR_KA = 0.0;
        public static final double SWERVE_ANGLE_MOTOR_KG = 0.0;
        public static final double SWERVE_ANGLE_MOTOR_KS = 0.0;
        public static final double SWERVE_ANGLE_MOTOR_KV = 0.0;

        public static final boolean ANGLE_MOTOR_INVERTED = false;
        public static final NeutralModeValue ANGLE_MOTOR_NEUTRAL_MODE = NeutralModeValue.Coast;
        public static final NeutralModeValue DRIVE_MOTOR_NEUTRAL_MODE = NeutralModeValue.Brake;

        
    }
}