
package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeTransport;

public class DefaultPneumatics extends CommandBase {

  private XboxController primaryController;
  private IntakeTransport intakeTransportSys;

  public DefaultPneumatics(XboxController primaryController, IntakeTransport intakeTransportSys) {
    addRequirements(intakeTransportSys);
    this.primaryController = primaryController;
    this.intakeTransportSys = intakeTransportSys;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {

    if (primaryController.getXButtonPressed() == true) {
      intakeTransportSys.wristUpFront();
    }

    if (primaryController.getXButtonReleased() == true) {
      intakeTransportSys.wristDownFront();
    }

    if (primaryController.getYButtonPressed() == true) {
      intakeTransportSys.wristUpBack();
    }

    if (primaryController.getYButtonReleased() == true) {
      intakeTransportSys.wristDownBack();
    }

    if (primaryController.getPOV() == 0) {
      intakeTransportSys.topSolenoidUp();
    }

    if (primaryController.getPOV() == 180) {
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
