
package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeTransport;

public class DefaultIntakeTransport extends CommandBase {

  private XboxController primaryController;
  private IntakeTransport intakeTransportSys;

  public DefaultIntakeTransport() {

    addRequirements(intakeTransportSys);
    this.primaryController = primaryController;
    this.intakeTransportSys = intakeTransportSys;

  }

  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

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
