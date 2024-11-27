package org.firstinspires.ftc.teamcode.commands.teleopCommands.drive;
import static org.firstinspires.ftc.teamcode.Constants.DriveConstants.driveMultiplier;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.initialize.Hardware;
import org.firstinspires.ftc.teamcode.RobotContainer;
public class DriveMethods {

    private double leftPwr, rightPwr, max;
    public DriveMethods() {}
    public void setDrivePower(double leftWheel, double rightWheel) {

        // Adding an exponential value to the inputs allows for a finer control over the motors.
        // This is especially important for grip as the wheels will spin out if 100% of power is initially applied (linear vs quadratic when exponent is 2)
        // The exponential value is recommended to be 2, but can also be larger numbers if necessary.
        // The (Math.abs(leftWheel) / leftWheel) section of the code is to ensure the negative inputs from the controllers aren't changed to positive by the exponent.

        // Drive multiplier should always be set under 1 to prevent unnecessary strain on the motors (this will reduce speed, however. change accordingly.

        leftWheel = ((Math.abs(leftWheel) / leftWheel) * (Math.pow(leftWheel * driveMultiplier, 2)));
        rightWheel = ((Math.abs(rightWheel) / rightWheel) * (Math.pow(rightWheel * driveMultiplier, 2)));

        RobotContainer.m_Hardware.setPowerLeft(leftWheel);
        RobotContainer.m_Hardware.setPowerRight(rightWheel);
    }
    public void strafe(double pwr) {
        RobotContainer.m_Hardware.setPowerTransverse(pwr);
    }

    public void driveArcade(double drive, double turn) {

        leftPwr = drive + turn;
        rightPwr = drive - turn;
        max = Math.max(Math.abs(leftPwr), Math.abs(rightPwr));

        if (max > 1.0) {
            leftPwr /= max;
            rightPwr /= max;
        }

        setDrivePower(leftPwr, rightPwr);
    }

}
