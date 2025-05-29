package org.firstinspires.ftc.teamcode.commands.teleop.claw;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;

public class OpenClaw extends CommandBase {
    private final ClawSubsystem m_clawSubsystem;
    private boolean endCommand = false;
    public OpenClaw(ClawSubsystem m_clawSubsystem) {
        this.m_clawSubsystem = m_clawSubsystem;
        addRequirements();
    }

    @Override
    public void initialize() {
        m_clawSubsystem.setClawPosition(Constants.ClawConstants.SERVO_OPEN);
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
