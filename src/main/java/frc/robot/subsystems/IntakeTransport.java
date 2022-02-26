
//PWMTalonSRX --> CANTalonSRX

//intake use joystick or toggle AGAIN ugh-
//yeah

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeTransport extends SubsystemBase {

  private final PWMTalonSRX intakeFront = new PWMTalonSRX(0);
  private final PWMTalonSRX intakeBack = new PWMTalonSRX(0);

  private final MotorControllerGroup inTake = new MotorControllerGroup(intakeFront, intakeBack);

  private final PWMTalonSRX transportOne = new PWMTalonSRX(0);
  private final PWMTalonSRX transportTwo = new PWMTalonSRX(0);

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
