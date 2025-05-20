package org.firstinspires.ftc.teamcode.commands.auto.drive;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDController;

import org.firstinspires.ftc.teamcode.Robot;

public class DriveToSetpoint extends CommandBase {

    PIDController driveController;
    PIDController strafeController;

    private final double driveSetpoint;
    private final double strafeSetpoint;

    public DriveToSetpoint(double driveSetpoint, double strafeSetpoint) {
        this.driveSetpoint = driveSetpoint;
        this.strafeSetpoint = strafeSetpoint;
    }

    @Override
    public void initialize() {
        Robot.m_driveSubsystem.resetGyro();
        Robot.m_driveSubsystem.resetEncoders();

        driveController = Robot.m_driveSubsystem.driveController;
        strafeController = Robot.m_driveSubsystem.strafeController;

        driveController.setSetPoint(driveSetpoint);
        strafeController.setSetPoint(strafeSetpoint);
    }

    @Override
    public void execute() {
        double drivePower = driveController.calculate(Robot.m_driveSubsystem.getDriveEncoderReading());
        double strafePower = strafeController.calculate(Robot.m_driveSubsystem.getStrafeEncoderReading());

        if (driveController.atSetPoint()) {
            drivePower = 0;
        }
        if (strafeController.atSetPoint()) {
            strafePower = 0;
        }

        Robot.m_driveSubsystem.drive(drivePower, 0, strafePower);
    }
    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return driveController.atSetPoint() && strafeController.atSetPoint();
    }

}
