//turret wheel + turn 
//PWMTalonSRX --> CANTalonSRX
//cant figure out joystick buttonbinding for turret turn??

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {

  private final PWMTalonSRX turretTurn = new PWMTalonSRX(0);
  private final CANSparkMax turretWheel = new CANSparkMax(0, MotorType.kBrushed);

  public Turret() {

  }
  /*
   * public void wheelToggleOn() {
   * turretWheel.set(1);
   * }
   * 
   * public void wheelToggleOff() {
   * turretWheel.set(0);
   * }
   * 
   * public void aimLeft() {
   * turretTurn.set(0.5);
   * }
   * 
   * public void turretStop() {
   * turretWheel.set(0);
   * }
   * 
   * public void aimRight() {
   * turretWheel.set(-0.5);
   * }
   */

  public void setTurretAim(double speed) {
    turretTurn.set(speed);
  }

  /*
   * public void DifferentialDrive(double x, double y){
   * turretTurn.arcadeDrive(x,y);
   * 
   * // analoginput from sparkmax documentation
   * //get pov?
   * }
   */

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
