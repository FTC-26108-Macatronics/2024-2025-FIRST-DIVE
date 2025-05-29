package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.ProfiledPIDCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ProfiledPIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;


public class ElevatorSubsystem extends SubsystemBase {

    private final DcMotorEx liftMotor;
    private final Telemetry dashboard;
    private final ProfiledPIDController elevatorController;
    private double setpoint = 0;

    public ElevatorSubsystem(final OpMode opMode, Telemetry dashboard) {
        this.dashboard = dashboard;
        elevatorController = new ProfiledPIDController(Constants.ElevatorConstants.K_P_ELV, Constants.ElevatorConstants.K_I_ELV, Constants.ElevatorConstants.K_D_ELV, Constants.ElevatorConstants.elvConstraints);

        liftMotor = opMode.hardwareMap.get(DcMotorEx.class, "lift");
        liftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        liftMotor.setDirection(DcMotorEx.Direction.FORWARD);
        liftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        dashboard.addData("KP ELV", Constants.ElevatorConstants.K_P_ELV);
        dashboard.addData("KI ELV", Constants.ElevatorConstants.K_I_ELV);
        dashboard.addData("KD ELV", Constants.ElevatorConstants.K_D_ELV);

    }
    public enum ElevatorPosition {
        L1,
        L2,
        L3,
        UNKNOWN
    }

    public void setPower(double power) {
        liftMotor.setPower(power);
    }

    public double getPosition() {
        return liftMotor.getCurrentPosition();
    }

    public double getVelocity() {
        return liftMotor.getVelocity();
    }

    public boolean atTargetHeight() {
        return Math.abs(getPositionInMeters() - setpoint) <= Constants.ElevatorConstants.ELEVATOR_ERROR_TOLERANCE;
    }

    public double getPositionInMeters() {
        return getPosition() * Constants.ElevatorConstants.ELEVATOR_METERS_PER_MOTOR_ROTATION;
    }

    public double getVelocityInMeters() {
        return getVelocity() * Constants.ElevatorConstants.ELEVATOR_METERS_PER_MOTOR_ROTATION;
    }

    public void stop() {
        liftMotor.setPower(0);
    }

    public void zeroElevator() {
        setPosition(0);
    }

    public void setPosition(double position) {
        if (position >= Constants.ElevatorConstants.MAX_LIFT_HEIGHT) {
            setpoint = Constants.ElevatorConstants.MAX_LIFT_HEIGHT;
        }
        else if (position <= Constants.ElevatorConstants.MIN_LIFT_HEIGHT) {
            setpoint = Constants.ElevatorConstants.MIN_LIFT_HEIGHT;
        }
        else {
            setpoint = position;
        }
        elevatorController.setGoal(setpoint);
    }

    public void goToSetpoint() {
        setPower(elevatorController.calculate(getPositionInMeters()));
    }

    public ElevatorPosition getElevatorEnumPosition() {
        double currentPosition = getPositionInMeters();

        if (Math.abs(currentPosition - Constants.ElevatorConstants.ELEVATOR_L1_HEIGHT) < Constants.ElevatorConstants.ELEVATOR_ERROR_TOLERANCE * 2) {
            return ElevatorPosition.L1;
        } else if (Math.abs(currentPosition - Constants.ElevatorConstants.ELEVATOR_L2_HEIGHT) < Constants.ElevatorConstants.ELEVATOR_ERROR_TOLERANCE * 2) {
            return ElevatorPosition.L2;
        } else if (Math.abs(currentPosition - Constants.ElevatorConstants.ELEVATOR_L3_HEIGHT) < Constants.ElevatorConstants.ELEVATOR_ERROR_TOLERANCE * 2) {
            return ElevatorPosition.L3;
        } else {
            return ElevatorPosition.UNKNOWN;
        }
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
        goToSetpoint();
    }

}
