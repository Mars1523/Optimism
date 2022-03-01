
//second

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turret;

public class DefaultTurret extends CommandBase {

  private XboxController secondaryController;
  private Turret turretSys;

  public DefaultTurret(XboxController secondaryController, Turret turretSys) {

    addRequirements(turretSys);
    this.turretSys = turretSys;
    this.secondaryController = secondaryController;

  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    /*
     * if (primaryController.getLeftBumperPressed() == true) {
     * turretSys.wheelToggleOn();
     * }
     * 
     * if (primaryController.getRightBumperPressed() == true) {
     * turretSys.wheelToggleOff();
     * }
     * 
     * if (primaryController.getAButtonPressed() == true) {
     * turretSys.aimLeft();
     * }
     * 
     * if (primaryController.getAButtonReleased() == true) {
     * turretSys.turretStop();
     * }
     * 
     * if (primaryController.getBButtonPressed() == true) {
     * turretSys.aimRight();
     * }
     * 
     * if (primaryController.getBButtonReleased() == true) {
     * turretSys.turretStop();
     * }
     */

    turretSys.setTurretAim(secondaryController.getLeftX());

  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
