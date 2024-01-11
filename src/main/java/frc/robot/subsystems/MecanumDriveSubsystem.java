// MecanumDriveSubsystem.java

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MecanumDriveSubsystem extends SubsystemBase {
    // MecanumDrive instance for controlling the robot's drivetrain
    private final MecanumDrive m_robotDrive;

    // Constructor initializes the MecanumDriveSubsystem
    public MecanumDriveSubsystem() {
        // Create Spark Max motor controllers for each wheel
        PWMSparkMax frontLeft = new PWMSparkMax(2);
        PWMSparkMax rearLeft = new PWMSparkMax(3);
        PWMSparkMax frontRight = new PWMSparkMax(1);
        PWMSparkMax rearRight = new PWMSparkMax(0);

        // Invert the right side motors. Adjust as needed for your robot configuration.
        frontRight.setInverted(true);
        rearRight.setInverted(true);

        // Create MecanumDrive instance with the configured motor controllers
        m_robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
    }

    // Method to drive the robot using Mecanum drive
    public void drive(double forward, double strafe, double rotation) {
        // Drive the robot in Cartesian coordinates (forward, strafe, rotation)
        m_robotDrive.driveCartesian(forward, strafe, rotation);
    }
}
