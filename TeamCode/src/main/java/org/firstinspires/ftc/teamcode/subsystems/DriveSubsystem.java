package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static org.firstinspires.ftc.teamcode.Constants.DriveConstants.DRIVE_CONSTRAINTS;
import static org.firstinspires.ftc.teamcode.Constants.DriveConstants.MAX_PWR_DT;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ProfiledPIDController;
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

    private final DcMotorEx leftDrive;
    private final DcMotorEx rightDrive;
    private final DcMotorEx transverseDrive;
    private final ProfiledPIDController driveController;
    private final ProfiledPIDController headingController;
    private final ProfiledPIDController strafeController;
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

        driveController = new ProfiledPIDController(Constants.DriveConstants.K_P_DRIVE, Constants.DriveConstants.K_I_DRIVE, Constants.DriveConstants.K_D_DRIVE, DRIVE_CONSTRAINTS);
        headingController = new ProfiledPIDController(Constants.DriveConstants.K_P_TURN, Constants.DriveConstants.K_I_TURN, Constants.DriveConstants.K_D_TURN, DRIVE_CONSTRAINTS);
        strafeController = new ProfiledPIDController(Constants.DriveConstants.K_P_STRAFE, Constants.DriveConstants.K_I_STRAFE, Constants.DriveConstants.K_D_STRAFE, DRIVE_CONSTRAINTS);
    }

    public void drive(double drive) {
        double leftPwr = drive;
        double rightPwr = drive;
        double max = Math.max(Math.abs(leftPwr), Math.abs(rightPwr));

//        if (max > MAX_PWR_DT) {
//            leftPwr /= max;
//            rightPwr /= max;
//        }

        leftDrive.setPower(leftPwr);
        rightDrive.setPower(rightPwr);
    }
    public void drive(double drive, double strafe) {
        double leftPwr = drive;
        double rightPwr = drive;
        double max = Math.max(Math.abs(leftPwr), Math.abs(rightPwr));

//        if (max > MAX_PWR_DT) {
//            leftPwr /= max;
//            rightPwr /= max;
//        }

        leftDrive.setPower(leftPwr);
        rightDrive.setPower(rightPwr);
        transverseDrive.setPower(strafe);
    }
    public void drive(double drive, double strafe, double turn) {
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

    public void turn(double power) {
        leftDrive.setPower(power);
        rightDrive.setPower(-power);
    }

    public void driveToDistance(double distMeters) {
        driveController.setGoal(distMeters);

        double driveDistance = driveController.calculate(getDrivePosition());

        drive(driveDistance);
    }

    public void drivetoDistance(double distMeters, double strafeMeters) {
        driveController.setGoal(distMeters);
        strafeController.setGoal(strafeMeters);

        double driveDistance = driveController.calculate(getDrivePosition());
        double strafeDistance = strafeController.calculate(getStrafePosition());

        drive(driveDistance, strafeDistance);
    }

    public void driveToDistance(double distMeters, double strafeMeters, double angle) {
        driveController.setGoal(distMeters);
        strafeController.setGoal(strafeMeters);
        headingController.setGoal(angle);

        double driveDistance = driveController.calculate(getDrivePosition());
        double strafeDistance = strafeController.calculate(getStrafePosition());
        double angleDifference = headingController.calculate(getRotation());

        drive(driveDistance, strafeDistance, angleDifference);
    }

    public double getAngularVelocity() {
        return imu.getRobotAngularVelocity(AngleUnit.DEGREES).zRotationRate;
    }
    public double getRotation() {
        return imu.getRobotYawPitchRollAngles().getYaw();
    }
    public double getDrivePosition() {
        return rightDrive.getCurrentPosition();
    }
    public double getStrafePosition() {
        return transverseDrive.getCurrentPosition();
    }

    public void resetEncoders() {
        leftDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        transverseDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void zeroGyro() {
        imu.resetYaw();
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
