package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.initialize.Hardware;

public class testOpMode extends OpMode {
    @Override
    public void init() {
        Hardware robot       = new Hardware(this);
    }

    public void loop() {

    }
}
