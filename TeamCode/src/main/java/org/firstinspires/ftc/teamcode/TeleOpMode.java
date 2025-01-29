package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TeleOpMode extends OpMode {
    double drive, turn, strafePwr;

    Hardware hardware = new Hardware(this);

    @Override
    public void init() {
        hardware.init();
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Running");

        drive = -gamepad1.left_stick_y;
        turn = gamepad1.right_stick_x;
        strafePwr = -gamepad1.left_stick_x;

        hardware.strafe(strafePwr);
        hardware.driveArcade(drive, turn);

        telemetry.addData("Drive", drive);
        telemetry.addData("Turn", turn);
        telemetry.addData("Strafe power", strafePwr);

        telemetry.addData("Servo position", hardware.getServoPosition());

        telemetry.update();
    }
}