package org.itmo;

public class Validate {
    public static boolean check(double x, double y, double r) {
        return square(x, y, r) || circle(x, y, r) || triangle(x, y, r);
    }

    private static boolean circle(double x, double y, double r) {
        // Проверка для четверти окружности во второй четверти
        return (x <= 0 && y >= 0 && Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r / 2, 2));
    }

    private static boolean square(double x, double y, double r) {
        // Проверка для прямоугольника в третьей четверти
        return (x <= 0 && y <= 0 && x >= -r && y >= -r / 2);
    }

    private static boolean triangle(double x, double y, double r) {
        // Проверка для треугольника в четвертой четверти
        return (x >= 0 && y <= 0 && y >= (-0.5 * x - r / 2) && x <= r);
    }

    // Validation methods
    public static boolean validateX(double x) {
        return x >= -3 && x <= 5;
    }

    public static boolean validateY(double y) {
        return y >= -3 && y <= 5;
    }

    public static boolean validateR(double r) {
        return r >= 1 && r <= 4;
    }
}
