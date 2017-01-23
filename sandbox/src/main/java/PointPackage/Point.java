package PointPackage; /**
 * Created by i-ru on 23.01.2017.
 */

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Point {
    double x;
    double y;

    void setCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    public static double distance(Point p1, Point p2) {
        return sqrt(pow(p2.x - p1.x, 2) + pow(p2.y - p1.y, 2));
    }
}
