package org.firstinspires.ftc.teamcode;

public final class Constants {
  public static class DriveConstants {
    public static final double strafeMultiplier = 0.5;
    public static final double driveMultiplier = 0.7;

    public static final double K_P = 0.2;
    public static final double K_I = 0.2;
    public static final double K_D = 0.2;

    public static final double liftTopLimiter = 0;
    public static final double liftBottomLimiter = 0;
  }
  public static class ArmConstants {
    public static final double CLAW_INIT_POSITION = 0.41,
                               CLAW_MIN_POSITION = 0.15,
                               CLAW_MAX_POSITION = 0.41,
                               clawTarget = CLAW_INIT_POSITION,
                               CLAW_VELOCITY = 0.01;
    public static final int ARM_INIT_POSITION = 0,
                            ARM_MIN_POSITION = 0,
                            ARM_MAX_POSITION = 235,
                            ENCODER_TARGET_POSITION = 10;
  }

  public static class AutonomousConstants {
  }

}
