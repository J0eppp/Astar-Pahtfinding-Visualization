package me.j0eppp.astarvisualization;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Grid extends JPanel {

	private Map<String, Cell> cells;

	public Map<String, Cell> getCells() {
		return cells;
	}

	Grid(int width, int height, int scale) {
		cells = new HashMap<>();

		int rows = height / scale;
		int cols = width / scale;

		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		for (int row = 0; row < width / scale; row++) {
			for (int col = 0; col < height / scale; col++) {
				gbc.gridx = row;
				gbc.gridy = col;

				Cell cell = new Cell(row, col, cells.size());
				cells.put(col + ", " + row, cell);
				Border border = null;
				if (row < height / scale - 1) {
					if (col < width / scale - 1) {
						border = new MatteBorder(1, 1, 0, 0, Color.DARK_GRAY);
					} else {
						border = new MatteBorder(1, 1, 0, 1, Color.DARK_GRAY);
					}
				} else {
					if (col < width / scale - 1) {
						border = new MatteBorder(1, 1, 1, 0, Color.DARK_GRAY);
					} else {
						border = new MatteBorder(1, 1, 1, 1, Color.DARK_GRAY);
					}
				}
				cell.setBorder(border);
				add(cell, gbc);
			}
		}
	}

}
