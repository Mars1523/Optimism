
package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.SuppliedValueWidget;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

public class DefaultDrive extends CommandBase {
  private XboxController primaryController;
  private Drivetrain drivetrain;
  private XboxController secondaryController;

  SlewRateLimiter filter = new SlewRateLimiter(0.5);
  SlewRateLimiter filterTurn = new SlewRateLimiter(1);
  Debouncer m_debouncer = new Debouncer(0.3, Debouncer.DebounceType.kBoth);

  private final NetworkTableEntry fancyDriveEntry = Shuffleboard.getTab("Drive")
      .add("Fancy Drive", false)
      .getEntry();

  // Slew rate limiters to make joystick inputs more gentle; 1/3 sec from 0 to 1.
  private final SlewRateLimiter speedLimiter = new SlewRateLimiter(3);
  private final SlewRateLimiter rotLimiter = new SlewRateLimiter(3);
  private final PIDController movePID;
  private final PIDController turnPID;
  private final Limelight limelight;

  private double getLimelightArea() {
    return limelight.getArea();
  }

  private double getShooterEnabled() {
    if (primaryController.getYButton()) {
      return 25;
    } else {
      return 0;
    }
  }

  private final SuppliedValueWidget<Double> limeLightArea = Shuffleboard.getTab("Drive")
      .addNumber("Area", this::getLimelightArea);

  private final SuppliedValueWidget<Double> yPressed = Shuffleboard.getTab("Drive")
      .addNumber("YPress", this::getShooterEnabled);

  public DefaultDrive(Drivetrain drivetrain, XboxController primaryController, Limelight m_Limelight,
      XboxController secondaryController) {
    addRequirements(drivetrain);
    limelight = m_Limelight;
    this.primaryController = primaryController;
    this.drivetrain = drivetrain;
    this.secondaryController = secondaryController;

    movePID = new PIDController(0.6, 0.001, 0);
    movePID.setSetpoint(1.5);
    turnPID = new PIDController(0.05, 0, 0);
    turnPID.setSetpoint(0);

  }

  static double deadband(double value, double limit) {
    if (Math.abs(value) > limit) {
      return value;
    } else {
      return 0;
    }
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // drivetrain.driveRaw(, 0);

  // System.out.println("turn" + turnOutput + " go " + output);

  @Override
  public void execute() {

    double output = movePID.calculate(limelight.getArea());
    double turnOutput = turnPID.calculate(limelight.getX());
    output = filter.calculate(output);
    turnOutput = filterTurn.calculate(turnOutput);

    if (primaryController.getYButton()) {

      drivetrain.driveRaw(0,
          MathUtil.clamp(turnOutput, -.2, .2));

      // } else if (secondaryController.getYButton()) {
      // drivetrain.driveRaw(MathUtil.clamp(-output, -.2, .2),
      // MathUtil.clamp(turnOutput, -.2, .2));
    } else {
      if (fancyDriveEntry.getBoolean(false)) {
        // Get the x speed. We are inverting this because Xbox controllers return
        // negative values when we push forward.
        double rawX = primaryController.getLeftX();

        final double rot = rotLimiter.calculate(deadband(rawX, .1))
            * Constants.DriveConstants.kMaxSpeed;

        // Get the rate of angular rotation. We are inverting this because we want a
        // positive value when we pull to the left (remember, CCW is positive in
        // mathematics). Xbox controllers return positive values when you pull to
        // the right by default.

        double rawY = primaryController.getRightY();
        final double xSpeed = speedLimiter.calculate(deadband(rawY, .1))
            * Constants.DriveConstants.kMaxAngularSpeed;

        drivetrain.fancyDrive(xSpeed, rot);

      } else {
        final double ySpeed = speedLimiter.calculate(-primaryController.getRightY());
        drivetrain.boringDrive(ySpeed, primaryController.getLeftX());
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
