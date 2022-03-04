
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {

  private final Spark turretTurn = new Spark(1);
  private final CANSparkMax turretWheel = new CANSparkMax(12, MotorType.kBrushless);
  private final Spark vertTransport = new Spark(2);

  private final Encoder encoder = new Encoder(0, 1);
  private final PIDController pidC = new PIDController(0.0001, 0, 0);

  public Turret() {

  }

  public double getVelocity() {
    return turretWheel.getEncoder().getVelocity();

  }

  public void shooterOn() {
    // vertTransport.set(-0.8);
    turretWheel.set(-0.6);
  }

  public void shooterOff() {
    // vertTransport.set(0);
    turretWheel.set(0);
  }

  public void shooterBack() {
    vertTransport.set(0.5);
  }

  public void setTurretAim(double speed) {
    turretTurn.set(speed);
  }

  public void setLift(double liftSpeed) {
    vertTransport.set(liftSpeed);
  }

  public double getTurretAngle() {
    return pidC.getSetpoint();
  }

  public void setTurretAngle(double numBer) {
    pidC.setSetpoint(numBer);
  }

  @Override
  public void periodic() {

  }
}
