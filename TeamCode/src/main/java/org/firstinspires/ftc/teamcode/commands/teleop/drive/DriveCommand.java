package org.firstinspires.ftc.teamcode.commands.teleop.drive;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

public class DriveCommand extends CommandBase {

    private final DriveSubsystem m_driveSubsystem;
    public DriveCommand(DriveSubsystem m_driveSubsystem) {
        this.m_driveSubsystem = m_driveSubsystem;
        addRequirements(m_driveSubsystem);

    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        double drive = Math.pow(-Robot.m_driverController.getLeftY(), 3);
        double turn = Math.pow(Robot.m_driverController.getRightX(), 3);
        double strafe = Math.pow(-Robot.m_driverController.getLeftX(), 3);

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
