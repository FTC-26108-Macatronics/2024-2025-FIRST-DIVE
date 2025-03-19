package org.firstinspires.ftc.teamcode.commands.drive;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

public class DriveCommand extends CommandBase {

    private DriveSubsystem m_driveSubsystem;

    public DriveCommand(DriveSubsystem m_driveSubsystem) {
        this.m_driveSubsystem = m_driveSubsystem;
        addRequirements();

    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        double drive = -gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;
        double strafe = -gamepad1.left_stick_x;

        m_driveSubsystem.drive(drive, turn, strafe);

    }
    @Override
    public void end(boolean interrupted) {
        m_driveSubsystem.drive(0, 0, 0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }


}
