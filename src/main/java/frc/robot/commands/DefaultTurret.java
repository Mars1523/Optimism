
//second

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeTransport;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Turret.TurretPIDMode;

public class DefaultTurret extends CommandBase {

  private XboxController secondaryController;
  private Turret turretSys;
  private XboxController primaryController;
  private Limelight limelight;
  private IntakeTransport intakeTransport;

  public DefaultTurret(XboxController secondaryController, Turret turretSys, IntakeTransport intakeTransport,
      XboxController primaryController,
      Limelight limelight) {

    addRequirements(turretSys);
    this.turretSys = turretSys;
    this.secondaryController = secondaryController;
    this.primaryController = primaryController;
    this.intakeTransport = intakeTransport;
    this.limelight = limelight;
  }

  boolean rightTriggerActivated = false;
  boolean leftTriggerActivated = false;

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

    if (primaryController.getYButtonPressed() == true) {
      turretSys.shooterHigh();
    }

    if (primaryController.getYButtonReleased() == true) {
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

    if (primaryController.getLeftTriggerAxis() > 0.1) {
      turretSys.setToLimelight();
      leftTriggerActivated = true;
    } else if (leftTriggerActivated) {
      turretSys.setToManuel();
      leftTriggerActivated = false;
    }

    if (primaryController.getBButtonPressed() == true) {
      System.out.println("B press");
      turretSys.shootLimeLight();
    }
    if (primaryController.getBButtonReleased()) {
      System.out.println("B release");

      turretSys.shooterOff();
    }

    double turretControl = secondaryController.getLeftX();
    // System.out.println(secondaryController.getLeftX());
    if (Math.abs(turretControl) < .09) {
      turretControl = 0;
    }

    // turretSys.setTurretAim(secondaryController.getLeftX());
    if (turretSys.getAimMode() == TurretPIDMode.manuelMode) {
      turretSys.setTurretAngle(turretSys.getTurretAngle() + turretControl * 100);
    } else {
      turretSys.setTurretAngle(turretSys.getTurretAngle() + limelight.getX() * 2);
    }

    if (turretSys.isReadyToShoot()) {
      double liftSpeed = -0.8;
      turretSys.setLift(liftSpeed);
      intakeTransport.horizTransportOn();
    } else if (!(primaryController.getXButton() || primaryController.getYButton() || primaryController.getLeftBumper())) {
      turretSys.setLift(0);
      intakeTransport.horizTransportOff();
  }

    // if (primaryController.getAButton() == true) {
    //   turretSys.shooterBack();
    // }

if (primaryController.getAButton() == true) {
      turretSys.setTurretAngle(turretSys.getTurretAngle() + limelight.getX() * 2);
    }

    

    // if (primaryController.getAButtonReleased() == true) {
    //   turretSys.shooterOff();
    // }
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
