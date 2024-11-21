package org.firstinspires.ftc.teamcode.commands.teleopCommands.arm;

import org.firstinspires.ftc.teamcode.initialize.Hardware;

public class ArmMethods extends Hardware {
    public ArmMethods() {}
    public void armRotation(double controllerInput, int max){

        // use custom mapVal(); and test
        armHex.setTargetPosition((int)((controllerInput*360)+max));
    }

}
