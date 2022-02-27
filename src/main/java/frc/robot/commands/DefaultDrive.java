
package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

public class DefaultDrive extends CommandBase {
  private XboxController primaryController;
  private Drivetrain drivetrain;

  private final NetworkTableEntry fancyDriveEntry = Shuffleboard.getTab("Drive")
      .add("Fancy Drive", false)
      .getEntry();

  // Slew rate limiters to make joystick inputs more gentle; 1/3 sec from 0 to 1.
  private final SlewRateLimiter speedLimiter = new SlewRateLimiter(3);
  private final SlewRateLimiter rotLimiter = new SlewRateLimiter(3);

  public DefaultDrive(Drivetrain drivetrain, XboxController primaryController) {
    addRequirements(drivetrain);
    this.primaryController = primaryController;
    this.drivetrain = drivetrain;
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

  @Override
  public void execute() {

    if (fancyDriveEntry.getBoolean(true)) {
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
