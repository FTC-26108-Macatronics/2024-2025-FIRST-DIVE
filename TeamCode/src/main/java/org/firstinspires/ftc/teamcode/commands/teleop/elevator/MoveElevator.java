package org.firstinspires.ftc.teamcode.commands.teleop.elevator;

import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.ElevatorSubsystem;

public class MoveElevator extends CommandBase {

    private final ElevatorSubsystem m_elevatorSubsystem;
    private boolean endCommand = false;

    public MoveElevator(ElevatorSubsystem m_elevatorSubsystem) {
        this.m_elevatorSubsystem = m_elevatorSubsystem;
        addRequirements(m_elevatorSubsystem);

    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        m_elevatorSubsystem.goToSetpoint();
    }


    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return endCommand;
    }

}
