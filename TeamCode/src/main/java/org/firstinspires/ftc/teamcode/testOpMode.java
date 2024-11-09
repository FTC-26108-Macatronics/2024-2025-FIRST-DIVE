package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.initialize.Hardware;

@TeleOp
public class testOpMode extends OpMode {
    @Override
    public void init() {
        init();
        double strafePwr,
                leftPwr,
                rightPwr;
    }

    public void loop() {
        telemetry.addData("Status", "Running");
        Hardware hardware = new Hardware(this);
        strafePwr;
        leftPwr = -gamepad1.left_stick_y;
        rightPwr = -gamepad1.right_stick_y;

        if (gamepad1.dpad_left) {
            strafePwr = 0.5;
        }
        else if (gamepad1.dpad_right) {
            strafePwr = -0.5;
        }
        else {
            strafePwr = 0;}

        hardware.strafe(strafePwr);
        hardware.setDrivePower(leftPwr, rightPwr);
        telemetry.addData("D-Pad left", gamepad1.dpad_left);
        telemetry.addData("D-Pad right", gamepad1.dpad_right);
        telemetry.addData("Left joystick y", gamepad1.left_stick_y);
        telemetry.addData("Right joystick y", gamepad1.right_stick_y);
        telemetry.addData("Strafe power", strafePwr);
        telemetry.addData("Left power", leftPwr);
        telemetry.addData("Right power", rightPwr);
        telemetry.update();
    }
}
