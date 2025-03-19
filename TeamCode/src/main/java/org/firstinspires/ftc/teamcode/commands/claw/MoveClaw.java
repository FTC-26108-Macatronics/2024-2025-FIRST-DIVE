package org.firstinspires.ftc.teamcode.commands.arm;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

public class MoveClaw extends CommandBase {


    private double targetClaw;
    private double iClaw;
    private double lastErrorClaw;

    private ElapsedTime timerClaw;

    private boolean clawState;
    private boolean override;

    private ClawSubsystem m_clawSubsystem;

    public MoveClaw(ClawSubsystem m_clawSubsystem) {
        this.m_clawSubsystem = m_clawSubsystem;
        addRequirements();

    }

    @Override
    public void initialize() {
        override = false;
        timerClaw = new ElapsedTime();

    }

    @Override
    public void execute() {

        if (gamepad1.options) {
            override = true;
            telemetry.addData("!OVERRIDE", "ACTIVE!");
        }

        int clawRState = 0;

        if (gamepad1.y && !gamepad1.a) {
            clawRState = 2;
        } else if (gamepad1.a && !gamepad1.y) {
            clawRState = 1;
        }

        if (gamepad1.left_bumper && !gamepad1.right_bumper) {
            clawState = true;
        } else if (gamepad1.right_bumper && !gamepad1.left_bumper) {
            clawState = false;
        }

        m_clawSubsystem.rotateClaw(clawRState, override);
        m_clawSubsystem.moveClaw(clawState);


    }
    @Override
    public void end(boolean interrupted) {

        m_clawSubsystem.rotateClaw(0, false);
        m_clawSubsystem.moveClaw(false);
    }

    @Override
    public boolean isFinished() {
        return false;
    }


}
