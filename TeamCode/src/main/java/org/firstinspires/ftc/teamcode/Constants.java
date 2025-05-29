package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.trajectory.TrapezoidProfile;

@Config
public class Constants {

    @Config
    public static class DriveConstants {
        public static double MAX_PWR_DT = 1;
        public static double K_P_DRIVE = 0;
        public static double K_I_DRIVE = 0;
        public static double K_D_DRIVE = 0;

        public static double K_P_TURN = 0;
        public static double K_I_TURN = 0;
        public static double K_D_TURN = 0;

        public static double K_P_STRAFE = 0;
        public static double K_I_STRAFE = 0;
        public static double K_D_STRAFE = 0;

        public static final double WHEEL_DIAMETER = 5.2; // in mm
        public static final double COUNTS_PER_MOTOR_REV = 28.0;
        public static final double DRIVE_GEAR_REDUCTION = 30.24;
        public static final double WHEEL_CIRCUMFERENCE_MM = WHEEL_DIAMETER * 3.14; // in mm
        public static final double COUNTS_PER_WHEEL_REV = COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION;
        public static final double COUNTS_PER_CM = (COUNTS_PER_WHEEL_REV / WHEEL_CIRCUMFERENCE_MM) * 0.1;
    }

    @Config
    public static class ArmConstants {
        public static double K_P_ARM = 0.05; //to tune

        public static double K_I_ARM = 0.003; // to tune

        public static double K_D_ARM = 0.003; // to tune

        public static double ARM_MAX_VELOCITY = 3; // to tune
        public static double ARM_MAX_ACCLERATION = 3; // to tune
        public static TrapezoidProfile.Constraints armConstraints = new TrapezoidProfile.Constraints(ARM_MAX_VELOCITY, ARM_MAX_ACCLERATION); // to tune

        public static int MAX_ARM_HEIGHT = 420; //to tune
        public static int MIN_ARM_HEIGHT = 0; //to tune (degrees)

        public static double ARM_ERROR_TOLERANCE = 0.3; // to tune

        public static double ARM_L1_HEIGHT = 0.3; // to tune (degrees)
        public static double ARM_L2_HEIGHT = 0.3; // to tune (degrees)
        public static double ARM_L3_HEIGHT = 0.3; // to tune (degrees)

    }
    @Config
    public static class ClawConstants {
        public static double PWR_CLAW = 1.0;
        public static double SERVO_OPEN = 0.4;
        public static double SERVO_CLOSED = 0.7;
        public static double K_P_CLAW = 0.1;
        public static double K_I_CLAW = 0;
        public static double K_D_CLAW = 0;

        public static double CLAW_DROP_ROTATION = 0.5;

        public static double CLAW_PICKUP_ROTATION = 0.5;

        public static double CLAW_CARRY_ROTATION = 0.5;

        public static double CLAW_ERROR_TOLERANCE = 0.3; // to tune

        public static double CLAW_ROTATION_MAX_VELOCITY = 3; // to tune

        public static double CLAW_ROTATION_MAX_ACCLERATION = 3; // to tune
        public static TrapezoidProfile.Constraints clawRotationConstraints = new TrapezoidProfile.Constraints(CLAW_ROTATION_MAX_VELOCITY, CLAW_ROTATION_MAX_ACCLERATION); // to tune
        public static int MAX_CLAW_ROTATION = 0;
        public static int MIN_CLAW_ROTATION = -180;

    }
    @Config
    public static class ElevatorConstants {

        public static double K_P_ELV = 0.2; // to tune

        public static double K_I_ELV = 0.2; // to tune
        public static double K_D_ELV = 0.3; // to tune

        public static double ELV_MAX_VELOCITY = 3; // to tune

        public static double ELV_MAX_ACCLERATION = 3; // to tune
        public static TrapezoidProfile.Constraints elvConstraints = new TrapezoidProfile.Constraints(ELV_MAX_VELOCITY, ELV_MAX_ACCLERATION); // to tune

        public static int MAX_LIFT_HEIGHT = 8400; // to tune (meters)
        public static int MIN_LIFT_HEIGHT = 0; // to tune (meters)

        public static double ELEVATOR_METERS_PER_MOTOR_ROTATION = 0.5; // to tune

        public static double ELEVATOR_ERROR_TOLERANCE = 0.3; // to tune

        public static double ELEVATOR_L1_HEIGHT = 0.3; // to tune (meters)
        public static double ELEVATOR_L2_HEIGHT = 0.3; // to tune (meters)
        public static double ELEVATOR_L3_HEIGHT = 0.3; // to tune (meters)

    }
    @Config
    public static class AutoConstants {
        public static double DRIVE_SETPOINT = 30;
    }

}
