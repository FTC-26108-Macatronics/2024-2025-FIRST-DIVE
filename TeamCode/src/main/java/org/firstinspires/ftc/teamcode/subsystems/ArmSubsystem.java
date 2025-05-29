package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.ProfiledPIDCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ProfiledPIDController;
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
    private final ProfiledPIDController armController;

    private final Telemetry dashboard;

    public ArmSubsystem(final OpMode opMode, Telemetry dashboard) {

        this.dashboard = dashboard;

        armMotor = opMode.hardwareMap.get(DcMotorEx.class, "arm");
        armMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        armMotor.setDirection(DcMotorEx.Direction.FORWARD);
        armMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        armController = new ProfiledPIDController(Constants.ArmConstants.K_P_ARM, Constants.ArmConstants.K_I_ARM, Constants.ArmConstants.K_D_ARM, Constants.ArmConstants.armConstraints);

        telemetry = Robot.dashboardTelemetry;
    }

    public int getArm() {
        return armMotor.getCurrentPosition();
    }

    public void setArm(double pwr) {
        armMotor.setPower(pwr);
    }

    public void moveArm(double rotation, boolean override) {
        armController.setGoal(rotation);
        if (!armController.atGoal()) {
            setArm(armController.calculate(armMotor.getCurrentPosition()));
        }
    }


    public void updateTelemetry() {
        dashboard.addData("Arm Position", armMotor.getCurrentPosition());
        dashboard.addData("Arm Current", armMotor.getCurrent(CurrentUnit.MILLIAMPS));

        dashboard.addData("Arm Error", armController.getPositionError());
        dashboard.addData("Arm Velocity", armMotor.getVelocity());
    }

    @Override
    public void periodic() {
        updateTelemetry();
    }

}
