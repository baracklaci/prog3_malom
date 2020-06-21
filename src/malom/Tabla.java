package malom;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.lang.Math;

public class Tabla implements Serializable{
	private ArrayList<Hely> helyek;
	private int babu_lerakva;
	private int fbabuszam, pbabuszam;
	
	public Tabla() {
		helyek = new ArrayList<Hely>();
		helyek.add(new Hely(1, 9));
        helyek.add(new Hely(4, 9));
        helyek.add(new Hely(8, 9));
        helyek.add(new Hely(2, 10));
        helyek.add(new Hely(4, 10));
        helyek.add(new Hely(7, 10));
        helyek.add(new Hely(3, 11));
        helyek.add(new Hely(4, 11));
        helyek.add(new Hely(6, 11));
        helyek.add(new Hely(1, 12));
        helyek.add(new Hely(2, 12));
        helyek.add(new Hely(3, 12));
        helyek.add(new Hely(6, 13));
        helyek.add(new Hely(7, 13));
        helyek.add(new Hely(8, 13));
        helyek.add(new Hely(3, 14));
        helyek.add(new Hely(5, 14));
        helyek.add(new Hely(6, 14));
        helyek.add(new Hely(2, 15));
        helyek.add(new Hely(5, 15));
        helyek.add(new Hely(7, 15));
        helyek.add(new Hely(1, 16));
        helyek.add(new Hely(5, 16));
        helyek.add(new Hely(8, 16));
	}
	
	public void reset() {
		for (Hely hely: helyek) {
			hely.setSzin(null);
		}
		babu_lerakva = 0;
		fbabuszam = 0;
		pbabuszam = 0;
	}
	
	public int getFbabuszam() {
		return fbabuszam;
	}
	
	public int getPbabuszam() {
		return pbabuszam;
	}
	
	public int getBabuk() {
		return babu_lerakva;
	}
	
	public boolean Felrak(Hely h, Color c) {
		for (Hely hely: helyek) {
			if (hely.equals(h) && hely.getSzin() == null) {
				hely.setSzin(c);
				babu_lerakva++;
				if (c == Color.black)
					fbabuszam++;
				else
					pbabuszam++;
				malom_frissit();
				return true;
			}
		}
		return false;
	}
	
	private boolean valid_lepes(Hely honnan, Hely hova) { //a számolása nagyon magic,de mûködik
		int honnanv1 = honnan.getVonal1().getSzam();
		int hovav1 = hova.getVonal1().getSzam();
		int honnanv2 = honnan.getVonal2().getSzam();
		int hovav2 = hova.getVonal2().getSzam();
		int kulonbseg;
		if (honnanv1 == hovav1) {
			switch(honnanv1) {
			case 1:
			case 8:
				kulonbseg = 3;
				break;
			case 2:
			case 7:
				kulonbseg = 2;
				break;
			default:
				kulonbseg = 1;
			}
			if (Math.abs(honnanv2 - hovav2) == kulonbseg || Math.abs(honnanv2 - hovav2) == kulonbseg +1) {
				return true;
			}
		}
		if (honnanv2 == hovav2) {
			switch(honnanv2) {
			case 9:
			case 16:
				kulonbseg = 3;
				break;
			case 10:
			case 15:
				kulonbseg = 2;
				break;
			default:
				kulonbseg = 1;
			}
			if (Math.abs(honnanv1 - hovav1) == kulonbseg || Math.abs(honnanv1 - hovav1) == kulonbseg +1) {
				return true;
			}
		}
		return false;
	}
	
	public boolean Lep(Hely honnan, Hely hova, Color c) {
		for (Hely thonnan: helyek) {
			for (Hely thova: helyek) {
				if (thonnan.equals(honnan) && thova.equals(hova) && thova.getSzin() == null) {
					int babuszam;
					if (c == Color.black)
						babuszam = fbabuszam;
					else 
						babuszam = pbabuszam;
					if (babuszam != 3) {
						if (valid_lepes(thonnan, thova)) {
						thova.setSzin(thonnan.getSzin());
						thonnan.setSzin(null);
						malom_frissit();
						return true;
						}
					}
					else {
						thova.setSzin(thonnan.getSzin());
						thonnan.setSzin(null);
						malom_frissit();
						return true;
					}
					
				}
			}
		}
		return false;
	}
	
	public boolean levesz(Hely honnan, Color c) {
		boolean csakmalombanvanbabu = true;
		for (Hely hely: helyek) {
			if (hely.getSzin() == c && !hely.malomban)
				csakmalombanvanbabu = false;
		}
		for (Hely hely: helyek) {
			if (honnan.equals(hely) && (hely.malomban == false || csakmalombanvanbabu)) {
				hely.setSzin(null);
				if (c == Color.black)
					--fbabuszam;
				else
					--pbabuszam;
				malom_frissit();
				return true;
			}
				
		}
		return false;
		
	}
	
	public void malom_frissit() {
		for (Hely hely: helyek) {
			hely.elozo_malomban = hely.malomban;
		}
		for (Hely hely: helyek) {
			hely.malomban = false;
		}
		for (int i = 1; i <= 16; i++) {							//végigmegy a vonalakon
			ArrayList<Hely> c = new ArrayList<Hely>();
			for (Hely hely: helyek) {
				if (i == hely.getVonal1().getSzam() || i == hely.getVonal2().getSzam()) //kiválasztja a 3 helyet, ami azon a vonalon van
					c.add(hely);
			}
			if (c.get(0).getSzin() == c.get(1).getSzin() && c.get(1).getSzin() == c.get(2).getSzin() && c.get(0).getSzin() != null) {
			//ha a három helyen azonos bábu van, akkor malom
				for (Hely j: c) {
					j.malomban = true;
				}
			}
		}
	}
	
	public Color volt_malom() {
		for (Hely hely: helyek) {
			if (hely.elozo_malomban == false && hely.malomban == true) {
				return hely.getSzin();
			}
				
		}
		return null;
	}
}
