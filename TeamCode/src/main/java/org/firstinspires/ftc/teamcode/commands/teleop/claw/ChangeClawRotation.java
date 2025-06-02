package org.firstinspires.ftc.teamcode.commands.teleop.claw;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ClawRotationSubsystem;
public class ChangeClawRotation extends CommandBase {

    private final ClawRotationSubsystem m_clawRotationSubsystem;
    private final double angle;
    private boolean endCommand = false;

    public ChangeClawRotation(ClawRotationSubsystem m_clawRotationSubsystem, double angle) {
        this.m_clawRotationSubsystem = m_clawRotationSubsystem;
        this.angle = angle;
        addRequirements();

    }

    @Override
    public void initialize() {
        m_clawRotationSubsystem.setClawRotationTarget(angle);
        endCommand = true;

    }

    @Override
    public void execute() {
    }
    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return endCommand;
    }


}
