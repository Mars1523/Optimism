
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.shuffleboard.ComplexWidget;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {

  private final Spark turretTurn = new Spark(1);
  private final CANSparkMax turretWheel = new CANSparkMax(12, MotorType.kBrushless);
  private final Spark vertTransport = new Spark(2);

  private final Encoder encoder = new Encoder(0, 1, true);
  private final PIDController pidC = new PIDController(0.00075, 0, 0);

  private final ComplexWidget turetAngleEntry = Shuffleboard.getTab("Debug").add("Turret Angle", encoder);
  private final ComplexWidget turetPidEntry = Shuffleboard.getTab("Debug").add("Turret PID", pidC);

  public Turret() {
    // pidC.setSetpoint(setpoint);
    pidC.disableContinuousInput();
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
    // turretTurn.set(speed);
  }

  public void setLift(double liftSpeed) {
    vertTransport.set(liftSpeed);
  }

  public double getTurretAngle() {
    return pidC.getSetpoint();
  }

  public void setTurretAngle(double numBer) {

    pidC.setSetpoint(MathUtil.clamp(numBer, -3600, 7000));
  }

  @Override
  public void periodic() {
    // System.out.println(encoder.get());

    // double pidOutput = pidC.calculate(encoder.get());
    // pidOutput *= .95;
    // turretTurn.set(pidOutput);
  }
}
