package org.firstinspires.ftc.teamcode;

public class Constants {

    public static class DriveConstants {
        public static final double MAX_PWR_DT = 1;
    }

    public static class ArmConstants {
        public static final double K_P_ARM = 0.05;

        public static final double K_I_ARM = 0.003;

        public static final double K_D_ARM = 0.003;

        public static final int MAX_ARM = 420;
        public static final int MIN_ARM = 0;

    }


    public static class ClawConstants {
        public static final double PWR_CLAW = 1.0;
        public static final double SERVO_OPEN = 0.4;
        public static final double SERVO_CLOSED = 0.7;

        public static final double K_P_CLAW = 0.1;
        public static final double K_I_CLAW = 0;
        public static final double K_D_CLAW = 0;

        public static final int MAX_CLAW = 0;
        public static final int MIN_CLAW = -180;

    }

    public static class ElevatorConstants {
        public static final double PWR_LIFT = 1;
        public static final int MAX_LIFT = 8400;
        public static final int MIN_LIFT = 0;

    }
}
