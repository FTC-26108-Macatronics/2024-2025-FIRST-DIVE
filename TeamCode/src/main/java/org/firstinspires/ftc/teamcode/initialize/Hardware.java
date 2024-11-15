package org.firstinspires.ftc.teamcode.initialize;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.Constants;

public class Hardware {

    protected OpMode callingOpMode;

    protected DcMotorEx leftDrive,
                        rightDrive,
                        transverseDrive;


    public Hardware() {
        callingOpMode = null;
        leftDrive = null;
        rightDrive = null;
        transverseDrive = null;
    }

    public Hardware (OpMode opmode) {
        callingOpMode = opmode;
    }

    public void init() {
        callingOpMode.telemetry.addData("Status", "Initialize");
        callingOpMode.telemetry.update();

        leftDrive = callingOpMode.hardwareMap.get(DcMotorEx.class, "leftDrive");
        rightDrive = callingOpMode.hardwareMap.get(DcMotorEx.class, "rightDrive");
        transverseDrive = callingOpMode.hardwareMap.get(DcMotorEx.class, "transverseDrive");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        transverseDrive.setDirection(DcMotorSimple.Direction.FORWARD);

        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        transverseDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        transverseDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        callingOpMode.telemetry.addData("Status", "Standby");
        callingOpMode.telemetry.update();
    }

}
