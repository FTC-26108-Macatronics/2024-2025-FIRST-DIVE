package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Constants.DriveConstants.getStrafeMultiplier;

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
    private double drive, turn, strafePwr, clawTarget;
    public static int target;

    void getTelemetry() {
        telemetry.addData("D-Pad left", gamepad1.dpad_left);
        telemetry.addData("D-Pad right", gamepad1.dpad_right);
        telemetry.addData("Left joystick y", gamepad1.left_stick_y);
        telemetry.addData("Right joystick y", gamepad1.right_stick_y);
        telemetry.addData("Strafe power", strafePwr);
        telemetry.addData("Drive", drive);
        telemetry.addData("Turn", turn);
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

        m_DriveMethods.strafe(strafePwr);
        m_DriveMethods.driveArcade(drive, turn);
        m_ArmMethods.armRotation(gamepad1.right_trigger, 90);

        if (getControllerOneLeftBumper()) {
            clawTarget = m_Hardware.moveClaw(true);
        }

        else if (getControllerOneRightBumper()) {
            clawTarget = m_Hardware.moveClaw(false);
        }

        getTelemetry();

    }
}
