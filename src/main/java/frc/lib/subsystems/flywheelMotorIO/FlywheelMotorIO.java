package frc.lib.subsystems.flywheelMotorIO;

import com.ctre.phoenix.motorcontrol.NeutralMode;

public interface FlywheelMotorIO {

    // enum motorNeutralMode{
    //     Brake,
    //     Coast
    // }

    // enum motorMode{
    //     ClosedLoop,
    //     PIDControl
    // }
    public static class MotorIOInputs{
        double motorUnitsPerSecond = 0.0;
        double motorPosition = 0.0;
        double motorPercentInput = 0.0;
        double motorCurrent = 0.0;
    }

    // public void initializeFlywheel();

    public void invertMotor(boolean isInverted);

    public void setPIDCoefficients(double kp, double ki, double kd/*, double kIzone, double kFeedForwardConstant, double kMinOutput, double kMaxOutput*/);

    public void setFeedforwardCoefficients(double kS, double kV, double kA);
    
    public void setNeutralMode(boolean neutralModeCoast);
    
    public default void updateInputs(MotorIOInputs inputs){}

    public void setVelocityMPS(double velocitySetpoint);

    public double getVelocityMPS();

    public void setMotorLoopMode(boolean isOpenLoop);

    public void stopMotor();

    public void periodic();
}
