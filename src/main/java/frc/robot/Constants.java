package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * Class containing constants for the robot and its components.
 */
public class Constants {

    /**
     * Constants related to the overall robot dimensions.
     */
    public static class Robot {
        public static final double k_width = 26; // Inches
        public static final double k_length = 28; // Inches
    }

    /**
     * Constants related to the intake subsystem.
     */
    public static class Intake {
        // Motors
        public static final int kIntakeMotorId = 9;
        public static final int kPivotMotorId = 10;

        // DIO
        public static final int k_pivotEncoderId = 0;
        public static final int k_intakeLimitSwitchId = 2;

        // Absolute encoder offset
        public static final double k_pivotEncoderOffset = 0.166842; // Straight up, sketchy to reset to "up"

        // Pivot set point angles
        public static final double k_pivotAngleGround = 60;
        public static final double k_pivotAngleSource = 190;
        public static final double k_pivotAngleAmp = k_pivotAngleSource;
        public static final double k_pivotAngleStow = 270;

        // Intake speeds
        public static final double k_intakeSpeed = 0.7;
        public static final double k_ejectSpeed = -0.45;
        public static final double k_feedShooterSpeed = -0.5;
    }

    /**
     * Constants related to the PCM (Pneumatics Control Module).
     */
    public static final int kPCMId = 0;
    public static final int kIntakeSolenoidForwardId = 2;

    // DIO

    /**
     * Constants related to the shooter subsystem.
     */
    public static class Shooter {
        public static final int kShooterLeftMotorId = 12;
        public static final int kShooterRightMotorId = 13;

        public static final double kShooterP = 0.00005;
        public static final double kShooterI = 0.0;
        public static final double kShooterD = 0.0;
        public static final double kShooterFF = 0.0002;

        public static final double kShooterMinOutput = -0.8;
        public static final double kShooterMaxOutput = 0.8;
    }

    /**
     * Constants related to the climber subsystem.
     */
    public static class Climber {
        public static final int kClimberLeftMotorId = 14;
        public static final int kClimberRightMotorId = 15;
        public static final double kClimberClimbSpeed = 480.0; // RPM
        public static final double kClimberReleaseSpeed = -480.0; // RPM

        public static final double kClimberGearRatio = 1.0 / 12.0;

        public static final double kClimberP = 0.001;
        public static final double kClimberI = 0.0;
        public static final double kClimberD = 0.0;
        public static final double kClimberMinOutput = -0.5;

        public static final double kClimberMaxOutput = 0.5;
    }

    /**
     * Constants related to the drivetrain subsystem.
     */
    public static class Drivetrain {
        public static final int kDrivetrainFLMotorId = 5;
        public static final int kDrivetrainBLMotorId = 6;
        public static final int kDrivetrainFRMotorId = 7;
        public static final int kDrivetrainBRMotorId = 8;
    }

    /**
     * Constants related to the field dimensions.
     */
    public static class Field {
        public static final double k_width = Units.feetToMeters(54.0);
        public static final double k_length = Units.feetToMeters(27.0);
    }

    /**
     * Constants related to LEDs.
     */
    public static class LEDs {
        public static final int k_PWMId = 0;
        public static final int k_totalLength = 300;
    }
}
