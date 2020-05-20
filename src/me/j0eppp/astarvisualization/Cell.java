package me.j0eppp.astarvisualization;

import javax.swing.*;
import java.awt.*;
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
		if (!Main.calculating) {
			switch (Main.pressedKey) {
				case 's':
					// Make the cell a "start" cell
					// Remove the last start cell if it exists
					if (Main.startCell != null) {
						Main.startCell.defaultColor = Color.WHITE;
						Main.startCell.cellType = CELL_TYPE.AIR;
						Main.startCell.setBackground(Main.startCell.defaultColor);
					}

					Main.startCell = this;

					defaultColor = Color.BLUE;
					cellType = CELL_TYPE.START;
					break;
				case 'e':
					// Make the cell a "end" cell
					// Remove the last end cell if it exists
					if (Main.endCell != null) {
						Main.endCell.defaultColor = Color.WHITE;
						Main.endCell.cellType = CELL_TYPE.AIR;
						Main.endCell.setBackground(Main.endCell.defaultColor);
					}

					Main.endCell = this;

					defaultColor = Color.RED;
					cellType = CELL_TYPE.END;
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
	}

	private void rightClick() {
		if (!Main.keyPressed && !Main.calculating) {
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
	public void mouseClicked(MouseEvent mouseEvent) {}

	@Override
	public void mousePressed(MouseEvent mouseEvent) {
		if (!Main.calculating) {
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
	}

	@Override
	public void mouseReleased(MouseEvent mouseEvent) {
		Main.mousePressed = false;
		Main.buttonPressed = 0;
	}

	@Override
	public void mouseEntered(MouseEvent mouseEvent) {
		if (!Main.calculating) {
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
	}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {
		if (!Main.calculating) {
			setBackground(defaultColor);
		}
	}

}
