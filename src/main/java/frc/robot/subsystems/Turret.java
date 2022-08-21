
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.opencv.core.Mat;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.filter.LinearFilter;
import edu.wpi.first.math.filter.SlewRateLimiter;
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
  private final PIDController manuelPidC = new PIDController(0.00086, 0, 0);
  private final PIDController limelightPidC = new PIDController(0.00086, 0, 0);

  private final ComplexWidget turetAngleEntry = Shuffleboard.getTab("Debug").add("Turret Angle", encoder);
  private final ComplexWidget turetPidEntry = Shuffleboard.getTab("Debug").add("Turret PID", manuelPidC);


  // if this doesn't work then delete this line below
  LinearFilter filter = LinearFilter.singlePoleIIR(0.1, 0.02);


  public enum TurretPIDMode {
    limelightMode, manuelMode
  }

  private TurretPIDMode currentPIDMode = TurretPIDMode.manuelMode;

  // private SparkMaxPIDController velocPID = turretWheel.getPIDController();
  private final SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(-0.061147, 0.13446, 0.019562);
  private final PIDController pid = new PIDController(1.2563E-06, 0, 0);

  private double pidSetpoint = 0;

  public Turret(Limelight limelight) {
    // pidC.setSetpoint(setpoint);
    manuelPidC.disableContinuousInput();

    turretWheel.restoreFactoryDefaults();
    turretWheel.setIdleMode(IdleMode.kCoast);
    this.limelight = limelight;
  }

  public TurretPIDMode getAimMode() {
    return currentPIDMode;
  }

  private double getVelocity() {
    return turretWheel.getEncoder().getVelocity();

  }

  public boolean isReadyToShoot() {
    double part1 = pidSetpoint - getVelocity();
    double part2 = Math.abs(part1);

    if (part2 < 100 && pidSetpoint != 0) {
      return true;
    } else {
      return false;
    }
  }

  public void shooterLower() {
    shooterOn(1500);
  }

  public void shooterHigh() {
    shooterOn(3500);
  }

  public void shooterOn(double setPoint) {
    // vertTransport.set(-0.8);
    // turretWheel.set(-0.6);
    pidSetpoint = -setPoint;
    System.out.println("ShootOn");
    // velocPID.setReference(pidSetpoint, ControlType.kVelocity);
  }

  public void shooterOff() {
    System.out.println("shootoff");
    vertTransport.set(0);
    // turretWheel.set(0);
    pidSetpoint = 0;
    // velocPID.setReference(pidSetpoint, ControlType.kVelocity);
  }

  public void shooterBack() {
    vertTransport.set(0.9);
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
    manuelPidC.setSetpoint(MathUtil.clamp(numBer, -3600, 3600));
  }

  public void shootLimeLight() {

    if (limelight.getTV() == false) {
      shooterOff();
      return;
    }

    double y = limelight.getY();

    // this should give better results when using limelight shooting, if not remove this line below and the 'yFiltered' variable
    double yFiltered = filter.calculate(y);

    double distance = 10.4 + (-0.466 * yFiltered) + (8.4 * Math.pow(10, -3) * Math.pow(yFiltered, 2));// 180 * Math.exp(-0.856) * area;

    double rpm = 15 * distance + 1750;
    rpm = -rpm;

    if (!Double.isFinite(rpm)) {
      return;
    }
    pidSetpoint = rpm;
    System.out.println(distance + " : " + rpm);
    System.out.println("shootLimelight");
    // velocPID.setReference(pidSetpoint, ControlType.kVelocity);
  }

  public void setToLimelight() {
    currentPIDMode = TurretPIDMode.limelightMode;
  }

  public void setToManuel() {
    currentPIDMode = TurretPIDMode.manuelMode;
  }

  @Override
  public void periodic() {

    double manuelPidOutput = manuelPidC.calculate(encoder.getDistance());

    // System.out.println(encoder.get());

    // double limelightPidOutput = limelightPidC.calculate(limelight.getX());

    // if (currentPIDMode == TurretPIDMode.manuelMode) {
    // System.out.println("manuel!!");
    turretTurn.set(manuelPidOutput * 0.85);
    // } else if (currentPIDMode == TurretPIDMode.limelightMode) {
    // // System.out.println("turret: " + limelightPidOutput);
    // turretTurn.set(MathUtil.clamp(-limelightPidOutput, -.5, .5));
    // }

    final double controlIn = pidSetpoint / 60;
    final double feedForwardResult = feedforward.calculate(controlIn);

    final double pidOut = pid.calculate(controlIn) + feedForwardResult;
    turretWheel.setVoltage(pidOut);
  }
}
