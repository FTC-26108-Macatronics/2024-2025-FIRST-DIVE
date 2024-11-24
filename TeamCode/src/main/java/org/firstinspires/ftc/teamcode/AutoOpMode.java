package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.initialize.Hardware;

@Autonomous
public class AutoOpMode extends OpMode {

    Hardware hardware = new Hardware(this);

    @Override
    public void init() {
        hardware.init();
    }

    @Override
    public void loop()  {
        if (hardware.ENCODER_TARGET_POSITION < Math.abs(hardware.getEncoderValues())) {
            requestOpModeStop();}
        hardware.strafe(-0.6);
        telemetry.addData("Encoder", hardware.getEncoderValues());
        telemetry.update();

    }
}
