package PointPackage;

/**
 * Created by i-ru on 23.01.2017.
 */
public class MainPoint {
    public static void main(String[] args) {
        Point p1 = new Point();
        Point p2 = new Point();

        {
            p1.setCoordinates(1.1, 1.1);
            p2.setCoordinates(1.2, 1.2);

            System.out.println("x1 = " + p1.getX() + "; " + "y1 = " + p1.getY());
            System.out.println("x1 = " + p2.getX() + "; " + "y1 = " + p2.getY());
            System.out.println("Расстояние между двумя точками равно: " + Point.distance(p1, p2));
            System.out.println();
        }

        {
            p1.setCoordinates(0.1, 0.1);
            p2.setCoordinates(0.2, 0.2);

            System.out.println("x1 = " + p1.getX() + "; " + "y1 = " + p1.getY());
            System.out.println("x1 = " + p2.getX() + "; " + "y1 = " + p2.getY());
            System.out.println("Расстояние между двумя точками равно: " + Point.distance(p1, p2));
            System.out.println();
        }

        {
            p1.setCoordinates(2, 2);
            p2.setCoordinates(3, 3);


            System.out.println("x1 = " + p1.getX() + "; " + "y1 = " + p1.getY());
            System.out.println("x1 = " + p2.getX() + "; " + "y1 = " + p2.getY());
            System.out.println("Расстояние между двумя точками равно: " + Point.distance(p1, p2));
            System.out.println();
        }

    }
}
