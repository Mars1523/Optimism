//a button makes turret go left (toggle)
//b button makes turret go right (toggle)
//left and right bumpers togggle turret wheel 
//on and off respectively/src/main/java/frc/robot/commands/DefaultArms.java': Permission denied

//cant figure out joystick buttonbinding for turret turn??

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turret;

public class DefaultTurret extends CommandBase {

  private XboxController primaryController;
  private Turret turretSys;

  public DefaultTurret(XboxController primaryController, Turret turretSys) {

    addRequirements(turretSys);
    this.turretSys = turretSys;
    this.primaryController = primaryController;

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

    turretSys.setTurretAim(primaryController.getLeftY());

  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
