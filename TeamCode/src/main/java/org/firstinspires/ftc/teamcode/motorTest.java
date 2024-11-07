package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class motorTest extends OpMode {
    private DcMotor motor = null;

    @Override
    public void init() {
        motor = hardwareMap.get(DcMotor.class, "testMotor");
    }

    @Override
    public void loop() {
        motor.setPower(1.0);
    }
}