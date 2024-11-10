package frc.robot;

public class Constants {
    public class pivotConstants{
        public static final int PIVOT_MOTOR_ID = 1;

        public static final double PIVOT_MOTOR_KP = 0.05;
        public static final double PIVOT_MOTOR_KI = 0.05;
        public static final double PIVOT_MOTOR_KD = 0.05;

        public static final double PIVOT_MOTOR_KS = 0.05;
        public static final double PIVOT_MOTOR_KG = 0.05;
        public static final double PIVOT_MOTOR_KV = 0.05;

        public static final double PIVOT_MOTOR_MAX_SPEED = 3;//m/s
        public static final double PIVOT_MOTOR_MAX_ACCELERATION = 3;//m/s^2

        public static final double PIVOT_MOTOR_ENCODER_OFFSET = 0.0;

        public static final double PIVOT_MOTOR_MAX_ANGLE = 90.0;
        public static final double PIVOT_MOTOR_MIN_ANGLE = 0.0;
    }

    public class shooterConstants{
        public static final int HIGH_SHOOTER_MOTOR_ID = 2;//CHANGE!!!!
        public static final int LOW_SHOOTER_MOTOR_ID = 3;//CHANGE!!!!

        public static final double LOW_SHOOTER_ROLLER_PERIMETER = 1;//not updated!
        public static final double HIGH_SHOOTER_ROLLER_PERIMETER = 1;//not updated!

    }
}
