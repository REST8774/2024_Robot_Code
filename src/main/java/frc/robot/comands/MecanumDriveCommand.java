// MecanumDriveCommand.java

package frc.robot.comands;

import frc.robot.subsystems.MecanumDriveSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;

public class MecanumDriveCommand extends Command {
    private final MecanumDriveSubsystem m_driveSubsystem;
    private final Joystick m_stick;

    public MecanumDriveCommand(MecanumDriveSubsystem driveSubsystem, Joystick stick) {
        m_driveSubsystem = driveSubsystem;
        m_stick = stick;
        addRequirements(m_driveSubsystem);
    }

    @Override
    public void execute() {
        // Use the joystick Y axis for forward movement, X axis for lateral
        // movement, and Z axis for rotation.
        m_driveSubsystem.drive(-m_stick.getY(), -m_stick.getX(), -m_stick.getZ());
    }
}
