// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;

public class MoveToTarget extends CommandBase {
  /** Creates a new MoveToTarget. */
  private final PIDController movePID;
  private final PIDController turnPID;
  Limelight limeLight;
  Drivetrain driveTrain;
  LimeLightCalcDist limeLightCalcDist;
  SlewRateLimiter filter = new SlewRateLimiter(0.5);
  SlewRateLimiter filterTurn = new SlewRateLimiter(1);
  Debouncer m_debouncer = new Debouncer(0.3, Debouncer.DebounceType.kBoth);

  public MoveToTarget(Drivetrain drivetrain, Limelight limelight, XboxController primaryController) {
    // Use addRequirements() here to declare subsystem dependencies.
    movePID = new PIDController(0.6, 0.001, 0);
    movePID.setSetpoint(1.2);
    turnPID = new PIDController(0.05, 0, 0);
    turnPID.setSetpoint(0);
    addRequirements(drivetrain);
    addRequirements(limelight);
    this.driveTrain = drivetrain;
    this.limeLight = limelight;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (m_debouncer.calculate(limeLight.getTV())) {
      double output = movePID.calculate(limeLightCalcDist.getDist());
      double turnOutput = turnPID.calculate(limeLight.getX());
      filter.calculate(output);
      filterTurn.calculate(output);
      // drivetrain.driveRaw(, 0);

      System.out.println("turn" + turnOutput + " go " + output);
      driveTrain.driveRaw(MathUtil.clamp(output, -.2, .2), MathUtil.clamp(-turnOutput, -.2, .2));
    } else {
      driveTrain.driveRaw(0, 0);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return false;

  }
}
