package org.firstinspires.ftc.teamcode.commands.teleop.arm;

import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ChangeArmPosition extends CommandBase {

    private final ArmSubsystem m_armSubsystem;
    private final ArmSubsystem.ArmPosition position;
    private boolean endCommand = false;

    public ChangeArmPosition(ArmSubsystem m_armSubsystem, ArmSubsystem.ArmPosition position) {
        this.m_armSubsystem = m_armSubsystem;
        this.position = position;
        addRequirements();

    }

    @Override
    public void initialize() {
        m_armSubsystem.setPosition(position);
        endCommand = true;
    }

    @Override
    public void execute() {
    }


    @Override
    public void end(boolean interrupted) {
//        m_armSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return endCommand;
    }

}
