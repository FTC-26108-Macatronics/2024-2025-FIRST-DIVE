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

        if (gamepad1.left_bumper) {
            hardware.moveClaw(true);
            telemetry.addData("Claw", "Open");
        } else if (gamepad1.right_bumper) {
            hardware.moveClaw(false);
            telemetry.addData("Claw", "Closed");
        } else {
            telemetry.addData("Claw", "Break");
        }

        /*if (gamepad1.dpad_up) {
            telemetry.addData("Lift position", hardware.moveLift(2));
            telemetry.addData("Lift state", "Up");
        }
        else if (gamepad1.dpad_down) {
            telemetry.addData("Lift position", hardware.moveLift(1));
            telemetry.addData("Lift state", "Down");
        }
        else {
            telemetry.addData("Lift position", hardware.moveLift(0));
            telemetry.addData("Lift state", "Break");
        }*/

        if (gamepad1.dpad_up) {
            hardware.setLift(1.0);
        } else if (gamepad1.dpad_down) {
            hardware.setLift(-1.0);
        } else {
            hardware.setLift(0);
        }

        hardware.strafe(strafePwr);
        hardware.driveArcade(drive, turn);

        telemetry.addData("Drive", drive);
        telemetry.addData("Turn", turn);
        telemetry.addData("Strafe power", strafePwr);


        telemetry.update();
    }
}