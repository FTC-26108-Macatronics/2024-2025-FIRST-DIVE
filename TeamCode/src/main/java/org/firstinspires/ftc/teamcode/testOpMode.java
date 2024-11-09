package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.initialize.Hardware;

public class testOpMode extends OpMode {
    @Override
    public void init() {
        Hardware hardware = new Hardware(this);
        init();
    }

    public void loop() {

    }
}
