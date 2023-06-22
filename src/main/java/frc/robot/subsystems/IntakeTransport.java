// documented
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeTransport extends SubsystemBase {

  // 19+20 were for the spinny bars at the bottom of the intake 
  // 22+23 grouped these motors so they would move in the same way at the same time
  // 19+22+23 are commented because someebody forgot to check the weight limit so 
  // we had to remove the second intake :(
  
  // private final Spark intakeFront = new Spark(0);
  private final Spark intakeBack = new Spark(0);

  // private final MotorControllerGroup inTake = new
  // MotorControllerGroup(intakeFront, intakeBack);

  private final Spark horizTransport = new Spark(4);

  // this is for the pcm, which controls all solenoid movement (pneumatics equivalent of a motorcontroller)
  private PneumaticsControlModule pCM;

  // solenoids for extending/retracting the front+back intakes and the top flap
  // (top flap got torn off too, it never worked idk why)
  private final DoubleSolenoid frontSolenoid;
  private final DoubleSolenoid backSolenoid;
  private final DoubleSolenoid topSolenoid;

  public IntakeTransport(PneumaticsControlModule pCM) {

    // java things- "the pcm in this method is the same pcm as the other one"
    this.pCM = pCM;

    // these establish that the solenoids are controlled by the pcm, and which way is up/down
    // the numbers coincide with what port the solenoids are plugged into
    backSolenoid = pCM.makeDoubleSolenoid(1, 0); // down/foward, up/reverse
    frontSolenoid = pCM.makeDoubleSolenoid(2, 3);
    topSolenoid = pCM.makeDoubleSolenoid(5, 4);

    // these set the default values/starting position
    backSolenoid.set(Value.kReverse);
    frontSolenoid.set(Value.kReverse);
    topSolenoid.set(Value.kForward);

  }
 
  // HOUSED IN INTAKE TRANSPORT (methods below were called in intakestransport command)

  // methods for the old front intake </3 rip front intake
  /*
   * public void wristDownFront() {
   * frontSolenoid.set(Value.kForward);
   * intakeFront.set(0.5);
   * horizTransport.set(1);
   * }
   * 
   * public void wristUpFront() {
   * frontSolenoid.set(Value.kReverse);
   * intakeFront.set(0);
   * horizTransport.set(0);
   * }
   */

  // activates solenoid (intake goes down), sets roller output to 1 (roller spins, pulling in the ball),
  // and sets horizontal transport output to 0.5 (internal wheels pull in the ball)
  public void wristDownBack() {
    frontSolenoid.set(Value.kForward);
    intakeBack.set(1);
    horizTransport.set(0.5);
  }

  // just sets the horizontal transport output to 0.5
  public void horizTransportOn() {
    horizTransport.set(0.5);
  }

  // turns of horizontal transport motor
  public void horizTransportOff() {
    horizTransport.set(0);
  }

  // pulls the intake back up, turns off all intake motors
  public void wristUpBack() {
    frontSolenoid.set(Value.kReverse);
    intakeBack.set(0);
    horizTransport.set(0);
  }

  // HOUSED IN PNEUMATICS (methods below are called in the pneumatics command)

  // these actually dont do anything because there is no top solenoid anymore rip
  public void topSolenoidDown() {
    topSolenoid.set(Value.kForward);
  }

  public void topSolenoidUp() {
    topSolenoid.set(Value.kReverse);
  }

  @Override
  public void periodic() {

  }
}
