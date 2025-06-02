package org.firstinspires.ftc.teamcode.commands.teleop.claw;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;

public class PinchClaw extends CommandBase {
    private final ClawSubsystem m_clawSubsystem;
    public PinchClaw(ClawSubsystem m_clawSubsystem) {
        this.m_clawSubsystem = m_clawSubsystem;
        addRequirements(m_clawSubsystem);
    }

    @Override
    public void initialize() {
        m_clawSubsystem.setClawPosition(Constants.ClawConstants.SERVO_CLOSED);
    }

    @Override
    public void execute() {
        m_clawSubsystem.goToPosition();
    }
    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return m_clawSubsystem.atTargetPosition();
    }


}
