package me.j0eppp.astarvisualization;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Canvas extends JComponent {

	private List<Node> nodes;

	public Canvas(List<Node> nodes) {
		this.nodes = nodes;
	}

	public void paint(Graphics g) {
		for (Node node : nodes) {
			int x = node.getX() * 10;
			int y = node.getY() * 10;
			Color c;
			switch (node.getType()) {
				case AIR:
					c = Color.GRAY;
					break;
				case WALL:
					c = Color.BLACK;
					break;
				case START:
					c = Color.CYAN;
					break;
				case END:
					c = Color.RED;
					break;
				default:
					throw new IllegalStateException("Unexpected value: " + node.getType());
			}
			g.setColor(c);
			g.fillRect(x, y, x + 10, y + 10);
//			g.drawRect(x, y, 10, 10);
		}
	}

}
