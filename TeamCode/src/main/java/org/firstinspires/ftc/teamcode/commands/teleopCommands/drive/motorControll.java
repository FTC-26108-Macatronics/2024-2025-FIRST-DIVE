package org.firstinspires.ftc.teamcode.commands.teleopCommands.drive;

import org.firstinspires.ftc.teamcode.initialize.Hardware;

public class motorControll extends Hardware {

    public void armRotation(double controllerInput, int max){

        // use custom mapVal(); and test
        armHex.setTargetPosition((int)((controllerInput*360)+max));
    }

}
