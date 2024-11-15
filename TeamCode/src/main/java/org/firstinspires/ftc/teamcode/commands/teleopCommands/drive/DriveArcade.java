package org.firstinspires.ftc.teamcode.commands.teleopCommands.drive;

import org.firstinspires.ftc.teamcode.initialize.Hardware;
import org.firstinspires.ftc.teamcode.commands.teleopCommands.drive.SetDrivePower;

public class DriveArcade extends Hardware {

    private SetDrivePower m_setDrivePower;

    private double leftPwr,
                    rightPwr,
                    max;
    public void driveArcade(double drive, double turn) {
        m_setDrivePower = new SetDrivePower();

        leftPwr = drive + turn;
        rightPwr = drive - turn;
        max = Math.max(Math.abs(leftPwr), Math.abs(rightPwr));

        if (max > 1.0) {
            leftPwr /= max;
            rightPwr /= max;
        }

        m_setDrivePower.setDrivePower(leftPwr, rightPwr);
    }
}
