
package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeTransport;

public class DefaultIntakeTransport extends CommandBase {

  private XboxController primaryController;
  private IntakeTransport intakeTransportSys;

  public DefaultIntakeTransport(XboxController primaryController, IntakeTransport intakeTransportSys) {

    addRequirements(intakeTransportSys);
    this.primaryController = primaryController;
    this.intakeTransportSys = intakeTransportSys;

  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {

    /*
     * if (primaryController.getRightBumperPressed() == true) {
     * intakeTransportSys.wristDownFront();
     * }
     * 
     * if (primaryController.getRightBumperReleased() == true) {
     * intakeTransportSys.wristUpFront();
     * }
     */

    if (primaryController.getLeftBumperPressed() == true) {
      intakeTransportSys.wristDownBack();
    }

    if (primaryController.getLeftBumperReleased() == true) {
      intakeTransportSys.wristUpBack();
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
