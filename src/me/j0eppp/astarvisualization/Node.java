package me.j0eppp.astarvisualization;

import java.awt.*;

public class Node {

	private int x, y;
	private NODE_TYPE type;
	private Color color;

	public Node(int x, int y, NODE_TYPE type) {
		this.x = x;
		this.y = y;
		this.type = type;
		switch (getType()) {
			case AIR:
				setBackground(Color.GRAY);
				break;
			case WALL:
				setBackground(Color.BLACK);
				break;
			case END:
				setBackground(Color.RED);
				break;
			case START:
				setBackground(Color.CYAN);
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + getType());
		}
	}

	public void setBackground(Color color) {
		this.color = color;
	}

	public Color getBackground() {
		return color;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public NODE_TYPE getType() {
		return type;
	}

	public void setType(NODE_TYPE type) {
		this.type = type;
	}

}
