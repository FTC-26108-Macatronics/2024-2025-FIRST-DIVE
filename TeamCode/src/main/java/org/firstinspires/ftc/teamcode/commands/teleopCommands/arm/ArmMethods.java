package org.firstinspires.ftc.teamcode.commands.teleopCommands.arm;

import org.firstinspires.ftc.teamcode.teleOpArcade;

public class ArmMethods {
    public ArmMethods() {}
    public void armRotation(double controllerInput, int max){

        // use custom mapVal(); and test
        teleOpArcade.m_Hardware.setPositionArmHex((int)((controllerInput * 360) + max));
    }

}
