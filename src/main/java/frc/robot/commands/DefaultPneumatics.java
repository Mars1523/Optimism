
package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeTransport;

public class DefaultPneumatics extends CommandBase {

  private XboxController primaryController;
  private IntakeTransport intakeTransportSys;
  private XboxController secondaryController;

  public DefaultPneumatics(XboxController primaryController, IntakeTransport intakeTransportSys,
      XboxController secondaryController) {
    addRequirements(intakeTransportSys);
    this.primaryController = primaryController;
    this.intakeTransportSys = intakeTransportSys;
    this.secondaryController = secondaryController;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {

    if (primaryController.getRightBumperPressed() == true) {
      intakeTransportSys.wristDownFront();
    }

    if (primaryController.getRightBumperReleased() == true) {
      intakeTransportSys.wristUpFront();
    }

    if (primaryController.getLeftBumperPressed() == true) {
      intakeTransportSys.wristDownBack();
    }

    if (primaryController.getLeftBumperReleased() == true) {
      intakeTransportSys.wristUpBack();
    }

    if (secondaryController.getPOV() == 0) {
      intakeTransportSys.topSolenoidUp();
    }

    if (secondaryController.getPOV() == 180) {
      intakeTransportSys.topSolenoidDown();
    }

  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
