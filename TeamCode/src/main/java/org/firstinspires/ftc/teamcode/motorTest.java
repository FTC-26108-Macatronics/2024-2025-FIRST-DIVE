package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class motorTest extends OpMode {
    private DcMotor motor = null;

    @Override
    public void init() {
        motor = hardwareMap.get(DcMotor.class, "testMotor");
    }

    @Override
    public void loop() {
        motor.setPower(0.3);
    }
}