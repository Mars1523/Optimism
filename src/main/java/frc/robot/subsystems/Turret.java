
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {

  private final Spark turretTurn = new Spark(1);
  private final CANSparkMax turretWheel = new CANSparkMax(12, MotorType.kBrushless);
  private final Spark vertTransport = new Spark(2);

  public Turret() {

  }

  public void shooterOn() {
    vertTransport.set(1);
  }

  public void shooterOff() {
    vertTransport.set(0);
  }

  public void setTurretAim(double speed) {
    turretTurn.set(speed);
  }

  @Override
  public void periodic() {

  }
}
