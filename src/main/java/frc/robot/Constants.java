package frc.robot;

// Define constants here as `static final`s
public final class Constants {
    public static final class IntakeConstants {
        public static final int kWristRange = 125;
    }

    public static final class DriveConstants {
        public static final double kMaxSpeed = 4.0; // Meters per second
        public static final double kMaxAngularSpeed = 2 * Math.PI; // One rotation per second

        public static final double kGearRatio = 10.71;
        // Meters (6 inch/2)
        public static final double kWheelRadius = 0.1524 / 2;
        public static final double kEncoderResolution = 2048;
        public static final double kDistancePerTick = (2 * Math.PI * kWheelRadius / kEncoderResolution) / kGearRatio;
        // Meters
        public static final double kTrackWidth = .6477 * 2;
    }
}