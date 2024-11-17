package org.firstinspires.ftc.teamcode.commands.teleopCommands.drive;

import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

public class DriveArcade extends DriveSubsystem {
    public void driveArcade(double drive, double turn) {
        SetDrivePower m_setDrivePower = new SetDrivePower();

        double leftPwr = drive + turn;
        double rightPwr = drive - turn;
        double max = Math.max(Math.abs(leftPwr), Math.abs(rightPwr));

        if (max > 1.0) {
            leftPwr /= max;
            rightPwr /= max;
        }

        m_setDrivePower.setDrivePower(leftPwr, rightPwr);
    }
}