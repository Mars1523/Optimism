// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IntakeTransport;
import frc.robot.subsystems.Turret;

public class FinalAuto2 extends CommandBase {

  Drivetrain drivetrain;
  DriveForward driveForward;
  Turret turret;
  IntakeTransport intake;

  /** Creates a new FinalAuto2. */
  public FinalAuto2(DriveForward driveForward, Drivetrain drivetrain, Turret turret, IntakeTransport intake) {

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
        new DriveForward(drivetrain, 1.3),
        new AutoHorizOn(intake).withTimeout(.5),
        new AutoHorizOff(intake).withTimeout(0),
        new AutoShoot(turret, false).withTimeout(4),
        new AutoShoot(turret, true)).schedule();

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
