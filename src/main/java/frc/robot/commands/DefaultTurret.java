
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

  boolean rightTriggerActivated = false;

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if (primaryController.getXButtonPressed() == true) {
      turretSys.shooterLower();
    }

    if (primaryController.getXButtonReleased() == true) {
      turretSys.shooterOff();
    }

    if (primaryController.getRightTriggerAxis() > 0.15) {
      turretSys.shootLimeLight();
      rightTriggerActivated = true;
    } else if (rightTriggerActivated) {
      turretSys.shooterOff();
      rightTriggerActivated = false;
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

    if (secondaryController.getAButtonPressed()) {
      turretSys.setToLimelight();
    }

    if (primaryController.getBButtonPressed() == true) {
      System.out.println("B press");
      turretSys.shootLimeLight();
    }
    if (primaryController.getBButtonReleased()) {
      System.out.println("B release");

      turretSys.shooterOff();
    }

    if (primaryController.getAButtonReleased()) {
      turretSys.setToManuel();
    }

    double turretControl = secondaryController.getLeftX();
    // System.out.println(secondaryController.getLeftX());
    if (Math.abs(turretControl) < .09) {
      turretControl = 0;
    }

    // turretSys.setTurretAim(secondaryController.getLeftX());
    turretSys.setTurretAngle(turretSys.getTurretAngle() + turretControl * 77);

    if (turretSys.isReadyToShoot()) {
      // if (secondaryController.getStartButton()) {
      double liftSpeed = -0.8;
      turretSys.setLift(liftSpeed);
    } else {
      turretSys.setLift(0);
    }

    if (primaryController.getBackButton()) {
      turretSys.setLift(.8);
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
