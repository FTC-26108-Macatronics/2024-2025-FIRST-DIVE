package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;


public class ElevatorSubsystem extends SubsystemBase {

    private final DcMotorEx liftMotor;

    public ElevatorSubsystem(final HardwareMap hMap) {
        liftMotor = hMap.get(DcMotorEx.class, "lift");
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
        int liftState = 0;
        boolean override = false;

        if (gamepad1.options) {
            override = true;
            telemetry.addData("!OVERRIDE", "ACTIVE!");
        }

        if ((gamepad1.dpad_up && !gamepad1.dpad_down) || (gamepad2.dpad_up && !(gamepad2.dpad_down))) {
            liftState = 2;
        } else if ((gamepad1.dpad_down && !gamepad1.dpad_up) || (gamepad2.dpad_down && !(gamepad2.dpad_up)))  {
            liftState = 1;
        }

        moveLift(liftState, override);

    }

}
