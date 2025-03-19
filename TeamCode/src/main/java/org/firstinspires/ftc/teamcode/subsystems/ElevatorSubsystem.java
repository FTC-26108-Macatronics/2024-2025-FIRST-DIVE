package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;


public class ElevatorSubsystem extends SubsystemBase {

    private final DcMotorEx liftMotor;

    public ElevatorSubsystem(final OpMode opMode) {
        liftMotor = opMode.hardwareMap.get(DcMotorEx.class, "lift");
        liftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        liftMotor.setDirection(DcMotorEx.Direction.FORWARD);
        liftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    public int getLift() {
        return liftMotor.getCurrentPosition();
    }

    public void setLift(double pwr) {
        liftMotor.setPower(pwr);
    }

    public void moveLift(int state, boolean override) {
        double pwr = 0;

        if (state == 2 && (getLift() < Constants.ElevatorConstants.MAX_LIFT || override)) {
            pwr = Constants.ElevatorConstants.PWR_LIFT;
        }

        if (state == 1 && (getLift() > Constants.ElevatorConstants.MIN_LIFT || override)) {
            pwr = -Constants.ElevatorConstants.PWR_LIFT;
        }

        setLift(pwr);
    }


    @Override
    public void periodic() {
    }

}
