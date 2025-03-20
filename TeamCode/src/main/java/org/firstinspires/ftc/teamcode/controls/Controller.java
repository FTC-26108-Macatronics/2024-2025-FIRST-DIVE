package org.firstinspires.ftc.teamcode.controls;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.arcrobotics.ftclib.command.button.Trigger;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Controller {

    static Gamepad trigger;
    public Controller() {
        trigger = new Gamepad();
        trigger.setGamepadId(gamepad1.getGamepadId());
    }

    public static boolean getRightTrigger() {

        return trigger.right_trigger > 3;
    }

    public static boolean getLeftTrigger() {
        return trigger.left_trigger > 3;
    }



}
