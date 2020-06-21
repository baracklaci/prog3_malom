package malom;

import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;


public class Movement implements MouseListener, MouseMotionListener{

	private circle c;
	static private Point piros_helye, fekete_helye;
	private int x, y, prevx, prevy;
	static private ArrayList<ponthely> pontok;
	static private Point kuka;
	private static Tabla t;
	static public Color elozo_szin;
	static public Color levehet;
	static private malomframe frame;
	static private boolean vege = false;
	
	static {
		elozo_szin = Color.red;
		piros_helye = new Point(50, 200);
		fekete_helye = new Point(50, 100);
		pontok = new ArrayList<ponthely>();
		pontok.add(new ponthely(210, 38, new Hely(1,9)));
		pontok.add(new ponthely(381, 37, new Hely(4,9)));
		pontok.add(new ponthely(557, 38, new Hely(8,9)));
		pontok.add(new ponthely(267, 96, new Hely(2,10)));
		pontok.add(new ponthely(381, 96, new Hely(4,10)));
		pontok.add(new ponthely(498, 96, new Hely(7,10)));
		pontok.add(new ponthely(325, 155, new Hely(3,11)));
		pontok.add(new ponthely(381, 155, new Hely(4,11)));
		pontok.add(new ponthely(440, 155, new Hely(6,11)));
		pontok.add(new ponthely(209, 214, new Hely(1,12)));
		pontok.add(new ponthely(266, 214, new Hely(2,12)));
		pontok.add(new ponthely(324, 214, new Hely(3,12)));
		pontok.add(new ponthely(439, 213, new Hely(6,13)));
		pontok.add(new ponthely(498, 213, new Hely(7,13)));
		pontok.add(new ponthely(555, 212, new Hely(8,13)));
		pontok.add(new ponthely(325, 272, new Hely(3,14)));
		pontok.add(new ponthely(380, 272, new Hely(5,14)));
		pontok.add(new ponthely(439, 271, new Hely(6,14)));
		pontok.add(new ponthely(265, 331, new Hely(2,15)));
		pontok.add(new ponthely(381, 331, new Hely(5,15)));
		pontok.add(new ponthely(497, 330, new Hely(7,15)));
		pontok.add(new ponthely(209, 391, new Hely(1,16)));
		pontok.add(new ponthely(382, 390, new Hely(5,16)));
		pontok.add(new ponthely(555, 389, new Hely(8,16)));
		kuka = new Point(50, 300);
	}
	
	public static void setTabla(Tabla T) {
		t = T;
	}
	
	public static void reset() {
		elozo_szin = Color.red;
		levehet = null;
		vege = false;
	}
	
	public Movement(Tabla T, circle C, malomframe Frame) {
		t = T;
		c = C;
		C.addMouseListener(this);
		C.addMouseMotionListener(this);
		frame = Frame;
		frame.setText("A fekete léphet!");
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub
		
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent event) {
		x = event.getX();
		y = event.getY();
		prevx = event.getComponent().getX();
		prevy = event.getComponent().getY();
		
	}

	private void kiir() {
		if (levehet == Color.black)
			frame.setText("A fekete levehet egy bábut!");
		else if (levehet == Color.red)
			frame.setText("A piros levehet egy bábut!");
		else if (elozo_szin == Color.black)
			frame.setText("A piros léphet!");
		else
			frame.setText("A fekete léphet!");
	}
	
	@Override
	public void mouseReleased(MouseEvent event) {
		boolean nem_pontra_lepett = true;
		for (ponthely i : pontok) {
			if (event.getComponent().getX() >= i.getX()-20 && event.getComponent().getX() <= i.getX()+20 
					&& event.getComponent().getY() >= i.getY()-20 && event.getComponent().getY() <= i.getY()+20 && !vege) {
				nem_pontra_lepett = false;
				if (levehet == null && elozo_szin != c.getColor() && (fekete_helye.equals(new Point(prevx, prevy)) || piros_helye.equals(new Point(prevx, prevy)))) {
					if(t.Felrak(i.getHely(), c.getColor())) {
						event.getComponent().setLocation((int)i.getX(), (int)i.getY());
						elozo_szin = c.getColor();
						levehet = t.volt_malom();
						kiir();
					}
					else {
						event.getComponent().setLocation(prevx, prevy);
					}
					
				}
				else {
					event.getComponent().setLocation(prevx, prevy);
				}
				
				for (ponthely j : pontok) {
					if (levehet == null && j.getX() == prevx && j.getY() == prevy && t.getBabuk() == 18 && elozo_szin != c.getColor()) {
						if (t.Lep(j.getHely(), i.getHely(), c.getColor())) {
							event.getComponent().setLocation((int)i.getX(), (int)i.getY());
							elozo_szin = c.getColor();
							levehet = t.volt_malom();
							kiir();
						}
						else {
							event.getComponent().setLocation(prevx, prevy);
						}
					}
				}
				
			}	
		}
		
		if (event.getComponent().getX() >= kuka.getX()-30 && event.getComponent().getX() <= kuka.getX()+30 
					&& event.getComponent().getY() >= kuka.getY()-30 && event.getComponent().getY() <= kuka.getY()+30) {
			if (levehet != c.getColor() && levehet != null)
				for (ponthely i: pontok) {
					if (i.getX() == prevx && i.getY() == prevy && t.levesz(i.getHely(), c.getColor())) {
						levehet = null;
						if (elozo_szin == Color.black)
							frame.setText("A piros léphet!");
						else
							frame.setText("A fekete léphet!");
						if (t.getFbabuszam() < 3 && t.getBabuk() == 18) {
							frame.setText("A piros nyert!");
							vege = true;
						}
						else if (t.getPbabuszam() < 3 && t.getBabuk() == 18) {
							vege = true;
							frame.setText("A fekete nyert!");							
						}
						event.getComponent().setVisible(false);
					}	
				}
			else {
				event.getComponent().setLocation(prevx, prevy);
			}
		}
			
		if (nem_pontra_lepett) {
			event.getComponent().setLocation(prevx, prevy);
		}
			
		
		
		
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		event.getComponent().setLocation((event.getX()+event.getComponent().getX())-x, (event.getY()+event.getComponent().getY())-y);
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}
	
}
