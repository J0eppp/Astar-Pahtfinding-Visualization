package me.j0eppp.astarvisualization;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Main {

    static Frame frame;

    static boolean mousePressed = false;
    static int buttonPressed = 0;

    static boolean keyPressed = false;
    static Character pressedKey = ' ';
    static CLICK_ACTION clickAction = CLICK_ACTION.NORMAL;

    static Cell startCell;
    static Cell endCell;

    public static Map<String, Cell> cells;
    public static List<Cell> path;

    static boolean calculating = false;

    public static int minIndex (ArrayList<Double> list) {
        return list.indexOf (Collections.min(list));
    }

    public static void startAStar() throws InterruptedException {
        calculating = true;
        System.out.println(startCell.x + ", " + startCell.y);
        calcStuff(startCell);
    }

    public static Cell[] getNeighbours(Cell cell) {
        int x = cell.x;
        int y = cell.y;
        String[] neighbours = {(x + 1) + ", " + y, (x - 1) + ", " + y, x + ", " + (y - 1), x + ", " + (y + 1), (x + 1) + ", " + (y + 1), (x - 1) + ", " + (y - 1) , (x - 1) + ", " + (y + 1), (x + 1) + ", " + (y - 1)};
        Cell[] neighbourCells = new Cell[8];
        for (int i = 0; i < neighbours.length; i++) {
            String coord = neighbours[i];
            neighbourCells[i] = cells.get(coord);
        }

        return neighbourCells;
    }

    public static void calcStuff(Cell cell) throws InterruptedException {
        Cell[] neighbourCells = getNeighbours(cell);
        ArrayList<Double> results = new ArrayList<>();
        for (Cell c : neighbourCells) {
            c.setBackground(Color.DARK_GRAY);
            double f = f(c);
            results.add(f);
        }

        int best = minIndex(results);
        Cell bestCell = neighbourCells[best];
        bestCell.cellType = CELL_TYPE.PATH;
        bestCell.defaultColor = Color.GREEN;
        bestCell.setBackground(bestCell.defaultColor);

        if (results.get(best) == 0.0) {
            return;
        }

        Thread.sleep(0);

        calcStuff(bestCell);
    }

    public static double h(Cell cell) {
        int cX = cell.x;
        int cY = cell.y;

        int eX = endCell.x;
        int eY = endCell.y;

        return Math.sqrt(Math.pow(cX - eX, 2) + Math.pow(cY - eY, 2));
    }

    public static double g(Cell cell) {
        int cX = cell.x;
        int cY = cell.y;

        int sX = startCell.x;
        int sY = startCell.y;

        return Math.sqrt(Math.pow(cX - sX, 2) + Math.pow(cY - sY, 2));
    }

    public static double f(Cell cell) {
        return g(cell) + h(cell);
    }

    static int scale;
    static int width;
    static int height;

    public static void main(String[] args) {
        scale = 40;
        width = 1200;
        height = 700;

        cells = new HashMap<>();
        path = new ArrayList<>();

        startCell = null;
        endCell = null;

        int xA = 3;
        int yA = 1;
        int xB = 5;
        int yB = 1;

        System.out.println(Math.sqrt(Math.pow(xA - xB, 2) + Math.pow(yA - yB, 2)));

        frame = new Frame("A* pathfinding");
    }
}
