package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;


public class ArmSubsystem extends SubsystemBase {

    private final DcMotorEx armMotor;
    private final Telemetry telemetry;
    private final PIDController armController;

    public ArmSubsystem(final OpMode opMode) {
        armMotor = opMode.hardwareMap.get(DcMotorEx.class, "arm");
        armMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        armMotor.setDirection(DcMotorEx.Direction.FORWARD);
        armMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        armController = new PIDController(Constants.ArmConstants.K_P_ARM, Constants.ArmConstants.K_I_ARM, Constants.ArmConstants.K_D_ARM);

        telemetry = Robot.dashboardTelemetry;
    }

    public int getArm() {
        return armMotor.getCurrentPosition();
    }

    public void setArm(double pwr) {
        armMotor.setPower(pwr);
    }

    public void moveArm(double rotation, boolean override) {
        armController.setSetPoint(rotation);
        if (!armController.atSetPoint()) {
            setArm(armController.calculate(armMotor.getCurrentPosition()));
        }
    }


    @Override
    public void periodic() {
        telemetry.addData("Arm Position", armMotor.getCurrentPosition());
        telemetry.addData("Arm Current", armMotor.getCurrent(CurrentUnit.MILLIAMPS));

        telemetry.addData("Arm Error", armController.getPositionError());
        telemetry.addData("Arm Velocity", armMotor.getVelocity());
    }

}
