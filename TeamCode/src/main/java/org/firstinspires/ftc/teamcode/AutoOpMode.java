package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class AutoOpMode extends LinearOpMode {
    static final double COUNTS_PER_MOTOR_REV = 28;
    static final double DRIVE_GEAR_REDUCTION = 20;
    static final double WHEEL_DIAMETER_INCHES = 4;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.6;
    static final double TURN_SPEED = 0.5;
    private final ElapsedTime runtime = new ElapsedTime();
    ElapsedTime timerArm = new ElapsedTime();
    ElapsedTime timerClaw = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotorEx transverseDrive = null;
    private DcMotorEx liftMotor = null;
    private DcMotorEx armMotor = null;
    private Servo clawServo = null;
    private DcMotorEx clawMotor = null;




    final double PWR_LIFT = 1, K_P_ARM = 0.05, K_I_ARM = 0.003, K_D_ARM = 0.003, PWR_CLAW = 1.0, SERVO_OPEN = 0.4, SERVO_CLOSED = 0.7;
    final double K_P_CLAW = 0.1, K_I_CLAW = 0, K_D_CLAW = 0;
    double targetArm, iArm, lastErrorArm;
    double targetClaw, iClaw, lastErrorClaw;




    @Override
    public void runOpMode() {
        targetArm = 0;
        iArm = 0;
        lastErrorArm = 0;
        targetClaw = 0;
        iClaw = 0;
        lastErrorClaw = 0;




        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        transverseDrive = hardwareMap.get(DcMotorEx.class, "transverse_drive");
        liftMotor = hardwareMap.get(DcMotorEx.class, "lift");
        armMotor = hardwareMap.get(DcMotorEx.class, "arm");
        clawMotor = hardwareMap.get(DcMotorEx.class, "claw_motor");
        clawServo = hardwareMap.get(Servo.class, "claw_servo");
        clawServo.setPosition(SERVO_CLOSED);

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        transverseDrive.setDirection(DcMotorEx.Direction.REVERSE);
        liftMotor.setDirection(DcMotorEx.Direction.FORWARD);
        armMotor.setDirection(DcMotorEx.Direction.FORWARD);
        clawMotor.setDirection(DcMotorEx.Direction.FORWARD);
        clawServo.setDirection(Servo.Direction.FORWARD);

        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        transverseDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        clawMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        transverseDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        liftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        armMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        clawMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Starting at", "%7d :%7d", leftDrive.getCurrentPosition(), rightDrive.getCurrentPosition());
        telemetry.update();
        waitForStart();
        encoderDrive(DRIVE_SPEED, 40, 40, 5.0);
        moveLift(2);
        sleep(1000);
        moveArm(0.5);
        sleep(2000);
        rotateClaw(2);
        sleep(1000);
        moveClaw(false);
        moveClaw(true);
        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }

    public void encoderDrive(double speed, double leftInches, double rightInches, double timeoutS) {
        int newLeftTarget;
        int newRightTarget;
        if (opModeIsActive()) {
            newLeftTarget = leftDrive.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = rightDrive.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            leftDrive.setTargetPosition(newLeftTarget);
            rightDrive.setTargetPosition(newRightTarget);
            leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            runtime.reset();
            leftDrive.setPower(Math.abs(speed));
            rightDrive.setPower(Math.abs(speed));
            while (opModeIsActive() && (runtime.seconds() < timeoutS) && (leftDrive.isBusy() && rightDrive.isBusy())) {
                telemetry.addData("Running to", " %7d :%7d", newLeftTarget, newRightTarget);
                telemetry.addData("Currently at", " at %7d :%7d", leftDrive.getCurrentPosition(), rightDrive.getCurrentPosition());
                telemetry.update();
            }
            leftDrive.setPower(0);
            rightDrive.setPower(0);
            leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            sleep(250);
        }
    }

    public void setLift(double pwr) {
        liftMotor.setPower(pwr);
    }
    public void moveLift(int state) {
        double pwr = 0;

        if (state == 2) {
            pwr = PWR_LIFT;
        }

        if (state == 1) {
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

    public void moveArm(double rotation) {

        targetArm += rotation;


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

    public void rotateClaw(int state) {
        if (state == 2) {
            targetClaw += PWR_CLAW;
        }

        if (state == 1) {
            targetClaw -= PWR_CLAW;
        }

        double error = targetClaw - getClaw();
        iClaw += error * timerClaw.seconds();

        setClaw((K_P_CLAW * error) + (K_I_CLAW * iClaw) + (K_D_CLAW * ((error - lastErrorClaw) / timerClaw.seconds())));

        lastErrorClaw = error;
        timerClaw.reset();
    }
    public void moveClaw(boolean state) {
        if (state) {
            clawServo.setPosition(SERVO_OPEN);
        } else {
            clawServo.setPosition(SERVO_CLOSED);
        }
    }
}