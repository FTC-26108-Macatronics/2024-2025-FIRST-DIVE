package org.firstinspires.ftc.teamcode.commands.teleop.elevator;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.subsystems.ElevatorSubsystem;

public class MoveElevator extends CommandBase {

    private final ElevatorSubsystem m_elevatorSubsystem;
    private final double setpoint;

    public MoveElevator(ElevatorSubsystem m_elevatorSubsystem, double setpoint) {
        this.m_elevatorSubsystem = m_elevatorSubsystem;
        this.setpoint = setpoint;
        addRequirements(m_elevatorSubsystem);

    }

    @Override
    public void initialize() {
        m_elevatorSubsystem.setPosition(setpoint);

    }

    @Override
    public void execute() {
    }


    @Override
    public void end(boolean interrupted) {
//        m_elevatorSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
