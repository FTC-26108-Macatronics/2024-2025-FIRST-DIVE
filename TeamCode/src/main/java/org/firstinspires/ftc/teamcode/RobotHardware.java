package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class RobotHardware {
    public static final double SERVO_CLOSED = 0.6, SERVO_OPEN = 0.7, MAX_PWR_DT = 1, MAX_PWR_LIFT = 1;
    public static final int MAX_LIFT = 8400, MIN_LIFT = 0;
    private final OpMode myOpMode;
    private DcMotorEx leftDrive = null;
    private DcMotorEx rightDrive = null;
    private DcMotorEx transverseDrive = null;
    private DcMotorEx liftMotor = null;
    private Servo clawServo = null;

    public RobotHardware(OpMode opmode) {
        myOpMode = opmode;
    }

    public void init() {
        myOpMode.telemetry.addData(">", "Initializing");
        myOpMode.telemetry.update();

        leftDrive = myOpMode.hardwareMap.get(DcMotorEx.class, "left_drive");
        rightDrive = myOpMode.hardwareMap.get(DcMotorEx.class, "right_drive");
        transverseDrive = myOpMode.hardwareMap.get(DcMotorEx.class, "transverse_drive");
        liftMotor = myOpMode.hardwareMap.get(DcMotorEx.class, "lift");
        clawServo = myOpMode.hardwareMap.get(Servo.class, "claw_servo");

        clawServo.setPosition(SERVO_CLOSED);

        leftDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        transverseDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        leftDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        transverseDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        liftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        leftDrive.setDirection(DcMotorEx.Direction.FORWARD);
        rightDrive.setDirection(DcMotorEx.Direction.REVERSE);
        transverseDrive.setDirection(DcMotorEx.Direction.REVERSE);
        liftMotor.setDirection(DcMotorEx.Direction.FORWARD);
        clawServo.setDirection(Servo.Direction.FORWARD);

        leftDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        transverseDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        liftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        myOpMode.telemetry.addData(">", "Initialized");
        myOpMode.telemetry.update();
    }

    public void setDrivePower(double left, double right) {
        leftDrive.setPower(left);
        rightDrive.setPower(right);
    }

    public void strafe(double pwr) {
        transverseDrive.setPower(pwr);
    }

    public void driveArcade(double drive, double turn) {
        double leftPwr = drive + turn;
        double rightPwr = drive - turn;
        double max = Math.max(Math.abs(leftPwr), Math.abs(rightPwr));

        if (max > MAX_PWR_DT) {
            leftPwr /= max;
            rightPwr /= max;
        }

        setDrivePower(leftPwr, rightPwr);
    }

    public void moveClaw(boolean direction) {
        if (direction) {
            clawServo.setPosition(SERVO_CLOSED);
        } else {
            clawServo.setPosition(SERVO_OPEN);
        }
    }

    public double moveLift(int state, boolean override) {
        double pwr = 0;
        /*switch (state) {
            case 2:
                if (getLift() < MAX_LIFT) {
                    pwr = MAX_PWR_LIFT;
                }
            case 1:
                if (getLift() > MIN_LIFT) {
                    pwr = -MAX_PWR_LIFT;
                }
        }*/
        if (state == 2 && (getLift() < MAX_LIFT || override)) {
            pwr = MAX_PWR_LIFT;
        }
        if (state == 1 && (getLift() > MIN_LIFT || override)) {
            pwr = -MAX_PWR_LIFT;
        }
        setLift(pwr);
        return pwr;
    }

    public double getLift() {
        return liftMotor.getCurrentPosition();
    }

    public void setLift(double pwr) {
        liftMotor.setPower(pwr);
    }
}