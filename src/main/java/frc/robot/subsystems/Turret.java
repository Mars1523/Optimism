
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

  private Limelight limelight;

  private final Spark turretTurn = new Spark(1);
  private final CANSparkMax turretWheel = new CANSparkMax(12, MotorType.kBrushless);
  private final Spark vertTransport = new Spark(2);

  private final Encoder encoder = new Encoder(0, 1, true);
  private final PIDController manuelPidC = new PIDController(0.00075, 0, 0);
  private final PIDController limelightPidC = new PIDController(0.00075, 0, 0);

  private final ComplexWidget turetAngleEntry = Shuffleboard.getTab("Debug").add("Turret Angle", encoder);
  private final ComplexWidget turetPidEntry = Shuffleboard.getTab("Debug").add("Turret PID", manuelPidC);

  public enum TurretPIDMode {
    limelightMode, manuelMode
  }

  private TurretPIDMode currentPIDMode = TurretPIDMode.manuelMode;

  public Turret() {
    // pidC.setSetpoint(setpoint);
    manuelPidC.disableContinuousInput();
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
    return manuelPidC.getSetpoint();
  }

  public void setTurretAngle(double numBer) {
    System.out.println(numBer);
    manuelPidC.setSetpoint(MathUtil.clamp(numBer, -3600, 7000));
  }

  public void setToLimelight() {
    currentPIDMode = TurretPIDMode.limelightMode;
  }

  public void setToManuel() {
    currentPIDMode = TurretPIDMode.manuelMode;
  }

  @Override
  public void periodic() {
    // System.out.println(encoder.get());

    // double manuelPidOutput = manuelPidC.calculate(encoder.get());
    // double limelightPidOutput = limelightPidC.calculate(limelight.getX());

    // if (currentPIDMode == TurretPIDMode.manuelMode) {
    // turretTurn.set(manuelPidOutput * 0.8);
    // } else if (currentPIDMode == TurretPIDMode.limelightMode) {
    // turretTurn.set(limelightPidOutput * 0.8);
    // }
  }
}
