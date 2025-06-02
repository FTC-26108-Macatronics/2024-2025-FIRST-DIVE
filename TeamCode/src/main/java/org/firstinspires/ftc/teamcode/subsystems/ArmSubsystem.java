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
    private final Telemetry dashboard;
    private final ProfiledPIDController armController;
    private double setpoint = 0;

    private ArmPosition armPos = ArmPosition.HOME;

    public ArmSubsystem(final OpMode opMode, Telemetry dashboard) {

        this.dashboard = dashboard;

        armMotor = opMode.hardwareMap.get(DcMotorEx.class, "arm");
        armMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        armMotor.setDirection(DcMotorEx.Direction.FORWARD);
        armMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        armController = new ProfiledPIDController(Constants.ArmConstants.K_P_ARM, Constants.ArmConstants.K_I_ARM, Constants.ArmConstants.K_D_ARM, Constants.ArmConstants.armConstraints);
    }

    public enum ArmPosition {
        HOME,
        L1,
        L2,
        L3,
        UNKNOWN
    }

    public int getArmPositionDegrees() {
        return armMotor.getCurrentPosition() * 360;
    }

    public void setPower(double pwr) {
        armMotor.setPower(pwr);
    }

    public void zeroArm() {
        setPosition(ArmPosition.HOME);
    }

    public void setPosition(ArmPosition position) {
        switch (position) {
            case HOME:
                setpoint = 0;
                break;
            case L1:
                setpoint = Constants.ArmConstants.ARM_L1_HEIGHT;
            case L2:
                setpoint = Constants.ArmConstants.ARM_L2_HEIGHT;
                break;
            case L3:
                setpoint = Constants.ArmConstants.ARM_L3_HEIGHT;
                break;
            default: setpoint = 0;
        }
        setArmEnumPosition(position);
        armController.setGoal(setpoint);
    }

    public void setArmEnumPosition(ArmPosition position) {
        armPos = position;
    }

    public ArmPosition getArmEnumPosition() {
        return armPos;
    }


    public void goToSetpoint() {
        setPower(armController.calculate(getArmPositionDegrees()));
    }

    public void updateTelemetry() {
//        dashboard.addData("Arm Position", armMotor.getCurrentPosition());
//        dashboard.addData("Arm Current", armMotor.getCurrent(CurrentUnit.MILLIAMPS));
//
//        dashboard.addData("Arm Error", armController.getPositionError());
//        dashboard.addData("Arm Velocity", armMotor.getVelocity());
         dashboard.addData("KP ARM", Constants.ArmConstants.K_P_ARM);
         dashboard.addData("KI ARM", Constants.ArmConstants.K_I_ARM);
         dashboard.addData("KD ARM", Constants.ArmConstants.K_D_ARM);
         dashboard.update();
         armController.setPID(Constants.ArmConstants.K_P_ARM, Constants.ArmConstants.K_I_ARM, Constants.ArmConstants.K_D_ARM);

    }

    @Override
    public void periodic() {
        updateTelemetry();
    }

}
