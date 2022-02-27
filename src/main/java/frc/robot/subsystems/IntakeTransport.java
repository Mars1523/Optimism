
//PWMTalonSRX --> CANTalonSRX

//intake use joystick or toggle AGAIN ugh-
//yeah

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeTransport extends SubsystemBase {

  private final Spark intakeFront = new Spark(8);
  private final Spark intakeBack = new Spark(9);

  private final MotorControllerGroup inTake = new MotorControllerGroup(intakeFront, intakeBack);

  private final Spark transportOne = new Spark(10);
  private final Spark transportTwo = new Spark(11);

  private final MotorControllerGroup intakeTransport = new MotorControllerGroup(transportOne, transportTwo);

  public IntakeTransport() {
  }

  public void transportToggleOn() {
    intakeTransport.set(1);
  }

  public void transportToggleOff() {
    intakeTransport.set(0);
  }

  @Override
  public void periodic() {

  }
}
