//when x pressed -> arm moves out
//when y pressed -> arm moves in
//when x+y released -> arm stop moving

//should be finished

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arms;

public class DefaultArms extends CommandBase {

  private XboxController primaryController;
  private Arms armsSys;

  public DefaultArms(Arms armsSys, XboxController primaryController) {

    addRequirements(armsSys);
    this.primaryController = primaryController;
    this.armsSys = armsSys;

  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {

    if (primaryController.getXButtonPressed() == true) {
      armsSys.armOutStart();
    }
    if (primaryController.getXButtonReleased() == true) {
      armsSys.armStop();
    }
    if (primaryController.getYButtonPressed() == true) {
      armsSys.armInStart();
    }
    if (primaryController.getYButtonReleased() == true) {
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
