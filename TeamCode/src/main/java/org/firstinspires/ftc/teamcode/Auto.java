package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.commands.auto.drive.DriveToSetpoint;
import org.firstinspires.ftc.teamcode.commands.teleop.elevator.MoveElevatorUp;

@Autonomous
public class Auto extends OpMode {



    @Override
    public void init()  {
    }

    @Override
    public void loop() {
        Robot.commandScheduler.schedule(true, new SequentialCommandGroup(
                new ParallelCommandGroup(
                    new DriveToSetpoint(5, 5),
                    new MoveElevatorUp(Robot.m_elevatorSubsystem, Robot.m_driverController))
                )
        );
    }

    @Override
    public void stop() {
        Robot.commandScheduler.cancelAll();
    }
}
