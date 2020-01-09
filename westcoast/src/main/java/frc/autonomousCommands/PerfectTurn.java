/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.autonomousCommands;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpiutil.math.MathUtil;

public class PerfectTurn extends CommandBase {
  private AHRS ahrs;
  private DifferentialDrive drive;
  private PIDController turn;
  private double angle;
  private double wait;
  private boolean end;

  public PerfectTurn(
    DifferentialDrive drive,
    AHRS ahrs,
    double angle
  ) {
    this.ahrs = ahrs;
    this.drive = drive;
    this.angle = angle;
  }

  @Override
  public void initialize() {
    wait = 0;

    turn = new PIDController(0.6, 0.15, 0.1);

    turn.reset();
    turn.setTolerance(1);
    turn.disableContinuousInput();
  }

  @Override
  public void execute() {
    double angleturned = MathUtil.clamp(turn.calculate(-angle+5, deadband(ahrs.getYaw(), 2)), -0.5, 0.5);

    drive.arcadeDrive(0, angleturned);

    if(turn.atSetpoint() == true){
      wait = wait + 1;
      if(wait >= 10){
        end = true;
      }
      else{
        end = false;
      }
    }
    else{
      end = false;
    }
  }

  @Override
  public void end(boolean interrupted) {
    turn.reset();
    drive.stopMotor();
  }

  @Override
  public boolean isFinished() {
    if(end){
      return true;
    }
    else{
      return false;
    }
  }

  private double deadband(double value, double deadzone){
    if(value<deadzone&&value>deadzone*(-1)){
      return 0;
    }
    else{
      return value;
    }
  }
}