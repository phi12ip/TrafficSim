package com.andisolsoftware.trafficsim;

import java.awt.*;

public class MapLocation {

    private double x;
    private double y;


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point getPoint() {
        return new Point((int) x, (int) y);
    }

    public void setPoint(Point p) {
        x = p.getX();
        y = p.getY();
    }
}
