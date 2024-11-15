package org.firstinspires.ftc.teamcode.commands.teleopCommands.drive;

import org.firstinspires.ftc.teamcode.initialize.Hardware;

public class SetDrivePower extends Hardware {

    public void setDrivePower(double leftWheel, double rightWheel) {
        leftDrive.setPower(leftWheel);
        rightDrive.setPower(rightWheel);
    }
}
