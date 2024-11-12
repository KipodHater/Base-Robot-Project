package frc.robot;

public class Constants {
    public class pivotConstants{
        public static final int PIVOT_MOTOR_ID = 15;

        public static final double PIVOT_MOTOR_KP = 0.01;
        public static final double PIVOT_MOTOR_KI = 0.00;
        public static final double PIVOT_MOTOR_KD = 0.05;

        public static final double PIVOT_MOTOR_KS = 0.17;
        public static final double PIVOT_MOTOR_KG = 3.11;
        public static final double PIVOT_MOTOR_KV = 0.00;

        public static final double PIVOT_MOTOR_MAX_SPEED = 3;//m/s
        public static final double PIVOT_MOTOR_MAX_ACCELERATION = 3;//m/s^2

        public static final double PIVOT_MOTOR_ENCODER_OFFSET = 355.0;

        public static final double PIVOT_MOTOR_MAX_ANGLE = 70.0;
        public static final double PIVOT_MOTOR_MIN_ANGLE = 5.0;
    }

    public class shooterConstants{
        public static final int HIGH_SHOOTER_MOTOR_ID = 25;
        public static final int LOW_SHOOTER_MOTOR_ID = 26;

        public static final double LOW_SHOOTER_ROLLER_PERIMETER = 0.32;//not updated!
        public static final double HIGH_SHOOTER_ROLLER_PERIMETER = 0.32;//not accurate
    }

    public class swerveContants{
        public static final int FRONT_RIGHT_ANGLE_MOTOR_ID = 1;
        public static final int FRONT_RIGHT_DRIVE_MOTOR_ID = 1;
        
        public static final int FRONT_LEFT_DRIVE_MOTOR_ID = 2;
        public static final int FRONT_LEFT_ANGLE_MOTOR_ID = 2;

        public static final int BACK_RIGHT_DRIVE_MOTOR_ID = 3;
        public static final int BACK_RIGHT_ANGLE_MOTOR_ID = 3;

        public static final int BACK_LEFT_DRIVE_MOTOR_ID = 4;
        public static final int BACK_LEFT_ANGLE_MOTOR_ID = 4;

        public static final double DISTANCE_FROM_CENTER = 0.251;//m
    }
}
