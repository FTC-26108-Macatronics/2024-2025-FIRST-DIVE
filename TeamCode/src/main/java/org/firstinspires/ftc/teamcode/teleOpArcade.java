package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Constants.DriveConstants.strafeMultiplier;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.teleopCommands.arm.ArmMethods;
import org.firstinspires.ftc.teamcode.commands.teleopCommands.drive.DriveMethods;
import org.firstinspires.ftc.teamcode.initialize.Hardware;

@TeleOp
public class teleOpArcade extends OpMode {
    final private DriveMethods m_driveMethods = new DriveMethods();
    final private ArmMethods m_armMethods = new ArmMethods();
    public static OpMode opmode;

    double drive, turn, strafePwr;

    @Override
    public void init() {
        RobotContainer.m_Hardware.configureMotors();
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Running");

        drive = -gamepad1.left_stick_y;
        turn = gamepad1.right_stick_x;

        strafePwr = (gamepad1.dpad_left) ? (strafeMultiplier) : (gamepad1.dpad_right) ? (-strafeMultiplier) : (0);

        m_driveMethods.strafe(strafePwr);
        m_driveMethods.driveArcade(drive, turn);
        m_armMethods.armRotation(gamepad1.right_trigger, 90);

        telemetry.addData("D-Pad left", gamepad1.dpad_left);
        telemetry.addData("D-Pad right", gamepad1.dpad_right);
        telemetry.addData("Left joystick y", gamepad1.left_stick_y);
        telemetry.addData("Right joystick y", gamepad1.right_stick_y);
        telemetry.addData("Strafe power", strafePwr);
        telemetry.addData("Drive", drive);
        telemetry.addData("Turn", turn);
        telemetry.update();
    }
}
