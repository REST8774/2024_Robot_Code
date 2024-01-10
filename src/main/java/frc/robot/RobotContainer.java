// RobotContainer.java

package frc.robot;

import frc.robot.subsystems.MecanumDriveSubsystem;
import frc.robot.comands.MecanumDriveCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class RobotContainer {
    private final MecanumDriveSubsystem m_driveSubsystem;
    private final MecanumDriveCommand m_driveCommand;

    public RobotContainer() {
        m_driveSubsystem = new MecanumDriveSubsystem();
        m_driveCommand = new MecanumDriveCommand(m_driveSubsystem, new Joystick(0));

        configureButtonBindings();
        CommandScheduler.getInstance().setDefaultCommand(m_driveSubsystem, m_driveCommand);
    }

    private void configureButtonBindings() {
        // Add any button bindings here if needed
    }

    public Command getAutonomousCommand() {
        // Return your autonomous command here if needed
        return null;
    }
}
