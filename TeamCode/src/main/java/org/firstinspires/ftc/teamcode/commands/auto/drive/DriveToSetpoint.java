package org.firstinspires.ftc.teamcode.commands.auto.drive;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDController;

import org.firstinspires.ftc.teamcode.RobotContainer;

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
        RobotContainer.m_driveSubsystem.resetGyro();
        RobotContainer.m_driveSubsystem.resetEncoders();

        driveController = RobotContainer.m_driveSubsystem.driveController;
        strafeController = RobotContainer.m_driveSubsystem.strafeController;

        driveController.setSetPoint(driveSetpoint);
        strafeController.setSetPoint(strafeSetpoint);
    }

    @Override
    public void execute() {
        double drivePower = driveController.calculate(RobotContainer.m_driveSubsystem.getDriveEncoderReading());
        double strafePower = strafeController.calculate(RobotContainer.m_driveSubsystem.getStrafeEncoderReading());

        if (driveController.atSetPoint()) {
            drivePower = 0;
        }
        if (strafeController.atSetPoint()) {
            strafePower = 0;
        }

        RobotContainer.m_driveSubsystem.drive(drivePower, 0, strafePower);
    }
    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return driveController.atSetPoint() && strafeController.atSetPoint();
    }

}
