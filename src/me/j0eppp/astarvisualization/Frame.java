package me.j0eppp.astarvisualization;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

class Frame extends JFrame implements MouseWheelListener {

	private String name;
	private int width;
	private int height;
	private int scale;

	private List<CellPane> cells;

	Frame(String name, int width, int height, int scale) {

		super(name);

		this.name = name;
		this.width = width;
		this.height = height;
		this.scale = scale;

		cells = new ArrayList<>();

		addMouseWheelListener(this);

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
				}


				setDefaultCloseOperation(EXIT_ON_CLOSE);
				setMinimumSize(new Dimension(width, height));
				setMaximumSize(new Dimension(width, height));
				setLayout(new BorderLayout());
				add(new TestPane(width, height, scale, cells));
				pack();
				setLocationRelativeTo(null);
				setVisible(true);
			}
		});
	}

	public List<CellPane> getCells() {
		return cells;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Add zoom functionality
	}
}

class TestPane extends JPanel {

	TestPane(int width, int height, int scale, List<CellPane> cells) {
		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		for (int row = 0; row < height / scale; row++) {
			for (int col = 0; col < width / scale; col++) {
				gbc.gridx = col;
				gbc.gridy = row;

				CellPane cellPane = new CellPane(scale, row, col);
				cells.add(cellPane);
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
				cellPane.setBorder(border);
				add(cellPane, gbc);
			}
		}
	}
}

class CellPane extends JPanel {

	private Color defaultBackground;
	private int row, col;
	private int scale;
	private CELL_TYPE type;

	private void makeWall() {
		defaultBackground = Color.GRAY;
		setBackground(defaultBackground);
		type = CELL_TYPE.WALL;
	}

	private void makeAir() {
		defaultBackground = Color.WHITE;
		setBackground(defaultBackground);
		type = CELL_TYPE.AIR;
	}

	CellPane(int scale, int row, int col) {

		this.scale = scale;
		this.row = row;
		this.col = col;
		this.type = CELL_TYPE.AIR;

		defaultBackground = Color.WHITE;
		setBackground(defaultBackground);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (Main.mousePressed) {
					if (Main.buttonPressed == 1) {
						makeWall();
					} else if (Main.buttonPressed == 3) {
						makeAir();
					}
				}
				defaultBackground = getBackground();
//				setBackground(new Color(14, 121, 251));
				setBackground(Color.LIGHT_GRAY);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Main.mousePressed = true;
				Main.buttonPressed = e.getButton();
				if (e.getButton() == 1) {
					makeWall();
				} else if (e.getButton() == 3) {
					makeAir();
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				Main.mousePressed = false;
				Main.buttonPressed = 0;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(defaultBackground);
			}
		});
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(scale, scale);
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	public CELL_TYPE getType() {
		return type;
	}

}