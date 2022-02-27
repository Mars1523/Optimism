
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
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

  private final DoubleSolenoid frontSolenoid;
  private final DoubleSolenoid backSolenoid;
  private final DoubleSolenoid topSolenoid;

  public IntakeTransport(PneumaticsControlModule pCM) {
    this.pCM = pCM;
    backSolenoid = pCM.makeDoubleSolenoid(1, 0); // down/foward, up/reverse
    frontSolenoid = pCM.makeDoubleSolenoid(2, 3);
    topSolenoid = pCM.makeDoubleSolenoid(5, 4);

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
    frontSolenoid.set(Value.kForward);
  }

  public void wristDownFront() {
    frontSolenoid.set(Value.kReverse);
  }

  public void wristUpBack() {
    frontSolenoid.set(Value.kForward);
  }

  public void wristDownBack() {
    frontSolenoid.set(Value.kReverse);
  }

  @Override
  public void periodic() {

  }
}
