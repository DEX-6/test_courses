package ru.stqa.test_courses.sandbox;

/**
 * Created by i-ru on 29.01.2017.
 */
public class Rectangle {
    public double a;
    public double b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double area() {
        return this.a * this.b;
    }
}
