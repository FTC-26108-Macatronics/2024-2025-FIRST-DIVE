package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.teleopCommands.arm.ArmMethods;
import org.firstinspires.ftc.teamcode.commands.teleopCommands.drive.DriveMethods;
import org.firstinspires.ftc.teamcode.initialize.Hardware;

@TeleOp
public class teleOpArcade extends OpMode {
    private DriveMethods m_DriveMethods;
    private ArmMethods m_ArmMethods;
    public static Hardware m_Hardware;
    private double drive, turn, strafePwr, armRotation;
    public static double clawTarget;
    public static int target;

    void getTelemetry() {
        telemetry.addData("Drive", drive);
        telemetry.addData("Turn", turn);
        telemetry.addData("Strafe power", strafePwr);
        telemetry.addData("Arm rotation", armRotation);
        telemetry.addData("Arm position ", m_Hardware.getArmPosition());
        telemetry.addData("Arm target", target);

        if (target >= Constants.ArmConstants.ARM_MIN_POSITION && target <= Constants.ArmConstants.ARM_MAX_POSITION) {
            telemetry.addData("Arm movable", true);
        } else {
            telemetry.addData("Arm movable", false);
        }

        telemetry.addData("Claw motion", gamepad1.left_bumper || gamepad1.right_bumper);
        telemetry.addData("Claw target", clawTarget);

        if (clawTarget >= Constants.ArmConstants.CLAW_MIN_POSITION && clawTarget <= Constants.ArmConstants.CLAW_MAX_POSITION) {
            telemetry.addData("Claw movable", true);
        } else {
            telemetry.addData("Claw movable", false);
        }
        telemetry.update();
    }

    float getControllerOneLeftStickY() {
        return -gamepad1.left_stick_y;
    }

    float getControllerOneRightStickX() {
        return gamepad1.right_stick_x;
    }
    float getControllerOneLeftStickX() {
        return -gamepad1.left_stick_x;
    }

    float getArmRotationValues() {
        return gamepad1.left_trigger - gamepad1.right_trigger;
    }
    boolean getControllerOneLeftBumper() {
        return gamepad1.left_bumper;
    }

    boolean getControllerOneRightBumper() {
        return gamepad1.right_bumper;
    }


    @Override
    public void init() {
        m_Hardware = new Hardware(this);
        m_ArmMethods = new ArmMethods();
        m_DriveMethods = new DriveMethods();
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Running");

        drive = getControllerOneLeftStickY();
        turn = getControllerOneRightStickX();
        strafePwr = getControllerOneLeftStickX();
        armRotation = getArmRotationValues();

        m_DriveMethods.strafe(strafePwr);
        m_DriveMethods.driveArcade(drive, turn);

        target = m_ArmMethods.rotateArm(armRotation);

        m_ArmMethods.rotateArm(armRotation);

        if (getControllerOneLeftBumper()) {
            clawTarget = m_ArmMethods.moveClaw(true);
        }

        else if (getControllerOneRightBumper()) {
            clawTarget = m_ArmMethods.moveClaw(false);
        }

        getTelemetry();

    }
}
