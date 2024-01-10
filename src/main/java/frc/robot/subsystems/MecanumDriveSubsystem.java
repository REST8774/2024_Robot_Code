// MecanumDriveSubsystem.java

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MecanumDriveSubsystem extends SubsystemBase {
    private final MecanumDrive m_robotDrive;

    public MecanumDriveSubsystem() {
        PWMSparkMax frontLeft = new PWMSparkMax(2);
        PWMSparkMax rearLeft = new PWMSparkMax(3);
        PWMSparkMax frontRight = new PWMSparkMax(1);
        PWMSparkMax rearRight = new PWMSparkMax(0);

        // Invert the right side motors.
        // You may need to change or remove this to match your robot.
        frontRight.setInverted(true);
        rearRight.setInverted(true);

        m_robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
    }

    public void drive(double forward, double strafe, double rotation) {
        m_robotDrive.driveCartesian(forward, strafe, rotation);
    }
}
