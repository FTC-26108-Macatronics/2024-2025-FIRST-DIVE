package org.firstinspires.ftc.teamcode.commands.elevator;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ElevatorSubsystem;

class MoveElevator extends CommandBase {

    private boolean override;

    private ElevatorSubsystem m_elevatorSubsystem;

    public MoveElevator(ElevatorSubsystem m_elevatorSubsystem) {
        this.m_elevatorSubsystem = m_elevatorSubsystem;
        addRequirements();

    }

    @Override
    public void initialize() {
        override = false;

    }

    @Override
    public void execute() {

        int liftState = 0;

        if (gamepad1.options) {
            override = true;
            telemetry.addData("!OVERRIDE", "ACTIVE!");
        }

        if ((gamepad1.dpad_up && !gamepad1.dpad_down) || (gamepad2.dpad_up && !(gamepad2.dpad_down))) {
            liftState = 2;
        } else if ((gamepad1.dpad_down && !gamepad1.dpad_up) || (gamepad2.dpad_down && !(gamepad2.dpad_up))) {
            liftState = 1;
        }

        m_elevatorSubsystem.moveLift(liftState, override);

    }


    @Override
    public void end(boolean interrupted) {

        m_elevatorSubsystem.moveLift(0, false);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
