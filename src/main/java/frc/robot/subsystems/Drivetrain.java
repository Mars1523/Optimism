
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  private final WPI_TalonFX leftFront = new WPI_TalonFX(2);
  private final WPI_TalonFX rightRear = new WPI_TalonFX(3);
  private final WPI_TalonFX leftRear = new WPI_TalonFX(4);
  private final WPI_TalonFX rightFront = new WPI_TalonFX(5);
  // altitude something or other- measuers the position of the robot and the angls
  // and stuff
  public static final double kGearRatio = 10.71;
  // Meters (6 inch/2)
  public static final double kWheelRadius = 0.1524 / 2;
  public static final double kEncoderResolution = 2048;
  public static final double kDistancePerTick = (2 * Math.PI * kWheelRadius / kEncoderResolution) / kGearRatio;

  private final MotorControllerGroup leftMotors = new MotorControllerGroup(leftFront, leftRear);
  private final MotorControllerGroup rightMotors = new MotorControllerGroup(rightFront, rightRear);
  private final DifferentialDrive robotDrive = new DifferentialDrive(leftMotors, rightMotors);

  private final PIDController leftPIDController = new PIDController(1, 0, 0);
  private final PIDController rightPIDController = new PIDController(1, 0, 0);

  // measures the distance ur robotb traveled
  // private final DifferentialDriveOdometry driveOdometry = new
  // DifferentialDriveOdometry(
  // Rotation2d.fromDegrees(navx.getAngle()));

  private final DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(
      Constants.DriveConstants.kTrackWidth);

  private final SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(.138, 2.45, .265);

  public Drivetrain() {

    zeroSensors();

    leftFront.configFactoryDefault();
    rightRear.configFactoryDefault();
    leftRear.configFactoryDefault();
    rightFront.configFactoryDefault();

    leftFront.setNeutralMode(NeutralMode.Brake);
    rightRear.setNeutralMode(NeutralMode.Brake);
    leftRear.setNeutralMode(NeutralMode.Brake);
    rightFront.setNeutralMode(NeutralMode.Brake);

    rightMotors.setInverted(true);
    robotDrive.setSafetyEnabled(false);

  }

  /*
   * public void alarm() {
   * leftRear.set(ControlMode.MusicTone, x + 450);
   * leftFront.set(ControlMode.MusicTone, x + 450);
   * rightRear.set(ControlMode.MusicTone, x + 450);
   * rightFront.set(ControlMode.MusicTone, x + 450);
   * x = (x + 8) % 150;
   * }
   */

  // public double getAngle() {
  // return navx.getAngle();
  // }

  public void driveRaw(double leftY, double rightX) {
    robotDrive.arcadeDrive(-leftY, -rightX, false);
  }

  public void zeroSensors() {
    // driveOdometry.resetPosition(new Pose2d(0.0, 0.0, new Rotation2d()),
    // Rotation2d.fromDegrees(navx.getYaw()));
    // navx.zeroYaw();
    leftFront.setSelectedSensorPosition(0);
    rightFront.setSelectedSensorPosition(0);
    leftRear.setSelectedSensorPosition(0);
    rightRear.setSelectedSensorPosition(0);
  }

  public void reset() {
    leftFront.setSelectedSensorPosition(0);
    rightFront.setSelectedSensorPosition(0);

  }

  public double getDistance() {
    double leftMotor = leftFront.getSelectedSensorPosition() * kDistancePerTick;
    double rightMotor = -rightFront.getSelectedSensorPosition() * kDistancePerTick;

    double averageMove = (leftMotor + rightMotor) / 2;

    return -averageMove;

  }

  public void boringDrive(double xSpeed, double rotation) {
    robotDrive.arcadeDrive(xSpeed, rotation);
  }

  public void fancyDrive(double rot, double xSpeed) {
    var wheelSpeeds = kinematics.toWheelSpeeds(new ChassisSpeeds(xSpeed, 0.0, rot));
    setSpeeds(wheelSpeeds);
  }

  public double getLeftDistance() {
    return (leftFront.getSelectedSensorPosition() * Constants.DriveConstants.kDistancePerTick
        + leftRear.getSelectedSensorPosition() * Constants.DriveConstants.kDistancePerTick) / 2.0;
  }

  public double getRightDistance() {
    return -(rightFront.getSelectedSensorPosition() * Constants.DriveConstants.kDistancePerTick
        + rightRear.getSelectedSensorPosition() * Constants.DriveConstants.kDistancePerTick) / 2.0;
  }

  public double getAverageDistance() {
    return (getLeftDistance() + getRightDistance()) / 2.0;
  }

  public void setSpeeds(DifferentialDriveWheelSpeeds speeds) {
    final double leftFeedforward = feedforward.calculate(speeds.leftMetersPerSecond);
    final double rightFeedforward = feedforward.calculate(speeds.rightMetersPerSecond);

    final double leftOutput = leftPIDController.calculate(
        leftFront.getSelectedSensorVelocity() * Constants.DriveConstants.kDistancePerTick,
        speeds.leftMetersPerSecond);
    final double rightOutput = rightPIDController.calculate(
        rightFront.getSelectedSensorVelocity() * Constants.DriveConstants.kDistancePerTick,
        speeds.rightMetersPerSecond);
    leftMotors.setVoltage(leftOutput + leftFeedforward);
    rightMotors.setVoltage(-(rightOutput + rightFeedforward));
  }

  @Override
  public void periodic() {
    // driveOdometry.update(Rotation2d.fromDegrees(navx.getYaw()),
    // getLeftDistance(),
    // getRightDistance());
  }

  public double getEncoder() {
    return leftFront.getSelectedSensorPosition();
  }

}
