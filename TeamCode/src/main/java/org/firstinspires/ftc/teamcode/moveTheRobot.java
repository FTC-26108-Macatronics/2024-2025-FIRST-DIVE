
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.commands.teleopCommands.drive.DriveMethods;
import org.firstinspires.ftc.teamcode.initialize.Hardware;



@Autonomous
public class moveTheRobot extends LinearOpMode {

    public static Hardware m_Hardware;
    public static DriveMethods m_DriveMethods;

    @Override
    public void runOpMode() {
        m_Hardware = new Hardware(this);
        // m_ArmMethods = new ArmMethods();
        m_DriveMethods = new DriveMethods();
        waitForStart();
        telemetry.addData("Motor encoder reading", m_Hardware.getDrivePosition());

    }

}
