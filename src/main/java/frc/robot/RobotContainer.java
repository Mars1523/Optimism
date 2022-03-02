
package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.DefaultArms;
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.DefaultIntakeTransport;
import frc.robot.commands.DefaultTurret;
import frc.robot.commands.LimeLightCalcDist;
import frc.robot.commands.MoveToTarget;
import frc.robot.commands.DefaultLimelight;
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
  private final Turret turret = new Turret();
  private final Limelight limelight = new Limelight();

  // controllers
  private final XboxController primaryController = new XboxController(0);
  private final XboxController secondaryController = new XboxController(1);

  // robo commands
  private final DefaultDrive defaultDrive = new DefaultDrive(drivetrain, primaryController);
  private final DefaultArms defaultArms = new DefaultArms(arms, secondaryController);
  private final DefaultTurret defaultTurret = new DefaultTurret(secondaryController, turret, primaryController);
  private final DefaultIntakeTransport defaultIntakeTransport = new DefaultIntakeTransport(primaryController, intrans);
  private final DefaultLimelight defaultLimelight = new DefaultLimelight(primaryController, limelight);
  private final MoveToTarget moveToTarget = new MoveToTarget(drivetrain, limelight, primaryController);
  private final LimeLightCalcDist limeLightCalcDist = new LimeLightCalcDist(primaryController, limelight);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    drivetrain.setDefaultCommand(defaultDrive);
    arms.setDefaultCommand(defaultArms);
    intrans.setDefaultCommand(defaultIntakeTransport);
    turret.setDefaultCommand(defaultTurret);
    limelight.setDefaultCommand(limeLightCalcDist);

    // Configure the button bindings
    configureButtonBindings();
  }

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
    return defaultDrive;
  }
}
