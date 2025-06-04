package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static org.firstinspires.ftc.teamcode.Constants.DriveConstants.MAX_PWR_DT;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.Constants;


public class DriveSubsystem extends SubsystemBase {

    public final DcMotorEx leftDrive;
    public final DcMotorEx rightDrive;
    public final DcMotorEx transverseDrive;
    public final PIDController driveController;
    public final PIDController turnController;
    public final PIDController strafeController;

    private final IMU imu;
    private final Telemetry dashboard;


    public DriveSubsystem(final OpMode opMode, final Telemetry dashboard) {

        this.dashboard = dashboard;

        leftDrive = opMode.hardwareMap.get(DcMotorEx.class, "leftDrive");
        rightDrive = opMode.hardwareMap.get(DcMotorEx.class, "rightDrive");
        transverseDrive = opMode.hardwareMap.get(DcMotorEx.class, "transverseDrive");
        imu = opMode.hardwareMap.get(IMU.class, "imu");

        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.FORWARD;
        RevHubOrientationOnRobot.UsbFacingDirection  usbDirection  = RevHubOrientationOnRobot.UsbFacingDirection.DOWN;
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);

        imu.initialize(new IMU.Parameters(orientationOnRobot));
        imu.resetYaw();

        leftDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        transverseDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        leftDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        transverseDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        leftDrive.setDirection(DcMotorEx.Direction.FORWARD);
        rightDrive.setDirection(DcMotorEx.Direction.REVERSE);
        transverseDrive.setDirection(DcMotorEx.Direction.REVERSE);

        leftDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        transverseDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        driveController = new PIDController(Constants.DriveConstants.K_P_DRIVE, Constants.DriveConstants.K_I_DRIVE, Constants.DriveConstants.K_D_DRIVE);
        turnController = new PIDController(Constants.DriveConstants.K_P_TURN, Constants.DriveConstants.K_I_TURN, Constants.DriveConstants.K_D_TURN);
        strafeController = new PIDController(Constants.DriveConstants.K_P_STRAFE, Constants.DriveConstants.K_I_STRAFE, Constants.DriveConstants.K_D_STRAFE);
    }
    public void drive(double drive, double turn, double strafe) {
        double leftPwr = drive + turn;
        double rightPwr = drive - turn;
        double max = Math.max(Math.abs(leftPwr), Math.abs(rightPwr));

//        if (max > MAX_PWR_DT) {
//            leftPwr /= max;
//            rightPwr /= max;
//        }

        leftDrive.setPower(leftPwr);
        rightDrive.setPower(rightPwr);
        transverseDrive.setPower(strafe);

    }

    public void resetGyro() {
        imu.resetYaw();
    }
    public double getAngularVelocity() {
        return imu.getRobotAngularVelocity(AngleUnit.DEGREES).zRotationRate;
    }
    public double getRotation() {
        return imu.getRobotYawPitchRollAngles().getYaw();
    }
    public double getDriveEncoderReading() {
        return rightDrive.getCurrentPosition();
    }

    public double getStrafeEncoderReading() {
        return transverseDrive.getCurrentPosition();
    }

    public void resetEncoders() {
        leftDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        transverseDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void updateTelemetry() {
        dashboard.addData("gyro", getRotation());
        dashboard.addData("Motor Velocity", leftDrive.getVelocity());
        dashboard.addData("Motor Current", leftDrive.getCurrent(CurrentUnit.MILLIAMPS));
        dashboard.addData("Current rotation", getRotation());
        dashboard.addData("Angular velocity", getAngularVelocity());
        dashboard.update();
    }

    @Override
    public void periodic() {
        updateTelemetry();
    }

}
