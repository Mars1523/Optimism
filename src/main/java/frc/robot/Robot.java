
package frc.robot;

import com.fasterxml.jackson.databind.util.PrimitiveArrayBuilder;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.MoveToTarget;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;

public class Robot extends TimedRobot {
  private Command autonomousCommand;
  Drivetrain drivetrain;
  Limelight limelight;
  MoveToTarget moveToTarget;
  private RobotContainer robotContainer;
  private XboxController primaryController = new XboxController(0);
  // private MoveToTarget moveToTarget = new MoveToTarget(drivetrain,
  // limelight);
  // MoveToTarget moveToTarget = new MoveToTarget(drivetrain, limelight);

  @Override
  public void robotInit() {

    robotContainer = new RobotContainer();
    CameraServer.startAutomaticCapture(0);

  }

  @Override
  public void robotPeriodic() {

    CommandScheduler.getInstance().run();

  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    autonomousCommand = robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (autonomousCommand != null) {
      autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.

    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }

    // new JoystickButton(primaryController,
    // XboxController.Button.kY.value)
    // .whileHeld(new MoveToTarget(drivetrain, limelight, primaryController));

  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    // if (primaryController.getYButtonPressed()) {

    // moveToTarget.schedule();

    // // System.out.println("y is true");
    // } else if (primaryController.getYButtonReleased()) {
    // moveToTarget.cancel();
    // }

  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }
}
