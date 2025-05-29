package org.firstinspires.ftc.teamcode.commands.teleop.elevator;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ElevatorSubsystem;

public class ChangeElevatorPosition extends CommandBase {

    private final ElevatorSubsystem m_elevatorSubsystem;
    private final double setpoint;
    private boolean endCommand = false;

    public ChangeElevatorPosition(ElevatorSubsystem m_elevatorSubsystem, double setpoint) {
        this.m_elevatorSubsystem = m_elevatorSubsystem;
        this.setpoint = setpoint;
        addRequirements();

    }

    @Override
    public void initialize() {
        m_elevatorSubsystem.setPosition(setpoint);
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
