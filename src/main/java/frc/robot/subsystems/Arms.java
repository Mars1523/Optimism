//toggles!!!

//done 

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arms extends SubsystemBase {

  private final CANSparkMax leftArm = new CANSparkMax(0, MotorType.kBrushless);
  private final CANSparkMax rightArm = new CANSparkMax(0, MotorType.kBrushless);

  private final MotorControllerGroup armS = new MotorControllerGroup(leftArm, rightArm);

  public Arms() {

  }

  public void armOutStart() {
    armS.set(0.1);
  }

  public void armStop() {
    armS.set(0);
  }

  public void armInStart() {
    armS.set(-0.1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
