package org.firstinspires.ftc.teamcode.commands.teleop.elevator;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.subsystems.ElevatorSubsystem;

public class MoveElevatorUp extends CommandBase {

    private final ElevatorSubsystem m_elevatorSubsystem;
    private final GamepadEx m_driverController;

    public MoveElevatorUp(ElevatorSubsystem m_elevatorSubsystem, GamepadEx m_driverController) {
        this.m_elevatorSubsystem = m_elevatorSubsystem;
        this.m_driverController = m_driverController;
        addRequirements(m_elevatorSubsystem);

    }

    @Override
    public void initialize() {
//        m_elevatorSubsystem.stopLift();

    }

    @Override
    public void execute() {

//        m_elevatorSubsystem.moveLift(1, Robot.m_driverController.getButton(GamepadKeys.Button.START));
        m_elevatorSubsystem.setLift(1);

    }


    @Override
    public void end(boolean interrupted) {
        m_elevatorSubsystem.setLift(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
