package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.commands.auto.drive.DriveToSetpoint;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

@Autonomous
public class AutoOpMode extends OpMode {


    @Override
    public void init()  {
    }

    @Override
    public void loop() {
        RobotContainer.commandScheduler.schedule(true, new SequentialCommandGroup(
                new DriveToSetpoint(5, 5)));
    }

    @Override
    public void stop() {
        RobotContainer.commandScheduler.cancelAll();
    }
}
