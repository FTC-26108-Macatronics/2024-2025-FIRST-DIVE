package org.firstinspires.ftc.teamcode.commands.teleopCommands.arm;
import static org.firstinspires.ftc.teamcode.Constants.DriveConstants;
import org.firstinspires.ftc.teamcode.teleOpArcade;


public class ArmMethods {
    public ArmMethods() {}
    public int rotateArm(double rotation) {

        /*if (target >= ARM_MIN_POSITION && target <= ARM_MAX_POSITION) {
            target += (int) rotation;
        }*/
        teleOpArcade.target += (int) rotation;

        // timer.reset();
        arm.setPower(pid(target));

        return target;
    }

}
