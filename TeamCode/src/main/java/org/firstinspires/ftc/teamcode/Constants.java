package org.firstinspires.ftc.teamcode;

public final class Constants {
  public static class DriveConstants {
    private static final double strafeMultiplier = 0.5;
    private static final double driveMultiplier = 0.7;

    public static double getDriveMultiplier() {
      return driveMultiplier;
    }
    public static double getStrafeMultiplier() {
      return strafeMultiplier;
    }

  }

  public static class ArmConstants {
    public static int armTarget = 0;
  }

  public static class AutonomousConstants {
  }

}
