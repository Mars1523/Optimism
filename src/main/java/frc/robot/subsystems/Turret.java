
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
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

  private SparkMaxPIDController velocPID = turretWheel.getPIDController();

  private double pidSetpoint = 0;

  public Turret(Limelight limelight) {
    // pidC.setSetpoint(setpoint);
    manuelPidC.disableContinuousInput();

    turretWheel.restoreFactoryDefaults();
    velocPID.setP(0.00024);
    velocPID.setI(0);
    velocPID.setD(0);
    velocPID.setFF(0.00018);
    velocPID.setOutputRange(-0.85, 0.2);
    this.limelight = limelight;

  }

  private double getVelocity() {
    return turretWheel.getEncoder().getVelocity();

  }

  public boolean isReadyToShoot() {

    double part1 = pidSetpoint - getVelocity();
    double part2 = Math.abs(part1);

    if (part2 < 400 && pidSetpoint != 0) {
      return true;
    } else {
      return false;
    }
  }

  public void shooterOn(double setPoint) {
    // vertTransport.set(-0.8);
    // turretWheel.set(-0.6);
    pidSetpoint = -setPoint;
    velocPID.setReference(pidSetpoint, ControlType.kVelocity);
  }

  public void shooterOff() {
    vertTransport.set(0);
    // turretWheel.set(0);
    pidSetpoint = 0;
    velocPID.setReference(pidSetpoint, ControlType.kVelocity);
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

  public void shootLimeLight() {

    if (limelight.getTV() == false) {
      shootLimeLightOff();
      return;
    }

    double area = limelight.getArea();

    double distance = 3.87 * Math.exp(-0.0184) * area;
    double rpm = 9.23 * distance + 1674;
    rpm = -rpm;
    pidSetpoint = rpm;
    System.out.println(rpm);
    velocPID.setReference(rpm, ControlType.kVelocity);
  }

  public void shootLimeLightOff() {
    velocPID.setReference(0, ControlType.kVelocity);
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
