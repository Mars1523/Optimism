// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Turret;

public class AutoLimelightShoot extends CommandBase {
  private Turret turretSys;
  boolean turnOff = false;
  private Limelight limelight;

  /** Creates a new AutoShoot. */

  public AutoLimelightShoot(Turret turretSys, Limelight limelight, boolean turnOff) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(turretSys);
    this.turretSys = turretSys;
    this.limelight = limelight;
    this.turnOff = turnOff;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (turnOff == true) {
      turretSys.shooterOff();
      return;
    }

    turretSys.setTurretAngle(turretSys.getTurretAngle() + limelight.getX() * 2);

    turretSys.shootLimeLight();

    if (turretSys.isReadyToShoot()) {
      // if (secondaryController.getStartButton())
      double liftSpeed = -0.8;
      turretSys.setLift(liftSpeed);

    } else {
      turretSys.setLift(0);
    }
  }

  public void turnOffShooter() {
    turretSys.shooterOff();
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
