package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TeleOpMode extends OpMode {
    RobotHardware robot = new RobotHardware(this);

    @Override
    public void init() {
        robot.init();
    }

    @Override
    public void loop() {
        telemetry.addData(">", "OpMode active");

        boolean override = false;
        if (gamepad1.options) {
            override = true;
            telemetry.addData("!OVERRIDE", "ACTIVE!");
        }

        double drive = -gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;
        double strafe = -gamepad1.left_stick_x;

        if (gamepad1.left_bumper) {
            robot.moveClaw(true);
            telemetry.addData("Claw", "Open");
        } else if (gamepad1.right_bumper) {
            robot.moveClaw(false);
            telemetry.addData("Claw", "Closed");
        } else {
            telemetry.addData("Claw", "Break");
        }

        if (gamepad1.dpad_up && !gamepad1.dpad_down) {
            telemetry.addData("Lift pwr", robot.moveLift(2, override));
        } else if (gamepad1.dpad_down && !gamepad1.dpad_up) {
            telemetry.addData("Lift pwr", robot.moveLift(1, override));
        } else {
            telemetry.addData("Lift pwr", robot.moveLift(0, override));
        }

        robot.strafe(strafe);
        robot.driveArcade(drive, turn);

        telemetry.addData("Drive", drive);
        telemetry.addData("Turn", turn);
        telemetry.addData("Strafe power", strafe);
        telemetry.addData("Lift", robot.getLift());
        telemetry.update();
    }
}