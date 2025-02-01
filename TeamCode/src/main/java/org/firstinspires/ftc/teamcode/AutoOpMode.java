package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
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
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;

    @Override
    public void runOpMode() {

        final double PWR_LIFT = 1, K_P_ARM = 0.05, K_I_ARM = 0.003, K_D_ARM = 0.003, PWR_CLAW = 1.0, SERVO_OPEN = 0.4, SERVO_CLOSED = 0.7;





        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("Starting at", "%7d :%7d", leftDrive.getCurrentPosition(), rightDrive.getCurrentPosition());
        telemetry.update();
        waitForStart();
        encoderDrive(DRIVE_SPEED, 48, 48, 5.0);
        encoderDrive(TURN_SPEED, 12, -12, 4.0);
        encoderDrive(DRIVE_SPEED, -24, -24, 4.0);
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
}