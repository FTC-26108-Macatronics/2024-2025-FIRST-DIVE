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
import org.firstinspires.ftc.teamcode.commands.teleop.arm.MoveArmDown;
import org.firstinspires.ftc.teamcode.commands.teleop.arm.MoveArmUp;
import org.firstinspires.ftc.teamcode.commands.teleop.claw.MoveClaw;
import org.firstinspires.ftc.teamcode.commands.teleop.drive.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.elevator.MoveElevatorDown;
import org.firstinspires.ftc.teamcode.commands.teleop.elevator.MoveElevatorUp;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ElevatorSubsystem;

@TeleOp
public class Robot extends OpMode {

    public FtcDashboard dashboard;
    public static Telemetry dashboardTelemetry;
    public static DriveSubsystem m_driveSubsystem;
    public static ArmSubsystem m_armSubsystem;
    public static ClawSubsystem m_clawSubsystem;
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

    public static Button DRIVER_BUTTON_Y;

    public static Trigger moveArmDown;


    @Override
    public void init() {

        dashboard = FtcDashboard.getInstance();
        dashboardTelemetry = dashboard.getTelemetry();

        m_driveSubsystem = new DriveSubsystem(this);
        m_armSubsystem = new ArmSubsystem(this);
        m_clawSubsystem = new ClawSubsystem(this);
        m_elevatorSubsystem = new ElevatorSubsystem(this);
//        commandScheduler = CommandScheduler.getInstance();

        m_driveSubsystem.setDefaultCommand(new DriveCommand(m_driveSubsystem));


        m_driverController = new GamepadEx(gamepad1);
        m_operatorController = new GamepadEx(gamepad2);

        DRIVER_LEFT_BUMPER = new GamepadButton(m_driverController, GamepadKeys.Button.LEFT_BUMPER);
        DRIVER_DPAD_UP = new GamepadButton(m_driverController, GamepadKeys.Button.DPAD_UP);
        DRIVER_DPAD_DOWN = new GamepadButton(m_driverController, GamepadKeys.Button.DPAD_DOWN);
        DRIVER_LEFT_TRIGGER = new Controller.ControllerTrigger(m_driverController, GamepadKeys.Trigger.LEFT_TRIGGER);
        DRIVER_RIGHT_TRIGGER = new Controller.ControllerTrigger(m_driverController, GamepadKeys.Trigger.RIGHT_TRIGGER);
        DRIVER_BUTTON_A = new GamepadButton(m_driverController, GamepadKeys.Button.A);
        DRIVER_BUTTON_Y = new GamepadButton(m_driverController, GamepadKeys.Button.Y);

        m_driverController.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER);

        configureBindings();

    }

    @Override
    public void loop() {
        CommandScheduler.getInstance().run();
    }

    public void configureBindings() {
//        DRIVER_LEFT_BUMPER.whileHeld(new MoveClaw(m_clawSubsystem));
        DRIVER_DPAD_UP.whileHeld(new MoveElevatorUp(m_elevatorSubsystem,  m_driverController));
        DRIVER_DPAD_DOWN.whileHeld(new MoveElevatorDown(m_elevatorSubsystem, m_driverController));
        DRIVER_LEFT_TRIGGER.whileActiveContinuous(new MoveArmUp(m_armSubsystem, m_driverController));
        DRIVER_RIGHT_TRIGGER.whileActiveContinuous(new MoveArmDown(m_armSubsystem, m_driverController));
        DRIVER_BUTTON_A.whileHeld(new MoveClaw(m_clawSubsystem));

        m_driveSubsystem.setDefaultCommand(new DriveCommand(m_driveSubsystem));
    }

}