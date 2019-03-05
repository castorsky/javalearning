package lesson06;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class prog06 {

	public static void main(String[] args) {
		// TODO Автоматически созданная заглушка метода
		MainFrame window = new MainFrame();
	}

}
class MainFrame extends JFrame {
	public MainFrame() {
		MainPanel panel = new MainPanel();
		Container container = getContentPane();
		container.add(panel);
		this.setBounds(200,200,800,600);
		setVisible(true);
	}
	public MainFrame(int x, int y) {
		MainPanel panel = new MainPanel();
		Container container = getContentPane();
		container.add(panel);
		this.setBounds(x, y, 800, 600);
		setVisible(true);
	}
}

class MainPanel extends JPanel {
	private int x,y;
	private int direction = 2;
	private Image image;
	
	private class MainKey implements KeyListener {

		@Override
		public void keyPressed(KeyEvent arg0) {
			int code = arg0.getKeyCode();
			if (code == 37) { direction = 0; } // LEFT
			if (code == 38) { direction = 1; } // UP
			if (code == 39) { direction = 2; } // RIGHT
			if (code == 40) { direction = 3; } // DOWN
		}
		@Override
		public void keyReleased(KeyEvent e) {}
		@Override
		public void keyTyped(KeyEvent e) {}
		
	}
	
	public MainPanel() {
		x = 0;
		y = 0;
		addKeyListener(new MainKey());
		setFocusable(true);
		try {
			image = ImageIO.read(new File("src/lesson06/earth.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Timer timer = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (direction == 0) x--;
				if (direction == 1) y--;
				if (direction == 2) x++;
				if (direction == 3) y++;
				repaint();
			}
		});
		timer.start();
	}
	public void paintComponent(Graphics graphics) {
		graphics.clearRect(x, y, 300, 300);
		graphics.drawImage(image, x, y, 300, 300, null);
	}
}