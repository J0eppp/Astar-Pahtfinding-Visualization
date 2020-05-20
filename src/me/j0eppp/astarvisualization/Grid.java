package me.j0eppp.astarvisualization;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

class Grid extends JPanel {

	public Map<String, Cell> getCells() {
		return Main.cells;
	}

	Grid(int width, int height, int scale) {

		int rows = width / scale;
		int cols = height / scale;

		System.out.println(rows + "x" + cols);

		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				gbc.gridx = row;
				gbc.gridy = col;

				Cell cell = new Cell(row, col, getCells().size());
				getCells().put(row + ", " + col, cell);
//				Border border = null;
//				if (row < rows - 1) {
//					if (col < cols - 1) {
//						border = new MatteBorder(1, 1, 0, 0, Color.DARK_GRAY);
//					} else {
//						border = new MatteBorder(1, 1, 0, 1, Color.DARK_GRAY);
//					}
//				} else {
//					if (col < cols - 1) {
//						border = new MatteBorder(1, 1, 0, 0, Color.DARK_GRAY);
//					} else {
//						border = new MatteBorder(1, 1, 1, 0, Color.DARK_GRAY);
//					}
//				}

				Border border = new MatteBorder(1, 1, (row == rows ? 1 : 0), (col == cols ? 1 : 0), Color.GRAY);

				cell.setBorder(border);
				add(cell, gbc);
			}
		}
	}

}
