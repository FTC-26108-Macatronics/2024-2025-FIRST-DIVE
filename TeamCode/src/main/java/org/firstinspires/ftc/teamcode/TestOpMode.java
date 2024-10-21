package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

// Test auto OpMode by François, 2024-10-21

@Autonomous

public class TestOpMode extends LinearOpMode {

    @Override
    public void runOpMode() {
        Servo servoTest = hardwareMap.get(Servo.class, "servoTest");
        DistanceSensor colorSensorRange = hardwareMap.get(DistanceSensor.class, "colorSensorRange");
        DigitalChannel digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        digitalTouch.setMode(DigitalChannel.Mode.INPUT);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            servoTest.setPosition(0.5);
            telemetry.addData("Servo Position", servoTest.getPosition());
            telemetry.addData("Distance", colorSensorRange.getDistance(DistanceUnit.CM));
            if (!digitalTouch.getState()) {
                telemetry.addData("Button", "PRESSED");
            } else {
                telemetry.addData("Button", "NOT PRESSED");
            }
            telemetry.addData("Status", "Running");
            telemetry.update();
            }
    }
}
