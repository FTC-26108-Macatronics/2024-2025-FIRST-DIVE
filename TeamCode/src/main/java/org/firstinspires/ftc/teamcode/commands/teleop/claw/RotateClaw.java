package org.firstinspires.ftc.teamcode.commands.teleop.claw;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ClawRotationSubsystem;

public class RotateClaw extends CommandBase {
    private final ClawRotationSubsystem m_clawRotationSubsystem;

    public RotateClaw(ClawRotationSubsystem m_clawRotationSubsystem) {
        this.m_clawRotationSubsystem = m_clawRotationSubsystem;
        addRequirements();

    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        m_clawRotationSubsystem.goToSetpointRotation();
    }
    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }


}
