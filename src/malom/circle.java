package malom;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.JPanel;

public class circle extends JPanel{
	
	private int r;
	private Color c;
	private int x, y;
	
	public circle(int rad, Color color, int X, int Y) {
		r = rad;
		c = color;
		x = X;
		y = Y;
		setLocation(x, y);
		setOpaque(false);
	}
	
	public Color getColor() {
		return c;
	}
	
	public void paint(Graphics g) {
		setSize(r+10, r+10);
		g.drawOval(x, y, r, r);
		g.setColor(c);
		g.fillOval(x, y, r, r);
	}
	
}
