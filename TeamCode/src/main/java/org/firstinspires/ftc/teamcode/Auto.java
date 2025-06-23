package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous
public class Auto extends OpMode {



    @Override
    public void init()  {

    }

    @Override
    public void loop() {
        Robot.commandScheduler.schedule(true, new SequentialCommandGroup(
                    new InstantCommand(() -> Robot.m_driveSubsystem.drive(5, 5))
        ));
    }

    @Override
    public void stop() {
        Robot.commandScheduler.cancelAll();
    }
}
