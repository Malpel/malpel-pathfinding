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

        return (distanceFromY + distanceFromX) + (1.41421356237 - 2) * minVal(distanceFromY, distanceFromX);
    }

    public static double absVal(double val) {
        return val < 0 ? -val : val;
    }

    public static double minVal(double val1, double val2) {
        return val1 < val2 ? val1 : val2;
    }

}
