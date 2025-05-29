package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ProfiledPIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;


public class ClawRotationSubsystem extends SubsystemBase {
    private final DcMotorEx clawMotor;
    private final Telemetry dashboard;
    private final ProfiledPIDController clawRotationController;
    private double setpoint = 0;

    public ClawRotationSubsystem(final OpMode opMode, Telemetry dashboard) {
        this.dashboard = dashboard;
        clawRotationController = new ProfiledPIDController(Constants.ClawConstants.K_P_CLAW, Constants.ClawConstants.K_I_CLAW, Constants.ClawConstants.K_D_CLAW, Constants.ClawConstants.clawRotationConstraints);
        clawMotor = opMode.hardwareMap.get(DcMotorEx.class, "claw_motor");

        clawMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        clawMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        clawMotor.setDirection(DcMotorEx.Direction.FORWARD);

        clawMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    public enum ClawRotation {
        DROP,
        PICKUP,
        CARRY,
        UNKNOWN
    }

    public ClawRotation getClawRotationEnumPosition() {
        double currentPosition = getClawRotationAngle();

        if (Math.abs(currentPosition - Constants.ClawConstants.CLAW_DROP_ROTATION) < Constants.ClawConstants.CLAW_ERROR_TOLERANCE * 2) {
            return ClawRotation.DROP;
        } else if (Math.abs(currentPosition - Constants.ClawConstants.CLAW_PICKUP_ROTATION) < Constants.ClawConstants.CLAW_ERROR_TOLERANCE * 2) {
            return ClawRotation.PICKUP;
        } else if (Math.abs(currentPosition - Constants.ClawConstants.CLAW_CARRY_ROTATION) < Constants.ClawConstants.CLAW_ERROR_TOLERANCE * 2) {
            return ClawRotation.CARRY;
        } else {
            return ClawRotation.UNKNOWN;
        }
    }
    public int getClawRotationAngle() {
        return clawMotor.getCurrentPosition() * 360;
    }

    public void setPower(double pwr) {
        clawMotor.setPower(pwr);
    }

    public void setClawRotationTarget(double angle) {
        if (angle >= Constants.ClawConstants.MAX_CLAW_ROTATION) {
            setpoint = Constants.ClawConstants.MAX_CLAW_ROTATION;
        }
        else if (angle <= Constants.ClawConstants.MIN_CLAW_ROTATION) {
            setpoint = Constants.ClawConstants.MIN_CLAW_ROTATION;
        }
        else {
            setpoint = angle;
        }
        clawRotationController.setGoal(setpoint);
    }

    public void goToSetpointRotation() {
        setPower(clawRotationController.calculate(getClawRotationAngle()));
    }

    public void updateTelemetry() {

    }

    @Override
    public void periodic() {
        updateTelemetry();

    }

}
