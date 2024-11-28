package org.firstinspires.ftc.teamcode.initialize;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Hardware {

    private final OpMode callingOpMode;
    private DcMotorEx leftDrive = null;
    private DcMotorEx rightDrive = null;
    private DcMotorEx transverseDrive = null;
    private DcMotorEx armHex = null;

    public void configureMotors() {
        callingOpMode.telemetry.addData("Status", "Initialize");
        callingOpMode.telemetry.update();

        armHex = callingOpMode.hardwareMap.get(DcMotorEx.class, "armHex");
        leftDrive = callingOpMode.hardwareMap.get(DcMotorEx.class, "leftDrive");
        rightDrive = callingOpMode.hardwareMap.get(DcMotorEx.class, "rightDrive");
        transverseDrive = callingOpMode.hardwareMap.get(DcMotorEx.class, "transverseDrive");

        armHex.setDirection(DcMotorEx.Direction.FORWARD);
        leftDrive.setDirection(DcMotorEx.Direction.REVERSE);
        rightDrive.setDirection(DcMotorEx.Direction.FORWARD);
        transverseDrive.setDirection(DcMotorEx.Direction.FORWARD);

        armHex.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        transverseDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        armHex.setTargetPosition(300);
        armHex.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        leftDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        transverseDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        callingOpMode.telemetry.addData("Status", "Standby");
        callingOpMode.telemetry.update();

        armHex.setTargetPosition(300);
        armHex.setVelocity(200);
    }
    public void setPowerLeft(double power) {
        leftDrive.setPower(power);
    }

    public void setPowerRight(double power) {
        rightDrive.setPower(power);
    }

    public void setPowerTransverse(double power) {
        transverseDrive.setPower(power);
    }

    public void setPositionArmHex(int position) {
        armHex.setTargetPosition(position);
    }
    public Hardware (OpMode opmode) {
        callingOpMode = opmode;
        configureMotors();
    }


}
