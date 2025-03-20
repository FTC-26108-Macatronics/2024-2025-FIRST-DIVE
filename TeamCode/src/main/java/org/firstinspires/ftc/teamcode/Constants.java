package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;

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
    }

    @Config
    public static class ArmConstants {
        public static double K_P_ARM = 0.05;

        public static double K_I_ARM = 0.003;

        public static double K_D_ARM = 0.003;

        public static int MAX_ARM = 420;
        public static int MIN_ARM = 0;

    }
    @Config
    public static class ClawConstants {
        public static double PWR_CLAW = 1.0;
        public static double SERVO_OPEN = 0.4;
        public static double SERVO_CLOSED = 0.7;
        public static double K_P_CLAW = 0.1;
        public static double K_I_CLAW = 0;
        public static double K_D_CLAW = 0;
        public static int MAX_CLAW = 0;
        public static int MIN_CLAW = -180;

    }
    @Config
    public static class ElevatorConstants {
        public static double PWR_LIFT = 1;
        public static int MAX_LIFT = 8400;
        public static int MIN_LIFT = 0;

    }
    @Config
    public static class AutoConstants {
        public static double DRIVE_SETPOINT = 30;
    }

}
