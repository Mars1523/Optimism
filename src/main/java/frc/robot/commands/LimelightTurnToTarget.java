
package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;

public class LimelightTurnToTarget extends CommandBase {

  private XboxController primaryController;
  private Limelight limelight;

  public LimelightTurnToTarget(XboxController primaryController, Limelight limelight) {

    addRequirements(limelight);
    this.primaryController = primaryController;
    this.limelight = limelight;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
