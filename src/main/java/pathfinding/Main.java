package pathfinding;

import pathfinding.io.MapReader;
import pathfinding.ui.ConsoleUi;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ConsoleUi consoleUi = new ConsoleUi(new Scanner(System.in), new MapReader());
        consoleUi.runUi();
    }
}