package org.firstinspires.ftc.teamcode.commands.teleopCommands.arm;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.teleOpArcade;


public class ArmMethods {

    public ArmMethods() {}
    public int rotateArm(double rotation) {

        if (teleOpArcade.target >= Constants.ArmConstants.ARM_MIN_POSITION && teleOpArcade.target <= Constants.ArmConstants.ARM_MAX_POSITION) {
            teleOpArcade.target += (int) rotation;
        }
        teleOpArcade.target += (int) rotation;

        // timer.reset();
        teleOpArcade.m_Hardware.setPowerArm(teleOpArcade.m_Hardware.pid(teleOpArcade.target));
        //armHex.setPower(pid(target));

        return teleOpArcade.target;
    }

    public double moveClaw(boolean direction) {
        if (teleOpArcade.clawTarget >= Constants.ArmConstants.CLAW_MIN_POSITION && direction) {
            teleOpArcade.clawTarget -= Constants.ArmConstants.CLAW_VELOCITY;
        }

        else if (teleOpArcade.clawTarget <= Constants.ArmConstants.CLAW_MAX_POSITION && !direction) {
            teleOpArcade.clawTarget += Constants.ArmConstants.CLAW_VELOCITY;
        }
        teleOpArcade.m_Hardware.setPositionClaw(teleOpArcade.clawTarget);

        return teleOpArcade.clawTarget;
    }

}
