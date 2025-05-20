package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

public class Controller {

    public static class ControllerTrigger extends Trigger {

        private final GamepadEx controller;
        private final GamepadKeys.Trigger triggerType;

        public ControllerTrigger(GamepadEx controller, GamepadKeys.Trigger triggerType) {
            this.controller = controller;
            this.triggerType = triggerType;
        }

        @Override
        public boolean get() {
            return controller.getTrigger(triggerType) > 0.1;
        }
    }

}
