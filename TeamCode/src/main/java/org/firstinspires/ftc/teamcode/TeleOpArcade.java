package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.initialize.Hardware;

@TeleOp
public class TeleOpArcade extends OpMode {
    double drive, turn, strafePwr, armRotation, clawTarget;
    int target;

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
        armRotation = gamepad1.left_trigger - gamepad1.right_trigger;

        hardware.strafe(strafePwr);
        hardware.driveArcade(drive, turn);

        target = hardware.rotateArm(armRotation);

        if (gamepad1.left_bumper) {
            clawTarget = hardware.moveClaw(true);
        }

        if (gamepad1.right_bumper) {
            clawTarget = hardware.moveClaw(false);
        }

        telemetry.addData("Drive", drive);
        telemetry.addData("Turn", turn);
        telemetry.addData("Strafe power", strafePwr);
        telemetry.addData("Arm rotation", armRotation);
        telemetry.addData("Arm position ", hardware.armPosition());
        telemetry.addData("Arm target", target);

        if (target >= hardware.ARM_MIN_POSITION && target <= hardware.ARM_MAX_POSITION) {
            telemetry.addData("Arm movable", true);
        } else {
            telemetry.addData("Arm movable", false);
        }

        telemetry.addData("Claw motion", gamepad1.left_bumper || gamepad1.right_bumper);
        telemetry.addData("Arm target", target);

        if (clawTarget >= hardware.CLAW_MIN_POSITION && clawTarget <= hardware.CLAW_MAX_POSITION) {
            telemetry.addData("Claw movable", true);
        } else {
            telemetry.addData("Claw movable", false);
        }

        telemetry.update();
    }
}