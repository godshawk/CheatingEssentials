package com.luna.lib.util.math;

/**
 * Created with IntelliJ IDEA.
 * User: audrey
 * Date: 9/19/13
 * Time: 3:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class PolarCoords {
    /**
     * Theta is in degrees
     * @param x
     * @param y
     * @return new double[] {r, theta}
     */
    public static double[] cartesianToPolar(double x, double y) {
        double r = Math.sqrt((x * x) + (y * y));
        // Math#atan returns radians
        double theta = Math.toDegrees(Math.atan2(x, y));
        return new double[] {r, theta};
    }
}
