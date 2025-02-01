package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp
public class TeleOpMode extends OpMode {
    RobotHardware robot = new RobotHardware(this);
    boolean clawState;
    FtcDashboard dashboard;
    Telemetry dashboardTelemetry;

    double clawError = 0, armError = 0;

    @Override
    public void init() {
        robot.init();
        dashboard = FtcDashboard.getInstance();
        dashboardTelemetry = dashboard.getTelemetry();
    }

    @Override
    public void loop() {

        dashboardTelemetry.addData("Claw KP", RobotConstants.K_P_CLAW);
        dashboardTelemetry.addData("Claw KI", RobotConstants.K_I_CLAW);
        dashboardTelemetry.addData("Claw KD", RobotConstants.K_D_CLAW);

        dashboardTelemetry.addData("Arm KP", RobotConstants.K_P_ARM);
        dashboardTelemetry.addData("Arm KI", RobotConstants.K_I_ARM);
        dashboardTelemetry.addData("Arm KD", RobotConstants.K_D_ARM);

        dashboardTelemetry.addData("Claw Error", clawError);
        dashboardTelemetry.addData("Arm Error", armError);

        dashboardTelemetry.addData(">", "OpMode active");

        boolean override = false;

        if (gamepad1.options) {
            override = true;
            dashboardTelemetry.addData("!OVERRIDE", "ACTIVE!");
        }

        double drive = -gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;
        double strafe = -gamepad1.left_stick_x;
        double arm = gamepad1.left_trigger - gamepad1.right_trigger;
        int liftState = 0, clawRState = 0;

        if ((gamepad1.dpad_up && !gamepad1.dpad_down) || (gamepad2.dpad_up && !gamepad2.dpad_down)) {
            liftState = 2;
        } else if ((gamepad1.dpad_down && !gamepad1.dpad_up) || (gamepad2.dpad_down && !gamepad2.dpad_up)) {
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
        robot.moveClaw(clawState);
        armError = robot.moveArm(arm, override);
        clawError = robot.rotateClaw(clawRState, override);
        dashboardTelemetry.update();
    }
}