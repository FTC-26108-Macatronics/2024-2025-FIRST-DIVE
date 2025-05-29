package org.firstinspires.ftc.teamcode.commands.teleop.arm;

import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ChangeArmPosition extends CommandBase {

    private final ArmSubsystem m_armSubsystem;
    private final double setpoint;
    private boolean endCommand = false;

    public ChangeArmPosition(ArmSubsystem m_armSubsystem, double setpoint) {
        this.m_armSubsystem = m_armSubsystem;
        this.setpoint = setpoint;
        addRequirements();

    }

    @Override
    public void initialize() {
        m_armSubsystem.setArmPosition(setpoint);
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
