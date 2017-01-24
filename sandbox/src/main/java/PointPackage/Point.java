package PointPackage; /**
 * Created by i-ru on 23.01.2017.
 */

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Point {
    public double x;
    public double y;

    public Point (double x, double y){
        this.x = x;
        this.y = y;
    }

    public static double distance(Point p1, Point p2) {
        return sqrt(pow(p2.x - p1.x, 2) + pow(p2.y - p1.y, 2));
    }
}
