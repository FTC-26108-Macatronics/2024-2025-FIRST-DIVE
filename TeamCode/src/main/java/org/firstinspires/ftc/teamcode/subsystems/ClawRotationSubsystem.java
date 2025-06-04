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
    private ClawRotation clawRotation;

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
        HOME,
        DROP,
        PICKUP,
        CARRY,
        UNKNOWN
    }

    public ClawRotation getClawRotationEnumPosition() {
        return clawRotation;
    }

    public void setClawEnumRotation(ClawRotation rotation) {
        clawRotation = rotation;
    }

    public void setRotation(ClawRotation rotation) {
        switch (rotation) {
            case HOME:
                setpoint = 0;
                break;
            case DROP:
                setpoint = Constants.ClawConstants.CLAW_DROP_ROTATION;
                break;
            case PICKUP:
                setpoint = Constants.ClawConstants.CLAW_PICKUP_ROTATION;
                break;
            case CARRY:
                setpoint = Constants.ClawConstants.CLAW_CARRY_ROTATION;
                break;
            default: setpoint = 0;
        }
        setClawEnumRotation(rotation);
        clawRotationController.setGoal(setpoint);
    }

    public int getClawRotationAngle() {
        return clawMotor.getCurrentPosition() * 360;
    }

    public void setPower(double pwr) {
        clawMotor.setPower(pwr);
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
