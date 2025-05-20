package org.firstinspires.ftc.teamcode.commands.teleop.arm;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static org.firstinspires.ftc.teamcode.Robot.m_armSubsystem;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

public class MoveArmUp extends CommandBase {

    private boolean override;

    private final ArmSubsystem m_armSubsystem;
    private final GamepadEx m_driverController;

    public MoveArmUp(ArmSubsystem m_armSubsystem, GamepadEx m_driverController) {
        this.m_armSubsystem = m_armSubsystem;
        this.m_driverController = m_driverController;
        addRequirements();

    }

    @Override
    public void initialize() {
        override = false;
    }

    @Override
    public void execute() {
        double arm = m_driverController.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER);

        Robot.m_armSubsystem.setArm(1);


    }
    @Override
    public void end(boolean interrupted) {


        m_armSubsystem.setArm(0);
//        m_armSubsystem.moveArm(0, false);
    }

    @Override
    public boolean isFinished() {
        return false;
    }


}
