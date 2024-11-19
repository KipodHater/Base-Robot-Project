package frc.robot;

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


    }
    public static final class swerveModuleConstants{
        public static final double SWERVE_ROTATION_KP = 1.0;
        public static final double SWERVE_ROTATION_KI = 0.0;
        public static final double SWERVE_ROTATION_KD = 0.0;

        public static final double ANGLE_GEAR_RATIO = (396.0/35.0)/1.0;

        public static final double TRACK_WIDTH = 0.596;
        public static final double WHEELBASE = 0.596;
    }

}
