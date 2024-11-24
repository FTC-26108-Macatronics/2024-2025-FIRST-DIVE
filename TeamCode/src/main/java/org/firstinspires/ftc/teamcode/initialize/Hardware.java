package org.firstinspires.ftc.teamcode.initialize;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware {
    private final OpMode callingOpMode;
    public int ARM_INIT_POSITION = 0, ARM_MIN_POSITION = 0, ARM_MAX_POSITION = 235, ENCODER_TARGET_POSITION = 10;
    public double CLAW_INIT_POSITION = 0.41, CLAW_MIN_POSITION = 0.15, CLAW_MAX_POSITION = 0.41  ;
    // ElapsedTime timer = new ElapsedTime();
    // Move these to a separate Constants object
    double K_P = 0.2;

    // double Ki = 0;
    // double Kd = 0;
    // These need to be in the class
    int target = 0;
    double clawTarget = CLAW_INIT_POSITION, CLAW_VELOCITY = 0.01;

    // double i = 0, d = 0;
    // double lastError = 0;

    private DcMotorEx leftDrive = null;
    private DcMotorEx rightDrive = null;
    private DcMotorEx transverseDrive = null;
    private DcMotorEx arm = null;
    private Servo claw = null;

    public Hardware(OpMode opmode) {
        callingOpMode = opmode;
    }


    public void init() {
        callingOpMode.telemetry.addData("Status", "Initializing");
        callingOpMode.telemetry.update();

        leftDrive = callingOpMode.hardwareMap.get(DcMotorEx.class, "leftDrive");
        rightDrive = callingOpMode.hardwareMap.get(DcMotorEx.class, "rightDrive");
        transverseDrive = callingOpMode.hardwareMap.get(DcMotorEx.class, "transverseDrive");
        arm = callingOpMode.hardwareMap.get(DcMotorEx.class, "arm");
        claw = callingOpMode.hardwareMap.get(Servo.class, "claw");

        leftDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        transverseDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        arm.setTargetPosition(ARM_INIT_POSITION);
        claw.setPosition(CLAW_INIT_POSITION);


        leftDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        transverseDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        leftDrive.setDirection(DcMotorEx.Direction.FORWARD);
        rightDrive.setDirection(DcMotorEx.Direction.REVERSE);
        transverseDrive.setDirection(DcMotorEx.Direction.REVERSE);
        // encoder - dr n tested arm.setDirection(DcMotorEx.Direction.FORWARD);
        arm.setDirection(DcMotorEx.Direction.REVERSE);
        claw.setDirection(Servo.Direction.FORWARD);

        leftDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        transverseDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        arm.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        callingOpMode.telemetry.addData("Status", "Ready");
        callingOpMode.telemetry.update();
    }


    public int getEncoderValues () {
        return transverseDrive.getCurrentPosition();
    }
    public int rotateArm(double rotation) {

        /*if (target >= ARM_MIN_POSITION && target <= ARM_MAX_POSITION) {
            target += (int) rotation;
        }*/
        target += (int) rotation;

        // timer.reset();
        arm.setPower(pid(target));

        return target;
    }

    public void setDrivePower(double left, double right) {
        left = ((Math.abs(left) / left) * (Math.pow(left * 0.8, 2)));
        right = ((Math.abs(right) / right) * (Math.pow(right * 0.8, 2)));
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

        if (max > 1.0) {
            leftPwr /= max;
            rightPwr /= max;
        }

        setDrivePower(leftPwr, rightPwr);
    }

    public int armPosition() {
        return arm.getCurrentPosition();
    }


    public double pid(int target) {
        double error = target - armPosition();
        //d = (error - lastError) / timer.seconds();
        //i += error * timer.seconds();
        //lastError = error;
        return (K_P * error)/* + (Ki * i) + (Kd * d)*/;
    }

    public double moveClaw(boolean direction) {




        if (clawTarget>=CLAW_MIN_POSITION && direction) {
            clawTarget -= CLAW_VELOCITY;
        }
        else if (clawTarget<=CLAW_MAX_POSITION && !direction)
        {
            clawTarget += CLAW_VELOCITY;
        }





        claw.setPosition(clawTarget);

        return clawTarget;
    }
}