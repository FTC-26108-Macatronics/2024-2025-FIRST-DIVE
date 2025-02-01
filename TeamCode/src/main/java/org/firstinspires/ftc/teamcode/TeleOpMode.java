package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TeleOpMode extends OpMode {
    RobotHardware robot = new RobotHardware(this);
    boolean clawState, lock;

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

        if (gamepad1.back) {
            lock = !lock;
        }

        double drive = -gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;
        double strafe = -gamepad1.left_stick_x;
        double arm = gamepad1.left_trigger - gamepad1.right_trigger;
        int liftState = 0, clawRState = 0;

        if (gamepad1.dpad_up && !gamepad1.dpad_down) {
            liftState = 2;
        } else if (gamepad1.dpad_down && !gamepad1.dpad_up) {
            liftState = 1;
        }

        if (gamepad1.y && !gamepad1.a) {
            clawRState = 2;
        } else if (gamepad1.a && !gamepad1.y) {
            clawRState = 1;
        }

        if (gamepad1.left_bumper && !gamepad1.right_bumper) {
            clawState = true;
        } else if (gamepad1.right_bumper && !gamepad1.left_bumper) {
            clawState = false;
        }



        robot.driveArcade(drive, turn);
        robot.strafe(strafe);
        robot.moveLift(liftState, override);

        //robot.rotateClaw(clawRState, override);

        if (lock){
            robot.lock();
            telemetry.addData("!LOCK", "ACTIVE!");
        } else {
            telemetry.addData("Claw error", robot.rotateClawPID(clawRState, override));

        }
        robot.moveArm(arm, override);
        telemetry.addData("Claw mode", robot.clawMotor.getMode());
        telemetry.addData("Claw target", robot.clawMotor.getTargetPosition());
        telemetry.addData("Claw power", robot.clawMotor.getPower());
        telemetry.update();
    }
}