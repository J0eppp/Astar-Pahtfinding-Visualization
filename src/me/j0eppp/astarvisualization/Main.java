package me.j0eppp.astarvisualization;

public class Main {

    static Frame frame;

    static boolean mousePressed = false;
    static int buttonPressed = 0;

    public static void main(String[] args) throws InterruptedException {
        int frameWidth = 1200;
        int frameHeight = 700;
        int scale = 40;


        frame = new Frame("A* pathfinding", 1200, 700, 40);

        Thread.sleep(3000);
        System.out.println(frame.getCells().get(0));
    }
}
