package org.firstinspires.ftc.teamcode.initialize;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.Constants;

public class Hardware {

    protected OpMode callingOpMode;

    protected DcMotorEx leftDrive, rightDrive, transverseDrive, armHex;


    public Hardware() {
        callingOpMode = null;
        leftDrive = null;
        rightDrive = null;
        transverseDrive = null;
        armHex = null;
    }

    public Hardware (OpMode opmode) {
        callingOpMode = opmode;
    }

    public void init() {
        callingOpMode.telemetry.addData("Status", "Initialize");
        callingOpMode.telemetry.update();

        armHex = callingOpMode.hardwareMap.get(DcMotorEx.class, "armHex");
        leftDrive = callingOpMode.hardwareMap.get(DcMotorEx.class, "leftDrive");
        rightDrive = callingOpMode.hardwareMap.get(DcMotorEx.class, "rightDrive");
        transverseDrive = callingOpMode.hardwareMap.get(DcMotorEx.class, "transverseDrive");

        armHex.setDirection(DcMotor.Direction.FORWARD);
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        transverseDrive.setDirection(DcMotorSimple.Direction.FORWARD);

        armHex.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        transverseDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        armHex.setTargetPosition(300);
        armHex.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        transverseDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        callingOpMode.telemetry.addData("Status", "Standby");
        callingOpMode.telemetry.update();

        armHex.setTargetPosition(300);
        armHex.setVelocity(200);
    }

}
