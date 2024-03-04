package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.net.PortForwarder;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Robot extends TimedRobot {
    private static final int FRONT_LEFT_CHANNEL = 5;
    private static final int REAR_LEFT_CHANNEL = 4;
    private static final int FRONT_RIGHT_CHANNEL = 2;
    private static final int REAR_RIGHT_CHANNEL = 3;
    private static final int INTAKE_LEVER_CHANNEL = 7;
    private static final int INTAKE_VACUUM_CHANNEL = 6;
    private static final int SHOOTER_L = 8;
    private static final int SHOOTER_R = 9;

    private CANSparkMax frontLeft, frontRight, rearLeft, rearRight, intakeLever, intakeVacuum, sparkshooterL, sparkshooterR;
    private Joystick driveJoystick;
    private XboxController operatorController;
    private MecanumDrive robotDrive;

    private double startTime;
    private double shooterSpeed = 0.95;
    private double spinSpeedDefault = 0.2;
    private double spinSpeed = spinSpeedDefault;
    private DutyCycleEncoder pivotEncoder;
    private DigitalInput noteSensor;

    private double pivotangle;
    private double leverAngle = 0.48;

    @Override
    public void robotInit() {
        noteSensor = new DigitalInput(8);
        pivotEncoder = new DutyCycleEncoder(9);
        frontLeft = new CANSparkMax(FRONT_LEFT_CHANNEL, MotorType.kBrushless);
        rearLeft = new CANSparkMax(REAR_LEFT_CHANNEL, MotorType.kBrushless);
        frontRight = new CANSparkMax(FRONT_RIGHT_CHANNEL, MotorType.kBrushless);
        rearRight = new CANSparkMax(REAR_RIGHT_CHANNEL, MotorType.kBrushless);
        intakeLever = new CANSparkMax(INTAKE_LEVER_CHANNEL, MotorType.kBrushless);
        intakeVacuum = new CANSparkMax(INTAKE_VACUUM_CHANNEL, MotorType.kBrushless);
        sparkshooterL = new CANSparkMax(SHOOTER_L, MotorType.kBrushless);
        sparkshooterR = new CANSparkMax(SHOOTER_R, MotorType.kBrushless);

        driveJoystick = new Joystick(0);
        operatorController = new XboxController(1);

        frontRight.setInverted(true);
        rearRight.setInverted(true);
        sparkshooterL.setInverted(true);

        robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

        //LimeLight Code

        for (int port = 5800; port <= 5807; port++) {
            PortForwarder.add(port, "limelight.local", port);
        }


        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry ta = table.getEntry("ta");

        //read values periodically
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);

        //post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
    }

    @Override
    public void autonomousInit() {
        startTime = Timer.getFPGATimestamp();
    }

    @Override
    public void autonomousPeriodic() {
        double time = Timer.getFPGATimestamp() - startTime;
        double pivotSpeed;
        pivotangle = pivotEncoder.getAbsolutePosition();
        if (time <= 1) {
            sparkshooterL.set(shooterSpeed);
            sparkshooterR.set(shooterSpeed);
        } else if (time <= 3) {
            intakeVacuum.set(-1);
        } else if (time <= 6) {
            intakeVacuum.set(0);

            pivotSpeed = (0.22 - pivotangle) / 0.3;
            intakeLever.set(pivotSpeed);
        } else if (time <= 8) {
            frontLeft.set(0.2);
            frontRight.set(0.2);
            rearLeft.set(0.2);
            rearRight.set(0.2);
        }else if (time <= 9) {
            intakeVacuum.set(.6);
            frontLeft.set(0.1);
            frontRight.set(0.1);
            rearLeft.set(0.1);
            rearRight.set(0.1);
        } else if (time <= 12) {
            frontLeft.set(0);
            frontRight.set(0);
            rearLeft.set(0);
            rearRight.set(0);
            pivotSpeed = (0.77 - pivotangle) / 0.3;
            intakeLever.set(pivotSpeed);
        } else if (time <= 14) {
            frontLeft.set(-0.3);
            frontRight.set(-0.3);
            rearLeft.set(-0.3);
            rearRight.set(-0.3);
        } else if (time <= 15) {
            frontLeft.set(0);
            frontRight.set(0);
            rearLeft.set(0);
            rearRight.set(0);
            intakeVacuum.set(-1);
        } else {
            frontLeft.set(0);
            frontRight.set(0);
            rearLeft.set(0);
            rearRight.set(0);
            sparkshooterL.set(0);
            sparkshooterR.set(0);
            intakeVacuum.set(0);
        }
    }

    @Override
    public void teleopInit() {
        intakeVacuum.set(0);
    }

    @Override
    public void teleopPeriodic() {
        if (driveJoystick.getTrigger())
            spinSpeed = 1.0;
        else
            spinSpeed = spinSpeedDefault;

        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry ta = table.getEntry("ta");

        //read values periodically
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);

        //post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);

        double adjustCam;
        double speed = -driveJoystick.getY(); // Remove the negative sign here
        double turn = driveJoystick.getZ() * spinSpeed;
        double strafe = driveJoystick.getX();
        double pivotangle = pivotEncoder.getAbsolutePosition();
        double leverAngle = 0.48;

        // ShuffleBoard
        SmartDashboard.putNumber("Bridge Angle", pivotangle);
        SmartDashboard.putNumber("Have Note", noteSensor.get() ? 0 : 1);
        intakeLever.getAbsoluteEncoder();

        if (Math.abs(turn) < 0.1) {
            turn = 0;
        }

        // Other teleop logic goes here
        if (operatorController.getAButton()) {
            intakeVacuum.set(1);
        } else if (operatorController.getXButton()) {
            intakeVacuum.set(-0.6);
        } else {
            intakeVacuum.set(0);
        }

        if (driveJoystick.getRawButton(2)) {
            adjustCam = (((x + 10) / 10)) * 0.1;

            frontLeft.set(adjustCam);
            frontRight.set(-adjustCam); // Invert the sign for moving backwards
            rearLeft.set(adjustCam);
            rearRight.set(-adjustCam);
        } else {
            robotDrive.driveCartesian(speed, strafe, turn);
            frontLeft.set((speed + turn + strafe) * 0.5);
            frontRight.set((speed - turn - strafe) * 0.5); // Invert the sign for moving backwards
            rearLeft.set((speed + turn - strafe) * 0.5);
            rearRight.set((speed - turn + strafe) * 0.5); // Invert the sign for moving backwards
        }

        if (operatorController.getRightTriggerAxis() > 0.5) {
            sparkshooterL.set(1.0);
            sparkshooterR.set(1.0);
        } else if (operatorController.getRightBumper()) {
            sparkshooterL.set(shooterSpeed);
            sparkshooterR.set(shooterSpeed);
        } else if (operatorController.getLeftBumper()) {
            sparkshooterL.set(0);
            sparkshooterR.set(0);
        }

        double leverSpeed = operatorController.getLeftY();
        if (operatorController.getLeftStickButton()) {
            if (pivotangle < leverAngle)
                leverSpeed = (leverAngle - pivotangle) / 0.3;
            else if (pivotangle > leverAngle)
                leverSpeed = (leverAngle - pivotangle) / 0.3;
        } else if (Math.abs(leverSpeed) > 0.1) {
            leverSpeed = leverSpeed * 0.75;
        } else {
            leverSpeed = 0.0;
        }

        if ((pivotangle > 0.77 && leverSpeed > 0) || (pivotangle < 0.22 && leverSpeed < 0)) {
            leverSpeed = 0.0;
        }
        intakeLever.set(leverSpeed);
    }
}
