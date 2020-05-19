package me.j0eppp.astarvisualization;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Main {

    static Frame frame;

    static boolean mousePressed = false;
    static int buttonPressed = 0;

    static boolean keyPressed = false;
    static Character pressedKey = ' ';
    static CLICK_ACTION clickAction = CLICK_ACTION.NORMAL;

    static int[] startCellLoc;
    static int[] endCellLoc;

    public static int h(Cell cell) {
        int returnValue = 0;

        return returnValue;
    }

    public static int g(Cell cell) {
        int returnValue = 0;

        return returnValue;
    }

    public static int f(Cell cell) {
        return g(cell) + h(cell);
    }

    static int scale;
    static int width;
    static int height;

    public static void main(String[] args) {
        scale = 40;
        width = 1200;
        height = 700;

        startCellLoc = new int[]{-1, -1};
        endCellLoc = new int[]{-1, -1};

        frame = new Frame("A* pathfinding");
    }
}
