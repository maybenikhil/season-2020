package frc.commands.auto.demo;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.commands.ballhandling.*;
import frc.maps.*;
import frc.subsystems.vision.*;
import frc.subsystems.vision.targeting.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.commands.auto.*;

public class FlexAuto extends SequentialCommandGroup {

  public FlexAuto(
    RobotMap map,
    DifferentialDrive drive,
    Encoder leftEncoder,
    Encoder rightEncoder,
    VisionRunner vision
  ) {

    super(
      new LeaveLine(map, drive, leftEncoder, rightEncoder, -1),
      new WaitCommand(0),
      new PerfectTurn(map, drive, leftEncoder, rightEncoder, 180),
      new WaitCommand(0),
      new LeaveLine(map, drive, leftEncoder, rightEncoder, 9),
      new WaitCommand(0),
      new PerfectTurn(map, drive, leftEncoder, rightEncoder, 180),
      new WaitCommand(0),
      new AlignToTarget(map, drive, vision),
      new ShooterLogic()
    );
  }
}