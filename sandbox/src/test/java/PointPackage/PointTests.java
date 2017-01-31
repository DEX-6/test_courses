package PointPackage;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by i-ru on 31.01.2017.
 */
public class PointTests {

    @Test
    public void testDistanceP1P2() {
        Point p1 = new Point(1.1, 1.1);
        Point p2 = new Point(1.2, 1.2);
        Assert.assertEquals(Point.distance(p1, p2), 0.1414213562373093);
    }

    @Test
    public void testDistanceP3P4() {
        Point p3 = new Point(-1.1, -1.1);
        Point p4 = new Point(-1.2, -1.2);
        Assert.assertEquals(Point.distance(p3, p4), 0.1414213562373093);
    }

    @Test
    public void testDistanceP5P6() {
        Point p5 = new Point(2, 2);
        Point p6 = new Point(3, 3);
        Assert.assertEquals(Point.distance(p5, p6), 1.4142135623730951);
    }
}
