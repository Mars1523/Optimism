// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Turret;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class FinalAuto extends SequentialCommandGroup {
  /** Creates a new FinalAuto. */
  public FinalAuto(Drivetrain drivetrain, Turret turret) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    // new Timeout

    addCommands(
        // new DriveForward(drivetrain, 1.3),
        new ParallelRaceGroup(new RunCommand(() -> {

          if (drivetrain.getDistance() < 1.3) {
            drivetrain.driveRaw(0.2, 0);
          } else if (drivetrain.getDistance() > 1.3) {
            drivetrain.driveRaw(0, 0);
            turret.shooterOn();
            if (turret.isReadyToShoot()) {
              // if (secondaryController.getStartButton()) {
              double liftSpeed = -0.8;
              turret.setLift(liftSpeed);
            } else {
              turret.setLift(0);
            }
          }
        }, turret)

        ));
  }
}
