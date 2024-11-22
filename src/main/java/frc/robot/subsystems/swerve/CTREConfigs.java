package frc.robot.subsystems.swerve;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import static frc.robot.Constants.ctreConstants.*;
import static frc.robot.Constants.swerveModuleConstants.ANGLE_GEAR_RATIO;
public final class CTREConfigs {
    public TalonFXConfiguration driveMotorConfig = new TalonFXConfiguration();
    public TalonFXConfiguration angleMotorConfig = new TalonFXConfiguration();
    public CANcoderConfiguration swerveCANCoderConfig = new CANcoderConfiguration();

    public CTREConfigs(){
        angleMotorConfig.Slot0.kP = SWERVE_ANGLE_MOTOR_KP;
        angleMotorConfig.Slot0.kI = SWERVE_ANGLE_MOTOR_KI;
        angleMotorConfig.Slot0.kD = SWERVE_ANGLE_MOTOR_KD;
        // angleMotorConfig.Slot0.kA = SWERVE_ANGLE_MOTOR_KA;
        // angleMotorConfig.Slot0.kG = SWERVE_ANGLE_MOTOR_KG;
        // angleMotorConfig.Slot0.kS = SWERVE_ANGLE_MOTOR_KS;
        // angleMotorConfig.Slot0.kV = SWERVE_ANGLE_MOTOR_KV;

        angleMotorConfig.MotorOutput.Inverted = ANGLE_MOTOR_INVERTED;
        angleMotorConfig.Feedback.SensorToMechanismRatio = ANGLE_GEAR_RATIO;

        angleMotorConfig.MotorOutput.NeutralMode = ANGLE_MOTOR_NEUTRAL_MODE;
    }
}
