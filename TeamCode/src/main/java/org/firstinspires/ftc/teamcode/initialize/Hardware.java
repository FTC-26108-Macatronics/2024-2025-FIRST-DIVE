package org.firstinspires.ftc.teamcode.initialize;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Hardware {
    private OpMode callingOpMode = null;
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor transverseDrive = null;

    public Hardware(OpMode opmode) {
        callingOpMode = opmode;
    }

    public void init() {
        callingOpMode.telemetry.addData("Status", "Initialize");
        callingOpMode.telemetry.update();

        leftDrive = callingOpMode.hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = callingOpMode.hardwareMap.get(DcMotor.class, "rightDrive");
        transverseDrive = callingOpMode.hardwareMap.get(DcMotor.class, "transverseDrive");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        transverseDrive.setDirection(DcMotorSimple.Direction.FORWARD);

        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        transverseDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        callingOpMode.telemetry.addData("Status", "Standby");
        callingOpMode.telemetry.update();
    }

    public void setDrivePower(double leftWheel, double rightWheel) {
        leftDrive.setPower(leftWheel);
        rightDrive.setPower(rightWheel);
    }

    public void strafe(double pwr) {
        transverseDrive.setPower(pwr);
    }

    public void driveArcade(double drive, double turn) {
        double leftPwr = drive + turn;
        double rightPwr = drive - turn;
        double max = Math.max(Math.abs(leftPwr), Math.abs(rightPwr));

        if (max > 1.0) {
            leftPwr /= max;
            rightPwr /= max;
        }

        setDrivePower(leftPwr, rightPwr);
    }
}