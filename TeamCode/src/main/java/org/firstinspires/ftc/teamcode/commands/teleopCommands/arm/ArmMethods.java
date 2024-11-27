package org.firstinspires.ftc.teamcode.commands.teleopCommands.arm;

import org.firstinspires.ftc.teamcode.RobotContainer;

public class ArmMethods {
    public ArmMethods() {}
    public void armRotation(double controllerInput, int max){

        // use custom mapVal(); and test
        RobotContainer.m_Hardware.setPositionArmHex((int)((controllerInput*360)+max));
    }

}
