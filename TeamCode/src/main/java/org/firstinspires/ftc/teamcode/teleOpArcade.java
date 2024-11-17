package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Constants.DriveConstants.strafeMultiplier;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.teleopCommands.drive.DriveArcade;
import org.firstinspires.ftc.teamcode.commands.teleopCommands.drive.Strafe;
import org.firstinspires.ftc.teamcode.commands.teleopCommands.drive.motorControll;
import org.firstinspires.ftc.teamcode.initialize.Hardware;
import org.firstinspires.ftc.teamcode.Constants;
@TeleOp
public class teleOpArcade extends OpMode {
    private Hardware hardware = new Hardware(this);

    private final motorControll motorControll = new motorControll();
    private Strafe m_strafe = new Strafe();

    private DriveArcade m_driveArcade = new DriveArcade();
    double drive, turn, strafePwr;

    @Override
    public void init() {
        hardware.init();
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Running");

        drive = -gamepad1.left_stick_y;
        turn = gamepad1.right_stick_x;


        strafePwr = (gamepad1.dpad_left) ? (strafeMultiplier) : (gamepad1.dpad_right) ? (-strafeMultiplier) : (0);

        m_strafe.strafe(strafePwr);
        m_driveArcade.driveArcade(drive, turn);
        motorControll.armRotation(gamepad1.right_trigger, 90);

        telemetry.addData("D-Pad left", gamepad1.dpad_left);
        telemetry.addData("D-Pad right", gamepad1.dpad_right);
        telemetry.addData("Left joystick y", gamepad1.left_stick_y);
        telemetry.addData("Right joystick y", gamepad1.right_stick_y);
        telemetry.addData("Strafe power", strafePwr);
        telemetry.addData("Drive", drive);
        telemetry.addData("Turn", turn);
        telemetry.update();
    }
}
