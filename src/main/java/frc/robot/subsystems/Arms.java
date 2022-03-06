//toggles!!!

//done 

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arms extends SubsystemBase {

  private final CANSparkMax leftArm = new CANSparkMax(6, MotorType.kBrushless);
  private final CANSparkMax rightArm = new CANSparkMax(7, MotorType.kBrushless);

  private final MotorControllerGroup armS = new MotorControllerGroup(leftArm, rightArm);

  public Arms() {

  }

  public void armOutStart() {
    armS.set(-0.8);
  }

  public void armStop() {
    armS.set(0);
  }

  public void armInStart() {
    armS.set(0.5);
  }

  @Override
  public void periodic() {
    leftArm.setInverted(true);
  }

}
