package org.firstinspires.ftc.teamcode.initialize;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import static org.firstinspires.ftc.teamcode.Constants.DriveConstants;

import org.firstinspires.ftc.teamcode.Constants;

public class Hardware {

    private final OpMode callingOpMode;
    private DcMotorEx leftDrive = null;
    private DcMotorEx rightDrive = null;
    private DcMotorEx transverseDrive = null;
    private DcMotorEx armHex = null;
    private Servo claw = null;


    public void configureMotors() {
        callingOpMode.telemetry.addData("Status", "Initialize");
        callingOpMode.telemetry.update();

        armHex = callingOpMode.hardwareMap.get(DcMotorEx.class, "armHex");
        leftDrive = callingOpMode.hardwareMap.get(DcMotorEx.class, "leftDrive");
        rightDrive = callingOpMode.hardwareMap.get(DcMotorEx.class, "rightDrive");
        transverseDrive = callingOpMode.hardwareMap.get(DcMotorEx.class, "transverseDrive");
        claw = callingOpMode.hardwareMap.get(Servo.class, "claw");

        leftDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        transverseDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        armHex.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        setTargetPositionArmHex(Constants.ArmConstants.ARM_INIT_POSITION);
        setPositionClaw(Constants.ArmConstants.CLAW_INIT_POSITION);

        leftDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        transverseDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        armHex.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        leftDrive.setDirection(DcMotorEx.Direction.FORWARD);
        rightDrive.setDirection(DcMotorEx.Direction.REVERSE);
        transverseDrive.setDirection(DcMotorEx.Direction.REVERSE);
        // encoder - dr n tested arm.setDirection(DcMotorEx.Direction.FORWARD);
        armHex.setDirection(DcMotorEx.Direction.REVERSE);
        claw.setDirection(Servo.Direction.FORWARD);

        leftDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        transverseDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        armHex.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        callingOpMode.telemetry.addData("Status", "Standby");
        callingOpMode.telemetry.update();
    }
    public void setPowerLeft(double power) {
        leftDrive.setPower(power);
    }

    public void setPowerRight(double power) {
        rightDrive.setPower(power);
    }

    public void setPowerArm(double power) {
        armHex.setPower(power);
    }

    public void setPowerTransverse(double power) {
        transverseDrive.setPower(power);
    }

    public void setTargetPositionArmHex(int position) {
        armHex.setTargetPosition(position);
    }

    public void setPositionClaw(double position) {
        claw.setPosition(position);
    }
    public int getArmPosition() {
        return armHex.getCurrentPosition();
    }
    public double pid(int target) {
        double error = target - getArmPosition();
        //d = (error - lastError) / timer.seconds();
        //i += error * timer.seconds();
        //lastError = error;
        return (DriveConstants.getPIDConstants('P') * error)/* + (Ki * i) + (Kd * d)*/;
    }
    public Hardware (OpMode opmode) {
        callingOpMode = opmode;
        configureMotors();
    }




}
