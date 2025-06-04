package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;


public class ClawSubsystem extends SubsystemBase {

    private final Servo clawServo;
    private final Telemetry dashboard;
    private double setpoint = 0;

    private ClawPosition clawPos = ClawPosition.CLOSED;
    public ClawSubsystem(final OpMode opMode, Telemetry dashboard) {
        this.dashboard = dashboard;
        clawServo = opMode.hardwareMap.get(Servo.class, "claw_servo");
        clawServo.setDirection(Servo.Direction.FORWARD);
    }

    public enum ClawPosition {
        CLOSED,
        OPEN,
        UNKNOWN
    }

    public ClawPosition getClawEnumPosition() {
        return clawPos;
    }

    public void setClawEnumPosition(ClawPosition position) {
        clawPos = position;
    }

    public void setPosition(ClawPosition position) {
        switch (position) {
            case CLOSED:
                setpoint = Constants.ClawConstants.SERVO_OPEN;
                break;
            case OPEN:
                setpoint = Constants.ClawConstants.SERVO_CLOSED;
                break;
            default: setpoint = 0;
        }
        setClawEnumPosition(position);
    }

    public double getClawPosition() {
        return clawServo.getPosition();
    }

    public boolean atTargetPosition() {
        return Math.abs(getClawPosition() - setpoint) <= Constants.ClawConstants.CLAW_ERROR_TOLERANCE;
    }
    public void goToPosition() {
        clawServo.setPosition(setpoint);
    }

    public void openClaw() {
        clawServo.setPosition(Constants.ClawConstants.SERVO_OPEN);
    }
    public void updateTelemetry() {

    }

    @Override
    public void periodic() {
        updateTelemetry();

    }

}
