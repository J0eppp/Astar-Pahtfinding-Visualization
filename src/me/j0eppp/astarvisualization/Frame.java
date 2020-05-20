package me.j0eppp.astarvisualization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Frame extends JFrame implements Runnable, KeyListener {

	public Grid getGrid() {
		return grid;
	}

	private Grid grid;

	Frame(String name) {

		super(name);

		addKeyListener(this);

		run();

	}

	@Override
	public void run() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setMinimumSize(new Dimension(Main.width, Main.height));
		setMaximumSize(new Dimension(Main.width, Main.height));

		setLayout(new BorderLayout());

		grid = new Grid(Main.width, Main.height, Main.scale);

		add(grid);

		pack();
		setLocationRelativeTo(null);


		setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent keyEvent) {}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		if (!Main.calculating) {
			Main.keyPressed = true;
			Main.pressedKey = keyEvent.getKeyChar();
			if (Main.pressedKey.equals('r')) {
				try {
					Main.startAStar();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent keyEvent) {
		if (!Main.calculating) {
			Main.keyPressed = false;
			Main.pressedKey = ' ';
		}
	}
}
