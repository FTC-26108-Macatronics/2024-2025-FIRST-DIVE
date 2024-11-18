package org.firstinspires.ftc.teamcode.initialize;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Hardware {
    private OpMode callingOpMode = null;
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor transverseDrive = null;

    private DcMotorEx armHex = null;

    public Hardware(OpMode opmode) {
        callingOpMode = opmode;
    }

    public void init() {
        callingOpMode.telemetry.addData("Status", "Initialize");
        callingOpMode.telemetry.update();

        armHex = callingOpMode.hardwareMap.get(DcMotorEx.class, "armHex");
        leftDrive = callingOpMode.hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = callingOpMode.hardwareMap.get(DcMotor.class, "rightDrive");
        transverseDrive = callingOpMode.hardwareMap.get(DcMotor.class, "transverseDrive");

        armHex.setDirection(DcMotor.Direction.FORWARD);
        leftDrive.setDirection(DcMotor.Direction.REVERSE);

        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        transverseDrive.setDirection(DcMotorSimple.Direction.FORWARD);

        // initilizing the arm requires a pre-set position change this as you go please
        armHex.setTargetPosition(300);

        armHex.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        transverseDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        callingOpMode.telemetry.addData("Status", "Standby");
        callingOpMode.telemetry.update();

        // remove this and test
        armHex.setTargetPosition(300);
        armHex.setVelocity(200);
    }

    public void armRotation(double controllerInput, int max){

        // use custom mapVal(); and test
        armHex.setTargetPosition((int)((controllerInput*360)+max));
    }


    public void setDrivePower(double leftWheel, double rightWheel) {
        leftDrive.setPower(leftWheel);
        rightDrive.setPower(rightWheel);
    }

    public void strafe(double pwr) {
        transverseDrive.setPower(pwr);
    }

    public void driveArcade(double drive, double turn) {
        double leftPwr = drive - turn;
        double rightPwr = drive + turn;
        double max = Math.max(Math.abs(leftPwr), Math.abs(rightPwr));

        if (max > 1.0) {
            leftPwr /= max;
            rightPwr /= max;
        }

        setDrivePower(leftPwr, rightPwr);
    }
}