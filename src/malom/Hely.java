package malom;

import java.awt.Color;
import java.io.Serializable;


public class Hely implements Serializable{
	private Color szin;
	private Vonal v1, v2;
	public boolean malomban;
	public boolean elozo_malomban;
	
	public Hely(int i1, int i2) {
		v1 = new Vonal(i1);
		v2 = new Vonal(i2);
		szin = null;
		malomban = false;
		elozo_malomban = false;
	}
	
	public boolean equals(Hely h) {
		return (v1.getSzam() == h.v1.getSzam() && v2.getSzam() == h.v2.getSzam());
	}
	
	public Hely(int i1, int i2, Color c) {
		v1 = new Vonal(i1);
		v2 = new Vonal(i2);
		szin = c;
		malomban = false;
		elozo_malomban = false;
	}
	
	public Vonal getVonal1() {
		return v1;
	}
	public Vonal getVonal2() {
		return v2;
	}
	public Color getSzin() {
		return szin;
	}
	public void setSzin(Color c) {
		szin = c;
	}
}
