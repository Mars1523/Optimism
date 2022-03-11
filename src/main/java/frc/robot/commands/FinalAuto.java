// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IntakeTransport;
import frc.robot.subsystems.Turret;

public class FinalAuto extends CommandBase {

  Drivetrain drivetrain;
  DriveForward driveForward;
  Turret turret;
  IntakeTransport intake;

  /** Creates a new FinalAuto2. */
  public FinalAuto(DriveForward driveForward, Drivetrain drivetrain, Turret turret, IntakeTransport intake) {

    addRequirements(drivetrain);
    this.drivetrain = drivetrain;
    this.driveForward = driveForward;
    this.turret = turret;
    this.intake = intake;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    new SequentialCommandGroup(
        new DriveForward(drivetrain, 1),
        new ParallelCommandGroup(
            new IntakeAutoOn(intake).withTimeout(0.1),
            new AutoLimelightShoot(turret, false).withTimeout(3.5),
            new DriveForward(drivetrain, 0.7)),
        new IntakeAutoOff(intake).withTimeout(0.1),
        new AutoShoot(turret, true).withTimeout(0.1)).schedule();

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
