package malom;

import java.io.Serializable;

public class Vonal implements Serializable{
	private int hely;
	
	public Vonal(int i) {
		hely = i;
	}
	
	public int getSzam() {
		return hely;
	}
}
