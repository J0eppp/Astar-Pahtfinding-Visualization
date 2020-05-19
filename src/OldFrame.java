//package me.j0eppp.astarvisualization;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.EventQueue;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.event.*;
//import java.util.ArrayList;
//import java.util.List;
//import javax.swing.*;
//import javax.swing.border.Border;
//import javax.swing.border.MatteBorder;
//
//class FrameOld extends JFrame implements MouseWheelListener, KeyListener {
//
//	private String name;
//	private int width;
//	private int height;
//	public int scale;
//
//	private List<CellPane> cells;
//
//	FrameOld(String name, int width, int height, int scale) {
//
//		super(name);
//
//		this.name = name;
//		this.width = width;
//		this.height = height;
//		this.scale = scale;
//
//		cells = new ArrayList<>();
//
//		addMouseWheelListener(this);
//		addKeyListener(this);
//
//		EventQueue.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//				}
//
//
//				setDefaultCloseOperation(EXIT_ON_CLOSE);
//				setMinimumSize(new Dimension(width, height));
//				setMaximumSize(new Dimension(width, height));
//				setLayout(new BorderLayout());
//				add(new TestPane(width, height, scale, cells));
//				pack();
//				setLocationRelativeTo(null);
//				setVisible(true);
//			}
//		});
//	}
//
//	public List<CellPane> getCells() {
//		return cells;
//	}
//
//	@Override
//	public void mouseWheelMoved(MouseWheelEvent e) {
//		// TODO Add zoom functionality
//	}
//
//	@Override
//	public void keyTyped(KeyEvent keyEvent) {}
//
//	@Override
//	public void keyPressed(KeyEvent keyEvent) {
//		Main.keyPressed = true;
//		Main.pressedKey = keyEvent.getKeyChar();
//		if (Main.pressedKey.equals('s')) {
//			System.out.println("START");
//			Main.clickAction = CLICK_ACTION.START;
//		} else if (Main.pressedKey.equals('e')) {
//			Main.clickAction = CLICK_ACTION.END;
//		} else if (Main.pressedKey.equals('r')) {
//			// Start the A* algorithm here
//		} else {
//			Main.clickAction = CLICK_ACTION.NORMAL;
//		}
//	}
//
//	@Override
//	public void keyReleased(KeyEvent keyEvent) {
//		Main.keyPressed = false;
//		Main.pressedKey = ' ';
//		Main.clickAction = CLICK_ACTION.NORMAL;
//	}
//}
//
//class TestPane extends JPanel {
//
//	TestPane(int width, int height, int scale, List<CellPane> cells) {
//		setLayout(new GridBagLayout());
//
//		GridBagConstraints gbc = new GridBagConstraints();
//		for (int row = 0; row < height / scale; row++) {
//			for (int col = 0; col < width / scale; col++) {
//				gbc.gridx = col;
//				gbc.gridy = row;
//
//				CellPane cellPane = new CellPane(scale, row, col, cells.size());
//				cells.add(cellPane);
//				Border border = null;
//				if (row < height / scale - 1) {
//					if (col < width / scale - 1) {
//						border = new MatteBorder(1, 1, 0, 0, Color.DARK_GRAY);
//					} else {
//						border = new MatteBorder(1, 1, 0, 1, Color.DARK_GRAY);
//					}
//				} else {
//					if (col < width / scale - 1) {
//						border = new MatteBorder(1, 1, 1, 0, Color.DARK_GRAY);
//					} else {
//						border = new MatteBorder(1, 1, 1, 1, Color.DARK_GRAY);
//					}
//				}
//				cellPane.setBorder(border);
//				add(cellPane, gbc);
//			}
//		}
//	}
//}
//
//class CellPane extends JPanel {
//
//	private Color defaultBackground;
//	private int row, col;
//	private int scale;
//	private CELL_TYPE type;
//	private int location;
//
//	private void makeWall() {
//		if (Main.clickAction == CLICK_ACTION.START) {
//			if (Main.startCellLoc == -1) {
//				// First time a start cell is created
//				Main.startCellLoc = location;
//			} else {
//				Main.frame.getCells().get(Main.startCellLoc).defaultBackground = Color.WHITE;
//				Main.frame.getCells().get(Main.startCellLoc).setBackground(Main.frame.getCells().get(Main.startCellLoc).defaultBackground);
//				Main.frame.getCells().get(Main.startCellLoc).type = CELL_TYPE.AIR;
//				Main.startCellLoc = location;
//			}
//
//			defaultBackground = Color.BLUE;
//			setBackground(defaultBackground);
//			type = CELL_TYPE.START;
//		} else if (Main.clickAction == CLICK_ACTION.END) {
//			if (Main.endCellLoc == -1) {
//				// First time a start cell is created
//				Main.endCellLoc = location;
//			} else {
//				Main.frame.getCells().get(Main.endCellLoc).defaultBackground = Color.WHITE;
//				Main.frame.getCells().get(Main.endCellLoc).setBackground(Main.frame.getCells().get(Main.endCellLoc).defaultBackground);
//				Main.frame.getCells().get(Main.endCellLoc).type = CELL_TYPE.AIR;
//				Main.endCellLoc = location;
//			}
//
//			defaultBackground = Color.RED;
//			setBackground(defaultBackground);
//			type = CELL_TYPE.END;
//		} else {
//			defaultBackground = Color.BLACK;
//			setBackground(defaultBackground);
//			type = CELL_TYPE.WALL;
//		}
//	}
//
//	private void makeAir() {
//		if (type == CELL_TYPE.WALL) {
//			defaultBackground = Color.WHITE;
//			setBackground(defaultBackground);
//			type = CELL_TYPE.AIR;
//		}
//	}
//
//	CellPane(int scale, int row, int col, int location) {
//
//		this.scale = scale;
//		this.row = row;
//		this.col = col;
//		this.type = CELL_TYPE.AIR;
//		this.location = location;
//
//		defaultBackground = Color.WHITE;
//		setBackground(defaultBackground);
//
//		addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				if (Main.mousePressed) {
//					if (Main.buttonPressed == 1) {
//						makeWall();
//					} else if (Main.buttonPressed == 3) {
//						makeAir();
//					}
//				}
//				defaultBackground = getBackground();
////				setBackground(new Color(14, 121, 251));
//				setBackground(Color.LIGHT_GRAY);
//			}
//
//			@Override
//			public void mousePressed(MouseEvent e) {
//				Main.mousePressed = true;
//				Main.buttonPressed = e.getButton();
//				if (e.getButton() == 1) {
//					makeWall();
//				} else if (e.getButton() == 3) {
//					makeAir();
//				}
//			}
//
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				Main.mousePressed = false;
//				Main.buttonPressed = 0;
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//				setBackground(defaultBackground);
//			}
//		});
//	}
//
//	@Override
//	public Dimension getPreferredSize() {
//		return new Dimension(Main.scale, Main.scale);
//	}
//
//	public int getCol() {
//		return col;
//	}
//
//	public int getRow() {
//		return row;
//	}
//
//	public CELL_TYPE getType() {
//		return type;
//	}
//
//}