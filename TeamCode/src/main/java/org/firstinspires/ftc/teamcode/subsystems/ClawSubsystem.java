package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static org.firstinspires.ftc.teamcode.Constants.DriveConstants.MAX_PWR_DT;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Constants;


public class ClawSubsystem extends SubsystemBase {

    private final Servo clawServo;
    private final DcMotorEx clawMotor;

    private double targetClaw;
    private double iClaw;
    private double lastErrorClaw;

    private ElapsedTime timerClaw;

    private boolean clawState;



    public ClawSubsystem(final HardwareMap hMap) {
        clawMotor = hMap.get(DcMotorEx.class, "claw_motor");
        clawServo = hMap.get(Servo.class, "claw_servo");

        clawMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        clawMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        clawMotor.setDirection(DcMotorEx.Direction.FORWARD);
        clawServo.setDirection(Servo.Direction.FORWARD);

        clawMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        targetClaw = 0;
        iClaw = 0;
        lastErrorClaw = 0;

        timerClaw = new ElapsedTime();

    }

    public int getClaw() {
        return clawMotor.getCurrentPosition();
    }

    public void setClaw(double pwr) {
        clawMotor.setPower(pwr);
    }

    public void rotateClaw(int state, boolean override) {
        if (state == 2 && (getClaw() < Constants.ClawConstants.MAX_CLAW || override)) {
            targetClaw += Constants.ClawConstants.PWR_CLAW;
        }

        if (state == 1 && (getClaw() > Constants.ClawConstants.MIN_CLAW || override)) {
            targetClaw -= Constants.ClawConstants.PWR_CLAW;
        }

        double error = targetClaw - getClaw();
        iClaw += error * timerClaw.seconds();

        setClaw((Constants.ClawConstants.K_P_CLAW * error) + (Constants.ClawConstants.K_I_CLAW * iClaw) + (Constants.ClawConstants.K_D_CLAW * ((error - lastErrorClaw) / timerClaw.seconds())));

        lastErrorClaw = error;
        timerClaw.reset();
    }

    public void moveClaw(boolean state) {
        if (state) {
            clawServo.setPosition(Constants.ClawConstants.SERVO_OPEN);
        } else {
            clawServo.setPosition(Constants.ClawConstants.SERVO_CLOSED);
        }
    }


    @Override
    public void periodic() {

        boolean override = false;

        if (gamepad1.options) {
            override = true;
            telemetry.addData("!OVERRIDE", "ACTIVE!");
        }

        int clawRState = 0;

        if (gamepad1.y && !gamepad1.a) {
            clawRState = 2;
        } else if (gamepad1.a && !gamepad1.y) {
            clawRState = 1;
        }

        if (gamepad1.left_bumper && !gamepad1.right_bumper) {
            clawState = true;
        } else if (gamepad1.right_bumper && !gamepad1.left_bumper) {
            clawState = false;
        }

        rotateClaw(clawRState, override);
        moveClaw(clawState);

    }

}
