package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.arm.MoveClaw;
import org.firstinspires.ftc.teamcode.commands.drive.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.elevator.MoveElevator;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ElevatorSubsystem;

@TeleOp
public class TeleOpMode extends OpMode {
    public static DriveSubsystem m_driveSubsystem;
    public static ArmSubsystem m_armSubsystem;
    public static ClawSubsystem m_clawSubsystem;
    public static ElevatorSubsystem m_elevatorSubsystem;
    public static CommandScheduler commandScheduler;
    public static GamepadEx m_driverController;
    public static GamepadEx m_operatorController;
    public static Button openClaw;
    public static Button moveElevatorUp;
    public static Button moveElevatorDown;
    public static Button moveArmUp;
    public static Button moveArmDown;

    @Override
    public void init() {
        m_driveSubsystem = new DriveSubsystem(this);
        m_armSubsystem = new ArmSubsystem(this);
        m_clawSubsystem = new ClawSubsystem(this);
        m_elevatorSubsystem = new ElevatorSubsystem(this);
        commandScheduler = CommandScheduler.getInstance();

        m_driveSubsystem.setDefaultCommand(new DriveCommand(m_driveSubsystem));

        m_driverController = new GamepadEx(gamepad1);
        m_operatorController = new GamepadEx(gamepad2);

        openClaw = new GamepadButton(m_operatorController, GamepadKeys.Button.LEFT_BUMPER);
        moveElevatorUp = new GamepadButton(m_operatorController, GamepadKeys.Button.DPAD_UP);
        moveElevatorDown = new GamepadButton(m_operatorController, GamepadKeys.Button.DPAD_DOWN);
    }

    @Override
    public void loop() {
        openClaw.whileHeld(new MoveClaw(m_clawSubsystem));
        moveElevatorDown.whileHeld(new MoveElevator(m_elevatorSubsystem));
        moveElevatorUp.whileHeld(new MoveElevator(m_elevatorSubsystem));


    }
}