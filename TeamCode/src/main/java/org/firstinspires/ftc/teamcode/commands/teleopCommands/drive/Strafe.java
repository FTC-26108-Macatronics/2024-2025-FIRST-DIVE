package org.firstinspires.ftc.teamcode.commands.teleopCommands.drive;

import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

public class Strafe extends DriveSubsystem {
    public void strafe(double pwr) {
        transverseDrive.setPower(pwr);
    }
}