//when x pressed -> arm moves out
//when y pressed -> arm moves in
//when x+y released -> arm stop moving

//second

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arms;

public class DefaultArms extends CommandBase {

  private XboxController secondaryController;
  private Arms armsSys;

  public DefaultArms(Arms armsSys, XboxController secondaryController) {

    addRequirements(armsSys);
    this.secondaryController = secondaryController;
    this.armsSys = armsSys;

  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {

    if (secondaryController.getRightTriggerAxis() == 1) {
      armsSys.armOutStart();
    }
    if (secondaryController.getRightTriggerAxis() == 0) {
      armsSys.armStop();
    }
    if (secondaryController.getLeftTriggerAxis() == 1) {
      armsSys.armInStart();
    }
    if (secondaryController.getLeftTriggerAxis() == 0) {
      armsSys.armStop();
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
