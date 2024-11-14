package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.initialize.Hardware;

@TeleOp
public class TeleOpArcade extends OpMode {
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
        strafePwr = gamepad1.left_stick_x;

        hardware.strafe(strafePwr);
        hardware.driveArcade(drive, turn);

        telemetry.addData("Left joystick x", gamepad1.left_stick_x);
        telemetry.addData("Left joystick y", gamepad1.left_stick_y);
        telemetry.addData("Right joystick x", gamepad1.right_stick_x);
        telemetry.addData("Drive", drive);
        telemetry.addData("Turn", turn);
        telemetry.addData("Strafe power", strafePwr);
        telemetry.update();
    }
}