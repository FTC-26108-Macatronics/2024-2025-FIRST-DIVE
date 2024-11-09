package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class teleOpTest extends OpMode {
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;

    @Override
    public void init() {
        telemetry.addData("Status", "Initializing");
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        telemetry.addData("Status", "Standby");
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Running");
        double pwrLeft;
        double pwrRight;
        pwrLeft = -gamepad1.left_stick_y;
        pwrRight = -gamepad1.right_stick_y;

        leftDrive.setPower(pwrLeft);
        rightDrive.setPower(pwrRight);
        telemetry.addData("Left",  "%.2f", pwrLeft);
        telemetry.addData("Right", "%.2f", pwrRight);
    }
}