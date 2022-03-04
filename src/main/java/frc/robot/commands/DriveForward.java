// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveForward extends CommandBase {
  private Drivetrain drivetrain;
  double distance = 0;
  public Command schedule;

  /** Creates a new DriveForward. */
  public DriveForward(Drivetrain driveTrain, double distance) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
    this.drivetrain = driveTrain;
    this.distance = distance;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.zeroSensors();
  }

  public double setDistance(double distance) {
    return distance;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // System.out.println("DriveForward execute: " + drivetrain.getDistance());
    if (drivetrain.getDistance() < distance) {
      drivetrain.driveRaw(0.2, 0);

    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (drivetrain.getDistance() > distance) {
      return true;
    } else {
      return false;
    }
  }
}
