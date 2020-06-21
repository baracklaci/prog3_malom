package malom;

//import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Assert;

public class Test {

	@org.junit.Test
	public void testTabla1() {
		Tabla t = new Tabla();
		boolean siker = t.Lep(new Hely(1, 9), new Hely(4, 9), Color.red);
		Assert.assertEquals(true, siker);
	}
	
	@org.junit.Test
	public void testTabla2() {
		Tabla t = new Tabla();
		boolean siker = t.Lep(new Hely(1, 9), new Hely(8, 9), Color.red);
		Assert.assertEquals(false, siker);
	}
	
	@org.junit.Test
	public void testTabla3() {
		Tabla t = new Tabla();
		boolean siker = t.Lep(new Hely(1, 9), new Hely(7, 9), Color.red);
		Assert.assertEquals(false, siker);
	}
	
	@org.junit.Test
	public void testTabla4() {
		Tabla t = new Tabla();
		boolean siker = t.Felrak(new Hely(4, 5), Color.red);
		Assert.assertEquals(false, siker);
	}
	
	@org.junit.Test
	public void testTabla5() {
		Tabla t = new Tabla();
		t.Felrak(new Hely(1, 9), Color.red);
		boolean siker = t.Felrak(new Hely(1, 9), Color.black);
		Assert.assertEquals(false, siker);
	}
	
	@org.junit.Test
	public void testvanott() {
		Tabla t = new Tabla();
		t.Felrak(new Hely(1, 9), Color.red);
		boolean siker = t.Felrak(new Hely(1, 9), Color.black);
		Assert.assertEquals(false, siker);
	}
	
	@org.junit.Test
	public void testmalomban() {
		Tabla t = new Tabla();
		t.Felrak(new Hely(1, 9), Color.red);
		t.Felrak(new Hely(4, 9), Color.black);
		t.Felrak(new Hely(1, 12), Color.red);
		t.Felrak(new Hely(4, 10), Color.black);
		t.Felrak(new Hely(1, 16), Color.red);
		t.levesz(new Hely(4, 9), Color.black);
		t.Felrak(new Hely(4, 9), Color.black);
		t.Felrak(new Hely(2, 12), Color.red);
		t.Felrak(new Hely(4, 11), Color.black);
		boolean siker = t.levesz(new Hely(1, 9), Color.red);
		Assert.assertEquals(false, siker);
	}
	
	@org.junit.Test
	public void testcsakmalomban() {
		Tabla t = new Tabla();
		t.Felrak(new Hely(1, 9), Color.red);
		t.Felrak(new Hely(4, 9), Color.black);
		t.Felrak(new Hely(1, 12), Color.red);
		t.Felrak(new Hely(4, 10), Color.black);
		t.Felrak(new Hely(1, 16), Color.red);
		t.levesz(new Hely(4, 9), Color.black);
		t.Felrak(new Hely(4, 9), Color.black);
		t.Felrak(new Hely(5, 16), Color.red);
		t.Felrak(new Hely(2, 10), Color.black);
		t.Felrak(new Hely(8, 16), Color.red);
		t.levesz(new Hely(2, 10), Color.black);
		t.Felrak(new Hely(4, 11), Color.black);
		boolean siker = t.levesz(new Hely(1, 9), Color.red);
		Assert.assertEquals(true, siker);
	}
	
	@org.junit.Test
	public void testCircle() {
		circle c = new circle(25, Color.red, 5, 5);
		Color color = c.getColor();
		Assert.assertEquals(Color.red, color);
	}
	
	@org.junit.Test
	public void testHely() {
		Hely hely1 = new Hely(1, 16);
		Hely hely2 = new Hely(1, 16, Color.red);
		boolean siker = hely1.equals(hely2);
		Assert.assertEquals(true, siker);
	}

}
