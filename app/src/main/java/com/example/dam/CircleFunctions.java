package com.example.dam;

import android.graphics.Point;

public class CircleFunctions {
    public static Point getPointOnCircle(float xCenter, float yCenter, double angle, float radius)
    {
        int xPos = Math.round((float) (xCenter + Math.cos(angle) * radius));
        int yPos = Math.round((float) (yCenter + Math.sin(angle) * radius));

        return new Point(xPos, yPos);
    }

    public static double pointsToAngle(float xCenter, float yCenter, float xTouch, float yTouch)
    {
        double angle = Math.atan2(
                ((yTouch - yCenter) / Math.sqrt(Math.pow(xTouch - xCenter, 2) + (Math.pow(yTouch - yCenter, 2)))),
                ((xTouch - xCenter) / Math.sqrt(Math.pow(xTouch - xCenter, 2) + (Math.pow(yTouch - yCenter, 2)))));

        return angle;
    }
}