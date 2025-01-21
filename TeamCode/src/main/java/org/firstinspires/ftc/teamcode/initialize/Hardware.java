package org.firstinspires.ftc.teamcode.initialize;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoControllerEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.Constants.DriveConstants;

import org.firstinspires.ftc.teamcode.Constants;


public class Hardware {

    private DcMotorEx leftDrive = null;
    private DcMotorEx rightDrive = null;
    private DcMotorEx transverseDrive = null;
    private DcMotorEx lift = null;

    private DcMotorEx arm = null;

    private DcMotorEx claw = null;

    private Servo clawServo = null;
    private final OpMode callingOpMode;

    public void configureMotors() {
        callingOpMode.telemetry.addData("Status", "Initialize");
        callingOpMode.telemetry.update();

        leftDrive = callingOpMode.hardwareMap.get(DcMotorEx.class, "leftDrive");
        rightDrive = callingOpMode.hardwareMap.get(DcMotorEx.class, "rightDrive");
        transverseDrive = callingOpMode.hardwareMap.get(DcMotorEx.class, "transverseDrive");
        lift = callingOpMode.hardwareMap.get(DcMotorEx.class, "lift");
        arm = callingOpMode.hardwareMap.get(DcMotorEx.class, "arm");
        claw = callingOpMode.hardwareMap.get(DcMotorEx.class, "claw");
        clawServo = callingOpMode.hardwareMap.get(Servo.class, "clawServo");

        leftDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        transverseDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        claw.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        setTargetPositionArmHex(Constants.ArmConstants.ARM_INIT_POSITION);
        setPositionClaw(Constants.ArmConstants.CLAW_INIT_POSITION);

        leftDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        transverseDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        lift.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        claw.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        leftDrive.setDirection(DcMotorEx.Direction.FORWARD);
        rightDrive.setDirection(DcMotorEx.Direction.REVERSE);
        transverseDrive.setDirection(DcMotorEx.Direction.REVERSE);
        lift.setDirection(DcMotorEx.Direction.FORWARD);
        arm.setDirection(DcMotorEx.Direction.REVERSE);
        claw.setDirection(DcMotorEx.Direction.REVERSE);
        clawServo.setDirection(Servo.Direction.FORWARD);

        leftDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        transverseDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        lift.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        arm.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        claw.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        callingOpMode.telemetry.addData("Status", "Standby");
        callingOpMode.telemetry.update();
    }
    public void setPowerLeft(double power) {
        leftDrive.setPower(power);
    }

    public void strafe(double power) {
        transverseDrive.setPower(power);
    }

    public void setPowerRight(double power) {
        rightDrive.setPower(power);
    }

    public void setPowerTransverse(double power) {
        transverseDrive.setPower(power);
    }

    public void moveLiftUp(double currentPosition) {
        if (currentPosition < DriveConstants.liftTopLimiter) {
            lift.setPower(0.80);
        }
    }

    public void moveLiftDown(double currentPosition) {
        if (currentPosition < DriveConstants.liftBottomLimiter) {
            lift.setPower(-0.80);
        }
    }

    public double getLiftCurrentPosition() {
        return lift.getCurrentPosition();
    }

    public double getDrivePosition() {
        return rightDrive.getCurrentPosition();
    }

    public double getStrafePosition() {
        return transverseDrive.getCurrentPosition();
    }

    public void setPowerArm(double power) {
        arm.setPower(power);
    }
    public void setTargetPositionArmHex(int position) {
        arm.setTargetPosition(position);
    }

    public void setPositionClaw(double position) {
        clawServo.setPosition(position);
    }
    public int getArmPosition() {
        return arm.getCurrentPosition();
    }


    public double pid(int   target) {
        double error = target - getArmPosition();
        //d = (error - lastError) / timer.seconds();
        //i += error * timer.seconds();
        //lastError = error;
        return (DriveConstants.K_P * error);// + (Ki * i) + (Kd * d);
    }

    public Hardware (OpMode opmode) {
        callingOpMode = opmode;
        configureMotors();
    }
}
