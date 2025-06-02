package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.command.Command;
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

    private final Telemetry dashboard;
    public static DriveSubsystem m_driveSubsystem;
    public static ArmSubsystem m_armSubsystem;
    public static ClawSubsystem m_clawSubsystem;
    public static ClawRotationSubsystem m_clawRotationSubsystem;
    public static ElevatorSubsystem m_elevatorSubsystem;
    public static CommandScheduler commandScheduler;
    public static GamepadEx m_driverController;
    public static GamepadEx m_operatorController;
    private final Button DRIVER_LEFT_BUMPER;
    private final Trigger DRIVER_LEFT_TRIGGER;
    private final Trigger DRIVER_RIGHT_TRIGGER;
    private final Button DRIVER_BUTTON_A;
    private final Button DRIVER_BUTTON_B;
    private final Button DRIVER_BUTTON_X;
    private final Button DRIVER_BUTTON_Y;
    private final Button DRIVER_DPAD_DOWN;
    private final Button DRIVER_DPAD_UP;
    private final Button DRIVER_DPAD_LEFT;
    private final Button DRIVER_DPAD_RIGHT;

    public Robot() {
        dashboard = FtcDashboard.getInstance().getTelemetry();
        commandScheduler = CommandScheduler.getInstance();

        m_driveSubsystem = new DriveSubsystem(this, dashboard);
        m_armSubsystem = new ArmSubsystem(this, dashboard);
        m_clawSubsystem = new ClawSubsystem(this, dashboard);
        m_clawRotationSubsystem = new ClawRotationSubsystem(this, dashboard);
        m_elevatorSubsystem = new ElevatorSubsystem(this, dashboard);

        m_driverController = new GamepadEx(gamepad1);
        m_operatorController = new GamepadEx(gamepad2);

        DRIVER_LEFT_BUMPER = new GamepadButton(m_driverController, GamepadKeys.Button.LEFT_BUMPER);

        DRIVER_LEFT_TRIGGER = new Controller.ControllerTrigger(m_driverController, GamepadKeys.Trigger.LEFT_TRIGGER);
        DRIVER_RIGHT_TRIGGER = new Controller.ControllerTrigger(m_driverController, GamepadKeys.Trigger.RIGHT_TRIGGER);

        DRIVER_BUTTON_A = new GamepadButton(m_driverController, GamepadKeys.Button.A);
        DRIVER_BUTTON_B = new GamepadButton(m_driverController, GamepadKeys.Button.B);
        DRIVER_BUTTON_X = new GamepadButton(m_driverController, GamepadKeys.Button.X);
        DRIVER_BUTTON_Y = new GamepadButton(m_driverController, GamepadKeys.Button.Y);

        DRIVER_DPAD_UP = new GamepadButton(m_driverController, GamepadKeys.Button.DPAD_UP);
        DRIVER_DPAD_DOWN = new GamepadButton(m_driverController, GamepadKeys.Button.DPAD_DOWN);
        DRIVER_DPAD_LEFT = new GamepadButton(m_driverController, GamepadKeys.Button.DPAD_LEFT);
        DRIVER_DPAD_RIGHT = new GamepadButton(m_driverController, GamepadKeys.Button.DPAD_RIGHT);

        configureBindings();
    }

    @Override
    public void init() {

    }

    @Override
    public void init_loop() {
        commandScheduler.run();
    }

    @Override
    public void start() {
        configureBindings();
    }

    @Override
    public void loop() {
        commandScheduler.run();
    }

    @Override
    public void stop() {
        deconfigureBindings();
    }

    public void configureBindings() {
        m_driveSubsystem.setDefaultCommand(new DriveCommand(m_driveSubsystem));
        m_elevatorSubsystem.setDefaultCommand(new MoveElevator(m_elevatorSubsystem));
        m_armSubsystem.setDefaultCommand(new MoveArm(m_armSubsystem));
        m_clawRotationSubsystem.setDefaultCommand(new RotateClaw(m_clawRotationSubsystem));

        DRIVER_BUTTON_A.whenPressed(new ChangeElevatorPosition(m_elevatorSubsystem, ElevatorSubsystem.ElevatorPosition.HOME));
        DRIVER_BUTTON_B.whenPressed(new ChangeElevatorPosition(m_elevatorSubsystem, ElevatorSubsystem.ElevatorPosition.L1));
        DRIVER_BUTTON_X.whenPressed(new ChangeElevatorPosition(m_elevatorSubsystem, ElevatorSubsystem.ElevatorPosition.L2));
        DRIVER_BUTTON_Y.whenPressed(new ChangeElevatorPosition(m_elevatorSubsystem, ElevatorSubsystem.ElevatorPosition.L3));

        DRIVER_DPAD_DOWN.whenPressed(new ChangeArmPosition(m_armSubsystem, ArmSubsystem.ArmPosition.HOME));
        DRIVER_DPAD_LEFT.whenPressed(new ChangeArmPosition(m_armSubsystem, ArmSubsystem.ArmPosition.L1));
        DRIVER_DPAD_RIGHT.whenPressed(new ChangeArmPosition(m_armSubsystem, ArmSubsystem.ArmPosition.L2));
        DRIVER_DPAD_UP.whenPressed(new ChangeArmPosition(m_armSubsystem, ArmSubsystem.ArmPosition.L3));

//        DRIVER_BUTTON_A.whileHeld(new MoveClaw(m_clawSubsystem));
    }

    public void deconfigureBindings() {
        commandScheduler.cancelAll();
    }


}