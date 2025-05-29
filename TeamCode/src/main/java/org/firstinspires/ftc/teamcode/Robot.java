package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.teleop.arm.ChangeArmPosition;
import org.firstinspires.ftc.teamcode.commands.teleop.arm.MoveArm;
import org.firstinspires.ftc.teamcode.commands.teleop.claw.MoveClaw;
import org.firstinspires.ftc.teamcode.commands.teleop.claw.RotateClaw;
import org.firstinspires.ftc.teamcode.commands.teleop.drive.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.elevator.ChangeElevatorPosition;
import org.firstinspires.ftc.teamcode.commands.teleop.elevator.MoveElevator;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ClawRotationSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ElevatorSubsystem;

@TeleOp
public class Robot extends OpMode {

    public FtcDashboard dashboard;
    public Telemetry dashboardTelemetry;
    public static DriveSubsystem m_driveSubsystem;
    public static ArmSubsystem m_armSubsystem;
    public static ClawSubsystem m_clawSubsystem;
    public static ClawRotationSubsystem m_clawRotationSubsystem;

    public static ElevatorSubsystem m_elevatorSubsystem;
    public static CommandScheduler commandScheduler;
    public static GamepadEx m_driverController;
    public static GamepadEx m_operatorController;
    public static Button DRIVER_LEFT_BUMPER;
    public static Button DRIVER_DPAD_UP;
    public static Button DRIVER_DPAD_DOWN;
    public static Trigger DRIVER_LEFT_TRIGGER;
    public static Trigger DRIVER_RIGHT_TRIGGER;
    public static Button DRIVER_BUTTON_A;
    public static Button DRIVER_BUTTON_B;
    public static Button DRIVER_BUTTON_X;
    public static Button DRIVER_BUTTON_Y;
    public static Button DRIVER_POV_DOWN;
    public static Button DRIVER_POV_UP;
    public static Button DRIVER_POV_LEFT;
    public static Button DRIVER_POV_RIGHT;

    public Robot() {
        dashboard = FtcDashboard.getInstance();
        dashboardTelemetry = dashboard.getTelemetry();

        m_driveSubsystem = new DriveSubsystem(this, dashboardTelemetry);
        m_armSubsystem = new ArmSubsystem(this, dashboardTelemetry);
        m_clawSubsystem = new ClawSubsystem(this, dashboardTelemetry);
        m_clawRotationSubsystem = new ClawRotationSubsystem(this, dashboardTelemetry);
        m_elevatorSubsystem = new ElevatorSubsystem(this, dashboardTelemetry);

        m_driverController = new GamepadEx(gamepad1);
        m_operatorController = new GamepadEx(gamepad2);

        DRIVER_LEFT_BUMPER = new GamepadButton(m_driverController, GamepadKeys.Button.LEFT_BUMPER);
        DRIVER_DPAD_UP = new GamepadButton(m_driverController, GamepadKeys.Button.DPAD_UP);
        DRIVER_DPAD_DOWN = new GamepadButton(m_driverController, GamepadKeys.Button.DPAD_DOWN);
        DRIVER_LEFT_TRIGGER = new Controller.ControllerTrigger(m_driverController, GamepadKeys.Trigger.LEFT_TRIGGER);
        DRIVER_RIGHT_TRIGGER = new Controller.ControllerTrigger(m_driverController, GamepadKeys.Trigger.RIGHT_TRIGGER);
        DRIVER_BUTTON_A = new GamepadButton(m_driverController, GamepadKeys.Button.A);
        DRIVER_BUTTON_B = new GamepadButton(m_driverController, GamepadKeys.Button.B);
        DRIVER_BUTTON_X = new GamepadButton(m_driverController, GamepadKeys.Button.X);
        DRIVER_BUTTON_Y = new GamepadButton(m_driverController, GamepadKeys.Button.Y);
        DRIVER_POV_UP = new GamepadButton(m_driverController, GamepadKeys.Button.DPAD_UP);
        DRIVER_POV_DOWN = new GamepadButton(m_driverController, GamepadKeys.Button.DPAD_DOWN);
        DRIVER_POV_LEFT = new GamepadButton(m_driverController, GamepadKeys.Button.DPAD_LEFT);
        DRIVER_POV_RIGHT = new GamepadButton(m_driverController, GamepadKeys.Button.DPAD_RIGHT);

        configureBindings();
    }

    @Override
    public void init() {

    }

    @Override
    public void init_loop() {
        m_driveSubsystem.periodic();
        m_armSubsystem.periodic();
        m_clawSubsystem.periodic();
        m_elevatorSubsystem.periodic();
    }

    @Override
    public void loop() {
        CommandScheduler.getInstance().run();
    }

    public void configureBindings() {
        m_driveSubsystem.setDefaultCommand(new DriveCommand(m_driveSubsystem));
        m_elevatorSubsystem.setDefaultCommand(new MoveElevator(m_elevatorSubsystem));
        m_armSubsystem.setDefaultCommand(new MoveArm(m_armSubsystem));
        m_clawSubsystem.setDefaultCommand(new MoveClaw(m_clawSubsystem));
        m_clawRotationSubsystem.setDefaultCommand(new RotateClaw(m_clawRotationSubsystem));

        DRIVER_BUTTON_A.whenPressed(new ChangeElevatorPosition(m_elevatorSubsystem, 0));
        DRIVER_BUTTON_B.whenPressed(new ChangeElevatorPosition(m_elevatorSubsystem, Constants.ElevatorConstants.ELEVATOR_L1_HEIGHT));
        DRIVER_BUTTON_X.whenPressed(new ChangeElevatorPosition(m_elevatorSubsystem, Constants.ElevatorConstants.ELEVATOR_L2_HEIGHT));
        DRIVER_BUTTON_Y.whenPressed(new ChangeElevatorPosition(m_elevatorSubsystem, Constants.ElevatorConstants.ELEVATOR_L3_HEIGHT));

        DRIVER_POV_DOWN.whenPressed(new ChangeArmPosition(m_armSubsystem, 0));
        DRIVER_POV_LEFT.whenPressed(new ChangeArmPosition(m_armSubsystem, Constants.ArmConstants.ARM_L1_HEIGHT));
        DRIVER_POV_RIGHT.whenPressed(new ChangeArmPosition(m_armSubsystem, Constants.ArmConstants.ARM_L2_HEIGHT));
        DRIVER_POV_UP.whenPressed(new ChangeArmPosition(m_armSubsystem, Constants.ArmConstants.ARM_L3_HEIGHT));

        DRIVER_BUTTON_A.whileHeld(new MoveClaw(m_clawSubsystem));
    }

}