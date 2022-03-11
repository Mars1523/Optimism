
package frc.robot;

import javax.sound.sampled.LineEvent;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.commands.DefaultArms;
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.DefaultIntakeTransport;
import frc.robot.commands.DefaultTurret;
import frc.robot.commands.DriveForward;
import frc.robot.commands.FinalAuto;
import frc.robot.commands.FinalAuto2;
import frc.robot.subsystems.Arms;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IntakeTransport;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Turret;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {

  private final PneumaticsControlModule pCM = new PneumaticsControlModule(0);

  // The robot's subsystems
  private final Arms arms = new Arms();
  private final Drivetrain drivetrain = new Drivetrain();
  private final IntakeTransport intrans = new IntakeTransport(pCM);
  private final Limelight limelight = new Limelight();
  private final Turret turret = new Turret(limelight);

  // controllers
  private final XboxController primaryController = new XboxController(0);
  private final XboxController secondaryController = new XboxController(1);

  // robo commands
  private final DefaultDrive defaultDrive = new DefaultDrive(drivetrain, primaryController, limelight,
      secondaryController);
  private final DefaultArms defaultArms = new DefaultArms(arms, secondaryController);
  private final DefaultTurret defaultTurret = new DefaultTurret(secondaryController, turret, primaryController,
      limelight);
  private final DefaultIntakeTransport defaultIntakeTransport = new DefaultIntakeTransport(primaryController, intrans);

  // private final DefaultLimelight defaultLimelight = new
  // DefaultLimelight(primaryController, limelight);\
  // private final DriveForward driveForward = new DriveForward(drivetrain, 1.3);
  // private final FinalAuto driveForward = new FinalAuto(drivetrain, turret);
  private final DriveForward driveForward = new DriveForward(drivetrain, 1.3);
  // private final LimeLightToggle limeLightToggle = new LimeLightToggle();

  // DriveForward driveForward;
  // private final MoveToTarget moveToTarget = new MoveToTarget(drivetrain,
  // limelight);
  // private final FinalAuto2 finalAuto2 = new FinalAuto2(driveForward,
  // drivetrain, turret, intrans);
  private final FinalAuto finalAuto = new FinalAuto(driveForward, drivetrain, limelight, turret, intrans);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Shuffleboard.getTab("Debug").add(new PowerDistribution(1, ModuleType.kCTRE));

    drivetrain.setDefaultCommand(defaultDrive);
    arms.setDefaultCommand(defaultArms);
    intrans.setDefaultCommand(defaultIntakeTransport);
    turret.setDefaultCommand(defaultTurret);

    // Configure the button bindings
    configureButtonBindings();
  }

  // public void limeLightTargeting() {
  // if (primaryController.getYButtonPressed()) {
  // moveToTarget.schedule();
  // } else if (primaryController.getYButtonReleased()) {
  // moveToTarget.cancel();
  // }
  // }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return finalAuto;
  }
}
