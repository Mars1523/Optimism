
//second

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turret;

public class DefaultTurret extends CommandBase {

  private XboxController secondaryController;
  private Turret turretSys;
  private XboxController primaryController;

  public DefaultTurret(XboxController secondaryController, Turret turretSys, XboxController primaryController) {

    addRequirements(turretSys);
    this.turretSys = turretSys;
    this.secondaryController = secondaryController;
    this.primaryController = primaryController;

  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if (primaryController.getXButtonPressed() == true) {
      turretSys.shooterOn();
    }
    if (primaryController.getXButtonReleased() == true) {
      turretSys.shooterOff();
    }

    if (secondaryController.getXButtonPressed()) {
      turretSys.setTurretAngle(0);
    }

    if (primaryController.getAButtonPressed() == true) {
      turretSys.shooterBack();
    }

    if (primaryController.getAButtonReleased() == true) {
      turretSys.shooterOff();
    }

    double turretControl = secondaryController.getLeftX();
    System.out.println(secondaryController.getLeftX());
    if (Math.abs(turretControl) < .09) {
      turretControl = 0;
    }

    // turretSys.setTurretAim(secondaryController.getLeftX());
    turretSys.setTurretAngle(turretSys.getTurretAngle() + turretControl * 45);

    if (turretSys.getVelocity() < -3000) {
      // if (secondaryController.getStartButton()) {
      double liftSpeed = -0.6;
      turretSys.setLift(liftSpeed);
    } else {
      turretSys.setLift(0);
    }
    System.out.println(turretSys.getVelocity());
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
