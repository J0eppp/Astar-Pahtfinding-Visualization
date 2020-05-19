package me.j0eppp.astarvisualization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class Cell extends JPanel implements MouseListener {

	int x;
	int y;
	int position;

	CELL_TYPE cellType;

	Color defaultColor;

	Cell(int x, int y, int position) {

		this.x = x;
		this.y = y;
		this.position = position;

		cellType = CELL_TYPE.AIR;

		defaultColor = Color.WHITE;

		setBackground(defaultColor);

		addMouseListener(this);

	}

	private void leftClick() {
			switch (Main.pressedKey) {
				case 's':
					// Make the cell a "start" cell
					// Remove the last start cell if it exists
					if (Main.startCellLoc[0] != -1 && Main.startCellLoc[1] != -1) {
						System.out.println(Main.frame.getGrid().getCells().get(Main.startCellLoc[0] + ", " + Main.startCellLoc[1]));
						Main.frame.getGrid().getCells().get(Main.startCellLoc[0] + ", " + Main.startCellLoc[1]).defaultColor = Color.WHITE;
						Main.frame.getGrid().getCells().get(Main.startCellLoc[0] + ", " + Main.startCellLoc[1]).cellType = CELL_TYPE.AIR;
						Main.frame.getGrid().getCells().get(Main.startCellLoc[0] + ", " + Main.startCellLoc[1]).setBackground(Color.WHITE);
					}


					Main.startCellLoc[0] = x;
					Main.startCellLoc[1] = y;

					defaultColor = Color.BLUE;
					cellType = CELL_TYPE.START;
					break;
				case 'e':
					// Make the cell a "end" cell
					// Remove the last end cell if it exists
					if (Main.endCellLoc[0] != -1 && Main.endCellLoc[1] != -1) {
						Main.frame.getGrid().getCells().get(Main.endCellLoc[0] + ", " + Main.endCellLoc[1]).defaultColor = Color.WHITE;
						Main.frame.getGrid().getCells().get(Main.endCellLoc[0] + ", " + Main.endCellLoc[1]).cellType = CELL_TYPE.AIR;
						Main.frame.getGrid().getCells().get(Main.endCellLoc[0] + ", " + Main.endCellLoc[1]).setBackground(Color.WHITE);
					}

					defaultColor = Color.RED;
					cellType = CELL_TYPE.END;

					Main.endCellLoc[0] = x;
					Main.endCellLoc[1] = y;
					break;
				default:
					// Make the cell a wall
					if (cellType == CELL_TYPE.AIR) {
						defaultColor = Color.BLACK;
						cellType = CELL_TYPE.WALL;
					}
					break;
			}
		}

	private void rightClick() {
		if (!Main.keyPressed) {
			// Make the wall an "air" cell
			if (cellType == CELL_TYPE.WALL) {
				defaultColor = Color.WHITE;
				cellType = CELL_TYPE.AIR;
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(Main.scale, Main.scale);
	}

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		System.out.println(x + ", " + y);
	}

	@Override
	public void mousePressed(MouseEvent mouseEvent) {
		Main.mousePressed = true;
		Main.buttonPressed = mouseEvent.getButton();

		if (Main.buttonPressed == 1) {
			// Left
			leftClick();
		} else if (Main.buttonPressed == 3) {
			// Right
			rightClick();
		}
		setBackground(defaultColor);
	}

	@Override
	public void mouseReleased(MouseEvent mouseEvent) {
		Main.mousePressed = false;
		Main.buttonPressed = 0;
	}

	@Override
	public void mouseEntered(MouseEvent mouseEvent) {
		if (!Main.mousePressed) {
			setBackground(new Color(150, 0, 140));
		} else {
			if (Main.buttonPressed == 1) {
				// Left
				leftClick();
			} else if (Main.buttonPressed == 3) {
				// Right
				rightClick();
			}
			setBackground(defaultColor);
		}
	}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {
		setBackground(defaultColor);
	}

}
