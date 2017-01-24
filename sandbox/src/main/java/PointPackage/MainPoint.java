package PointPackage;

/**
 * Created by i-ru on 23.01.2017.
 */
public class MainPoint {
    public static void main(String[] args) {

        {
            Point p1 = new Point(1.1, 1.1);
            Point p2 = new Point(1.2, 1.2);

            System.out.println("x1 = " + p1.x + "; " + "y1 = " + p1.y);
            System.out.println("x2 = " + p2.x + "; " + "y2 = " + p2.y);
            System.out.println("Расстояние между двумя точками равно: " + Point.distance(p1, p2));
            System.out.println();
        }

        {
            Point p3 = new Point(-1.1, -1.1);
            Point p4 = new Point(-1.2, -1.2);

            System.out.println("x3 = " + p3.x + "; " + "y3 = " + p3.y);
            System.out.println("x4 = " + p4.x + "; " + "y4 = " + p4.y);
            System.out.println("Расстояние между двумя точками равно: " + Point.distance(p3, p4));
            System.out.println();
        }

        {
            Point p5 = new Point(2, 2);
            Point p6 = new Point(3, 3);

            System.out.println("x5 = " + p5.x + "; " + "y5 = " + p5.y);
            System.out.println("x6 = " + p6.x + "; " + "y6 = " + p6.y);
            System.out.println("Расстояние между двумя точками равно: " + Point.distance(p5, p6));
            System.out.println();
        }
    }
}
