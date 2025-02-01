package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class RobotHardware {
    ElapsedTime timerArm = new ElapsedTime();
    ElapsedTime timerClaw = new ElapsedTime();
    public static final double MAX_PWR_DT = 1, PWR_LIFT = 1, K_P_ARM = 0.05, K_I_ARM = 0.003, K_D_ARM = 0.003, PWR_CLAW = 1.0, SERVO_OPEN = 0.4, SERVO_CLOSED = 0.7;
    public static final double K_P_CLAW = 0.1, K_I_CLAW = 0, K_D_CLAW = 0;
    public static final int MAX_LIFT = 8400, MIN_LIFT = 0, MAX_ARM = 420, MIN_ARM = 0, MAX_CLAW = 0, MIN_CLAW = -180;
    public static double targetArm, iArm, lastErrorArm;
    public static double targetClaw, iClaw, lastErrorClaw;
    private final OpMode myOpMode;
    private DcMotorEx leftDrive = null;
    private DcMotorEx rightDrive = null;
    private DcMotorEx transverseDrive = null;
    private DcMotorEx liftMotor = null;
    private DcMotorEx armMotor = null;
    public DcMotorEx clawMotor = null;
    private Servo clawServo = null;

    public RobotHardware(OpMode opmode) {
        myOpMode = opmode;
    }

    public void init() {
        targetArm = 0;
        iArm = 0;
        lastErrorArm = 0;
        targetClaw = 0;
        iClaw = 0;
        lastErrorClaw = 0;

        myOpMode.telemetry.addData(">", "Initializing");
        myOpMode.telemetry.update();

        leftDrive = myOpMode.hardwareMap.get(DcMotorEx.class, "left_drive");
        rightDrive = myOpMode.hardwareMap.get(DcMotorEx.class, "right_drive");
        transverseDrive = myOpMode.hardwareMap.get(DcMotorEx.class, "transverse_drive");
        liftMotor = myOpMode.hardwareMap.get(DcMotorEx.class, "lift");
        armMotor = myOpMode.hardwareMap.get(DcMotorEx.class, "arm");
        clawMotor = myOpMode.hardwareMap.get(DcMotorEx.class, "claw_motor");
        clawServo = myOpMode.hardwareMap.get(Servo.class, "claw_servo");

        clawServo.setPosition(SERVO_CLOSED);

        leftDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        transverseDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        clawMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        leftDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        transverseDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        liftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        armMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        clawMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        leftDrive.setDirection(DcMotorEx.Direction.FORWARD);
        rightDrive.setDirection(DcMotorEx.Direction.REVERSE);
        transverseDrive.setDirection(DcMotorEx.Direction.REVERSE);
        liftMotor.setDirection(DcMotorEx.Direction.FORWARD);
        armMotor.setDirection(DcMotorEx.Direction.FORWARD);
        clawMotor.setDirection(DcMotorEx.Direction.FORWARD);
        clawServo.setDirection(Servo.Direction.FORWARD);

        leftDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        transverseDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        liftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        armMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        clawMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        myOpMode.telemetry.addData(">", "Initialized");
        myOpMode.telemetry.update();
    }

    public void setDrivePower(double left, double right) {
        leftDrive.setPower(left);
        rightDrive.setPower(right);
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

    public void strafe(double pwr) {
        transverseDrive.setPower(pwr);
    }

    public int getLift() {
        return liftMotor.getCurrentPosition();
    }

    public void setLift(double pwr) {
        liftMotor.setPower(pwr);
    }

    public void moveLift(int state, boolean override) {
        double pwr = 0;

        if (state == 2 && (getLift() < MAX_LIFT || override)) {
            pwr = PWR_LIFT;
        }
        if (state == 1 && (getLift() > MIN_LIFT || override)) {
            pwr = -PWR_LIFT;
        }

        setLift(pwr);
    }

    public int getArm() {
        return armMotor.getCurrentPosition();
    }

    public void setArm(double pwr) {
        armMotor.setPower(pwr);
    }

    public void moveArm(double rotation, boolean override) {
        armMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        if (((rotation > 0 && getArm() < MAX_ARM) || (rotation < 0 && getArm() > MIN_ARM)) || override) {
            targetArm += rotation;
        }
        double error = targetArm - getArm();
        iArm += error * timerArm.seconds();

        setArm((K_P_ARM * error) + (K_I_ARM * iArm) + (K_D_ARM * ((error - lastErrorArm) / timerArm.seconds())));

        lastErrorArm = error;
        timerArm.reset();
    }

    public int getClaw() {
        return clawMotor.getCurrentPosition();
    }

    public void setClaw(double pwr) {
        clawMotor.setPower(pwr);
    }

    public void rotateClaw(int state, boolean override) {
        double pwr = 0;

        if (state == 2 && (getClaw() < MAX_CLAW || override)) {
            pwr = PWR_CLAW;
        }
        if (state == 1 && (getClaw() > MIN_CLAW || override)) {
            pwr = -PWR_CLAW;
        }

        setClaw(pwr);
    }

    public double rotateClawPID(int state, boolean override) {
        clawMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        if (state == 2 && (getClaw() < MAX_CLAW || override)) {
            targetClaw += PWR_CLAW;
        }
        if (state == 1 && (getClaw() > MIN_CLAW || override)) {
            targetClaw -= PWR_CLAW;
        }

        double error = targetClaw - getClaw();
        iClaw += error * timerClaw.seconds();

        setClaw((K_P_CLAW * error) + (K_I_CLAW * iArm) + (K_D_CLAW * ((error - lastErrorClaw) / timerArm.seconds())));

        lastErrorClaw = error;
        timerClaw.reset();
        return error;
    }

    public void moveClaw(boolean state) {
        if (state) {
            clawServo.setPosition(SERVO_OPEN);
        } else {
            clawServo.setPosition(SERVO_CLOSED);
        }
    }

    public void lock (){
        //armMotor.setPower(1.0);
        clawMotor.setPower(1.0);
        //armMotor.setTargetPosition(getArm());

        //armMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        clawMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        clawMotor.setTargetPosition(getClaw());
    }
}