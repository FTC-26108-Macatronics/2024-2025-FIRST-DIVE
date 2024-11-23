package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.initialize.Hardware;

@Autonomous
public class AutoOpMode extends LinearOpMode {

    Hardware hardware = new Hardware(this);

    @Override
    public void runOpMode() throws InterruptedException {

        hardware.init();
        waitForStart();
        hardware.driveArcade(5, 0);

        while (hardware.ENCODER_TARGET_POSITION >= hardware.getEncoderValues()) {
            hardware.driveArcade(0.6, 0);
            telemetry.addData("Encoder", hardware.getEncoderValues());
            telemetry.update();

        }
    }
}
