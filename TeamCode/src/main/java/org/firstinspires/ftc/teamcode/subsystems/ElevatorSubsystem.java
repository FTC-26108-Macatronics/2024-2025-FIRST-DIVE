package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ProfiledPIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;


public class ElevatorSubsystem extends SubsystemBase {

    private final DcMotorEx liftMotor;
    private final Telemetry dashboard;
    private final ProfiledPIDController elevatorController;
    private double setpoint = 0;
    private ElevatorPosition elvPos = ElevatorPosition.HOME;

    public ElevatorSubsystem(final OpMode opMode, Telemetry dashboard) {
        this.dashboard = dashboard;
        elevatorController = new ProfiledPIDController(Constants.ElevatorConstants.K_P_ELV, Constants.ElevatorConstants.K_I_ELV, Constants.ElevatorConstants.K_D_ELV, Constants.ElevatorConstants.elvConstraints);

        liftMotor = opMode.hardwareMap.get(DcMotorEx.class, "lift");
        liftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        liftMotor.setDirection(DcMotorEx.Direction.FORWARD);
        liftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

    }
    public enum ElevatorPosition {
        HOME,
        L1,
        L2,
        L3,
        UNKNOWN
    }

    public void setPower(double power) {
        liftMotor.setPower(power);
    }

    public boolean atTargetHeight() {
        return Math.abs(getPositionInMeters() - setpoint) <= Constants.ElevatorConstants.ELEVATOR_ERROR_TOLERANCE;
    }

    public double getPositionInMeters() {
        return liftMotor.getCurrentPosition() * Constants.ElevatorConstants.ELEVATOR_METERS_PER_MOTOR_ROTATION;
    }

    public double getVelocityInMeters() {
        return liftMotor.getVelocity() * Constants.ElevatorConstants.ELEVATOR_METERS_PER_MOTOR_ROTATION;
    }

    public void stop() {
        liftMotor.setPower(0);
    }

    public void zeroElevator() {
        setPosition(ElevatorPosition.HOME);
    }

    public void setPosition(ElevatorPosition position) {
        switch (position) {
            case HOME:
                setpoint = 0;
                break;
            case L1:
                setpoint = Constants.ElevatorConstants.ELEVATOR_L1_HEIGHT;
                break;
            case L2:
                setpoint = Constants.ElevatorConstants.ELEVATOR_L2_HEIGHT;
                break;
            case L3:
                setpoint = Constants.ElevatorConstants.ELEVATOR_L3_HEIGHT;
                break;
            default: setpoint = 0;
        }
        setElevatorEnumPosition(position);
        elevatorController.setGoal(setpoint);
    }

    public void goToSetpoint() {
        setPower(elevatorController.calculate(getPositionInMeters()));
    }

    public double getSetpoint() {
        return setpoint;
    }

    public void setElevatorEnumPosition(ElevatorPosition position) {
        elvPos = position;
    }
    public ElevatorPosition getElevatorEnumPosition() {
        return elvPos;
    }
    public void updateTelemetry() {
        //to tune pid (remove when done or comment out)
        dashboard.addData("KP ELV", Constants.ElevatorConstants.K_P_ELV);
        dashboard.addData("KI ELV", Constants.ElevatorConstants.K_I_ELV);
        dashboard.addData("KD ELV", Constants.ElevatorConstants.K_D_ELV);
        elevatorController.setPID(Constants.ElevatorConstants.K_P_ELV, Constants.ElevatorConstants.K_I_ELV, Constants.ElevatorConstants.K_D_ELV);

        dashboard.update();
    }

    @Override
    public void periodic() {
        updateTelemetry();
    }

}
