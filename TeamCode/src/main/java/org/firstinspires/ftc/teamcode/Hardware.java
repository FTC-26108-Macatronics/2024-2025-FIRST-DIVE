package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware {
    private final OpMode callingOpMode;

    private DcMotorEx leftMotor = null;
    private DcMotorEx rightMotor = null;
    private DcMotorEx transverseMotor = null;
    private Servo clawServo = null;

    public Hardware(OpMode opmode) {
        callingOpMode = opmode;
    }


    public void init() {
        callingOpMode.telemetry.addData("Status", "Initializing");
        callingOpMode.telemetry.update();

        leftMotor = callingOpMode.hardwareMap.get(DcMotorEx.class, "leftMotor");
        rightMotor = callingOpMode.hardwareMap.get(DcMotorEx.class, "rightMotor");
        transverseMotor = callingOpMode.hardwareMap.get(DcMotorEx.class, "transverseMotor");
        clawServo = callingOpMode.hardwareMap.get(Servo.class, "clawServo");

        //claw.setPosition(CLAW_INIT_POSITION);

        leftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        transverseMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        leftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        transverseMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        leftMotor.setDirection(DcMotorEx.Direction.FORWARD);
        rightMotor.setDirection(DcMotorEx.Direction.REVERSE);
        transverseMotor.setDirection(DcMotorEx.Direction.REVERSE);
        clawServo.setDirection(Servo.Direction.FORWARD);

        leftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        transverseMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        callingOpMode.telemetry.addData("Status", "Ready");
        callingOpMode.telemetry.update();
    }

    public void setDrivePower(double left, double right) {
        leftMotor.setPower(left);
        rightMotor.setPower(right);
    }

    public void strafe(double pwr) {
        transverseMotor.setPower(pwr);
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

    public double getServoPosition() {
        return clawServo.getPosition();
    }
    /*public void moveClaw(boolean direction) {
        if (direction) {
            clawServo.setPosition();
        } else {

        }

    }*/
}