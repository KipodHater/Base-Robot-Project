package frc.lib.subsystems.positionMotorIO;

public interface PositionMotorIO {


    public static class MotorIOInputs{
        double motorUnitsPerSecond = 0.0;
        double motorPosition = 0.0;
        double motorPercentInput = 0.0;
        double motorCurrent = 0.0;
    }

    public void invertMotor(boolean isInverted);

    public void setPIDCoefficients(double kP,double kI,double kD, double kIz,
    double kFF, double kMaxOutput, double kMinOutput, double maxRPM, double maxVel,
    double minVel, double maxAcc,double allowedErr);

    public void setFeedforwardCoefficients(double kS, double kV, double kA);
    
    public void setNeutralMode(boolean neutralModeCoast);
    
    public default void updateInputs(MotorIOInputs inputs){}

    public void setPositionRadian(double radians);

    public double getPositionRadian();

    public void setMotorLoopMode(boolean isOpenLoop);

    public void zeroMotor();

    public void periodic();
}
