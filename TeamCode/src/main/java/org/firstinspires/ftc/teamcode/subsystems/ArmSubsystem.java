package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Constants;


public class ArmSubsystem extends SubsystemBase {

    private final DcMotorEx armMotor;

    private double targetArm;
    private double lastErrorArm;
    private double iArm;
    private ElapsedTime timerArm;

    public ArmSubsystem(final OpMode opMode) {
        armMotor = opMode.hardwareMap.get(DcMotorEx.class, "arm");
        armMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        armMotor.setDirection(DcMotorEx.Direction.FORWARD);
        armMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        targetArm = 0;
        lastErrorArm = 0;
        iArm = 0;
        timerArm = new ElapsedTime();
    }

    public int getArm() {
        return armMotor.getCurrentPosition();
    }

    public void setArm(double pwr) {
        armMotor.setPower(pwr);
    }

    public void moveArm(double rotation, boolean override) {
        if (((rotation > 0 && getArm() < Constants.ArmConstants.MAX_ARM) || (rotation < 0 && getArm() > Constants.ArmConstants.MIN_ARM)) || override) {
            targetArm += rotation;
        }

        double error = targetArm - getArm();
        iArm += error * timerArm.seconds();

        setArm((Constants.ArmConstants.K_P_ARM * error) + (Constants.ArmConstants.K_I_ARM * iArm) + (Constants.ArmConstants.K_D_ARM * ((error - lastErrorArm) / timerArm.seconds())));

        lastErrorArm = error;
        timerArm.reset();
    }


    @Override
    public void periodic() {


    }

}
