package org.firstinspires.ftc.teamcode.commands.arm;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

public class MoveArmUp extends CommandBase {

    private boolean override;

    private ArmSubsystem m_armSubsystem;

    public MoveArmUp(ArmSubsystem m_armSubsystem) {
        this.m_armSubsystem = m_armSubsystem;
        addRequirements();

    }

    @Override
    public void initialize() {
        override = false;

    }

    @Override
    public void execute() {

        if (gamepad1.options) {
            override = true;
            telemetry.addData("!OVERRIDE", "ACTIVE!");
        }

        double arm = gamepad1.right_trigger;
        m_armSubsystem.moveArm(arm, override);

    }
    @Override
    public void end(boolean interrupted) {

        m_armSubsystem.moveArm(0, false);
    }

    @Override
    public boolean isFinished() {
        return false;
    }


}
