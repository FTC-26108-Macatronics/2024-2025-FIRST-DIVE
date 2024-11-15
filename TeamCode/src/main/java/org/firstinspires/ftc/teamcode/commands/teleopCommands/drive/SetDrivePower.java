package org.firstinspires.ftc.teamcode.commands.teleopCommands.drive;


import static org.firstinspires.ftc.teamcode.Constants.DriveConstants.driveMultiplier;

import org.firstinspires.ftc.teamcode.initialize.Hardware;

public class SetDrivePower extends Hardware {

    public void setDrivePower(double leftWheel, double rightWheel) {

        leftWheel = ((Math.abs(leftWheel) / leftWheel) * (Math.pow(leftWheel * driveMultiplier, 2)));
        rightWheel = ((Math.abs(rightWheel) / rightWheel) * (Math.pow(rightWheel * driveMultiplier, 2)));

        leftDrive.setPower(leftWheel);
        rightDrive.setPower(rightWheel);
    }
}
