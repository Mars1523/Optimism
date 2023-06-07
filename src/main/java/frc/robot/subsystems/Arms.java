// documented
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arms extends SubsystemBase {

  // make variables for left and right arm motors
  private final CANSparkMax leftArm = new CANSparkMax(6, MotorType.kBrushless);
  private final CANSparkMax rightArm = new CANSparkMax(7, MotorType.kBrushless);

  // groups arm motors so that they move in the same way at the same time
  private final MotorControllerGroup armS = new MotorControllerGroup(leftArm, rightArm);

  // empty method, i dont think its important
  public Arms() {

  }

  // method that sets the motor output to -0.8, so the motors unwind the string
  public void armOutStart() {
    armS.set(-0.8);
  }

  // sets the motor output to 0, stopping it 
  public void armStop() {
    armS.set(0);
  }

  // method that sets the motor output to 0.5, so the motors rewind the string 
  public void armInStart() {
    armS.set(0.5);
  }

  @Override
  public void periodic() {
    // the left arm motor moved in the opposite way from the right arm motor, so 
    // we had to invert one of them to make them match
    leftArm.setInverted(true);
  }

}
