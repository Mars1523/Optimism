// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;

public class LimeLightCalcDist extends CommandBase {
  /** Creates a new LimeLightShoot. */
  Limelight limeLight;
  MoveToTarget moveToTarget;
  double limelightMountAngleDegrees = 20.0;
  double limelightLensHeightInches = 15.3543;
  double goalHeightInches = 104;

  private XboxController primaryController;

  public LimeLightCalcDist(XboxController primaryController, Limelight limeLight) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(limeLight);
    this.primaryController = primaryController;
    this.limeLight = limeLight;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (primaryController.getRightTriggerAxis() > 0.2) {
      // moveToTarget.execute();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  public double getDist() {
    // distanceFromLimelightToGoalInches = (goalHeightInches -
    // limelightLensHeightInches)
    // / Math.tan(angleToGoalRadians);
    return getDist();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
