package org.firstinspires.ftc.teamcode.commands.teleopCommands.drive;

import org.firstinspires.ftc.teamcode.initialize.Hardware;

public class Strafe extends Hardware {
    public void strafe(double pwr) {
        transverseDrive.setPower(pwr);
    }
}
