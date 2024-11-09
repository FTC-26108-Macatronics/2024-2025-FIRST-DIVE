package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.initialize.Hardware;

@TeleOp
public class teleOpArcade extends OpMode {
    Hardware hardware = new Hardware(this);

    double drive, turn, strafePwr;

    @Override
    public void init() {
        hardware.init();
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Running");

        drive = -gamepad1.left_stick_y;
        turn = gamepad1.right_stick_x;

        if (gamepad1.dpad_left) {
            strafePwr = 0.5;
        }
        else if (gamepad1.dpad_right) {
            strafePwr = -0.5;
        }
        else {
            strafePwr = 0;}

        hardware.strafe(strafePwr);
        hardware.driveArcade(drive, turn);

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
