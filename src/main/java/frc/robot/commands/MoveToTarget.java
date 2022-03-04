// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.commands;

// import edu.wpi.first.math.MathUtil;
// import edu.wpi.first.math.controller.PIDController;
// import edu.wpi.first.math.filter.Debouncer;
// import edu.wpi.first.math.filter.SlewRateLimiter;
// import edu.wpi.first.wpilibj.DigitalInput;
// import edu.wpi.first.wpilibj.XboxController;
// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.subsystems.Drivetrain;
// import edu.wpi.first.wpilibj2.command.button.JoystickButton;
// import frc.robot.subsystems.Limelight;

// public class MoveToTarget extends CommandBase {
// /** Creates a new MoveToTarget. */

// Limelight limeLight;
// Drivetrain driveTrain;

// boolean isFinishedButton = false;

// public MoveToTarget(Drivetrain drivetrain, Limelight limelight) {
// // Use addRequirements() here to declare subsystem dependencies.

// }

// // Called when the command is initially scheduled.
// @Override
// public void initialize() {

// }

// // Called every time the scheduler runs while the command is scheduled.
// @Override
// public void execute() {

// if (m_debouncer.calculate(limeLight.getTV())) {

// }
// }

// public void yesFinished() {
// isFinishedButton = true;
// }

// public void noFinished() {
// isFinishedButton = false;
// }

// // Called once the command ends or is interrupted.
// @Override
// public void end(boolean interrupted) {

// }

// // Returns true when the command should end.
// @Override
// public boolean isFinished() {

// return false;

// }
// }
