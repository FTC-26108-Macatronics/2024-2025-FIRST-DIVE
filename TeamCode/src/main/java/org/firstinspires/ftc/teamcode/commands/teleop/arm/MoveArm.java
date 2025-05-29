package org.firstinspires.ftc.teamcode.commands.teleop.arm;
import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class MoveArm extends CommandBase {

    private final ArmSubsystem m_armSubsystem;
    private boolean endCommand = false;

    public MoveArm(ArmSubsystem m_armSubsystem) {
        this.m_armSubsystem = m_armSubsystem;
        addRequirements(m_armSubsystem);

    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        m_armSubsystem.goToSetpoint();
    }


    @Override
    public void end(boolean interrupted) {
//        m_armSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return endCommand;
    }

}
