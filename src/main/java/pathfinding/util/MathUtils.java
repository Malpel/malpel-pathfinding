package pathfinding.util;

import pathfinding.domain.Node;

public class MathUtils {

    public static int clamp(int a, int min, int max) {
        if (a < min) {
            return min;
        } else if (a > max) {
            return max;
        }

        return a;
    }

    public static double shortestDistance(Node first, Node second) {
        double distanceFromY = absVal(first.getY() - second.getY());
        double distanceFromX = absVal(first.getX() - second.getX());

        return Math.sqrt((distanceFromY * distanceFromY) + (distanceFromX * distanceFromX));
    }

    public static double absVal(double val) {
        return val < 0 ? -val : val;
    }

}
