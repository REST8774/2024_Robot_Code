package frc.robot;

/**
 * Helper class containing static methods for common operations.
 */
public class Helpers {

    /**
     * Modifies an input value to be within the range [0.0, 1.0).
     * 
     * @param input The input value.
     * @return The modified input value within the specified range.
     */
    public static double modRotations(double input) {
        // Ensure input is in the range [0.0, 1.0)
        input %= 1.0;
        if (input < 0.0) {
            input += 1.0;
        }
        return input;
    }

    /**
     * Modifies an input value to be within the range [0.0, 360.0).
     * 
     * @param input The input value.
     * @return The modified input value within the specified range.
     */
    public static double modDegrees(double input) {
        // Ensure input is in the range [0.0, 360.0)
        input %= 360.0;
        if (input < 0.0) {
            input += 360.0;
        }
        return input;
    }

    /**
     * Clamps a value to be within a specified range.
     * 
     * @param val The value to be clamped.
     * @param min The minimum allowed value.
     * @param max The maximum allowed value.
     * @return The clamped value within the specified range.
     */
    public static int clamp(int val, int min, int max) {
        // Ensure val is within the range [min, max]
        return Math.max(min, Math.min(max, val));
    }
}
