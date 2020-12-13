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
        double distanceFromY = Math.abs(first.getY() - second.getY());
        double distanceFromX = Math.abs(first.getX() - second.getX());

        return Math.sqrt((distanceFromY * distanceFromY) + (distanceFromX * distanceFromX));
        //return (distanceFromY + distanceFromX) + (1.41421356237 - 2) * Math.min(distanceFromY, distanceFromX);
    }

}
