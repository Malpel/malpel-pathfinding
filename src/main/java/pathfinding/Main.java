package pathfinding;

import pathfinding.ui.ConsoleUi;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // the format is (y, x), NOT (x, y)
        // 78, 59         243, 242      33, 6
        // 84, 90         18, 6         992, 994
        ConsoleUi consoleUi = new ConsoleUi(new Scanner(System.in));
        consoleUi.runUi();
    }
}