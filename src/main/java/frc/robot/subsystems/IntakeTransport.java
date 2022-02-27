
//PWMTalonSRX --> CANTalonSRX

//intake use joystick or toggle AGAIN ugh-
//yeah

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeTransport extends SubsystemBase {

  private final Spark intakeFront = new Spark(0);
  private final Spark intakeBack = new Spark(5);

  private final MotorControllerGroup inTake = new MotorControllerGroup(intakeFront, intakeBack);

  private final Spark vertTransport = new Spark(2);
  private final Spark horizTransport = new Spark(4);

  private final MotorControllerGroup intakeTransport = new MotorControllerGroup(vertTransport, horizTransport);

  private PneumaticsControlModule pCM;

  public IntakeTransport(PneumaticsControlModule pCM) {
    this.pCM = pCM;
  }

  public void transportToggleOn() {
    intakeTransport.set(1);
    inTake.set(1);
  }

  public void transportToggleOff() {
    intakeTransport.set(0);
    inTake.set(0);
  }

  public void wristUpFront() {

  }

  public void wristDownFront() {

  }

  public void wristUpBack() {

  }

  public void wristDownBack() {

  }

  @Override
  public void periodic() {

  }
}
