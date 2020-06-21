package malom;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

public class malomframe extends JFrame implements ActionListener{
	private Tabla t;
	private JTextField tf;
	private ArrayList<circle> fekete;
	private ArrayList<circle> piros;
	private ArrayList<Movement> movement;
	
	class Szerializalas implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			try {
		         FileOutputStream fileOut = new FileOutputStream("tabla.txt");
		         ObjectOutputStream out = new ObjectOutputStream(fileOut);
		         out.writeObject(t);
		         BufferedWriter bw = new BufferedWriter(new FileWriter("tf.txt"));
		         bw.write(tf.getText());
		         BufferedWriter br = new BufferedWriter(new FileWriter("szinek.txt"));
		         for (circle x: piros) {
		        	 br.write(Integer.toString(x.getX()));
		        	 br.newLine();
		        	 br.write(Integer.toString(x.getY()));
		        	 br.newLine();
		        	 br.write(new Boolean(x.isVisible()).toString());
		        	 br.newLine();
		         }
		         for (circle x: fekete) {
		        	 br.write(Integer.toString(x.getX()));
		        	 br.newLine();
		        	 br.write(Integer.toString(x.getY()));
		        	 br.newLine();
		        	 br.write(new Boolean(x.isVisible()).toString());
		        	 br.newLine();
		         }
		         if (Movement.elozo_szin == Color.red)
		        	 br.write("red");
		         else
		        	 br.write("black");
		         br.newLine();
		         if (Movement.levehet == Color.red)
		        	 br.write("red");
		         else if (Movement.levehet == Color.black)
		        	 br.write("black");
		         else
		        	 br.write("null");
		         br.close();
		         bw.close();
		         out.close();
		         fileOut.close();
		      } catch (IOException i) {
		         i.printStackTrace();
		      }
			
		}
		
	}
	
	class Deszerializalas implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			try {
		         FileInputStream fileIn = new FileInputStream("tabla.txt");
		         ObjectInputStream in = new ObjectInputStream(fileIn);
		         t = (Tabla) in.readObject();
		         BufferedReader bw = new BufferedReader(new FileReader("tf.txt"));
		         tf.setText(bw.readLine());
		         BufferedReader br = new BufferedReader(new FileReader("szinek.txt"));
		         for (circle x: piros) {
		        	 x.setLocation(Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()));
		        	 x.setVisible(Boolean.parseBoolean(br.readLine()));
		         }
		         for (circle x: fekete) {
		        	 x.setLocation(Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()));
		        	 x.setVisible(Boolean.parseBoolean(br.readLine()));
		         }
		         String str = br.readLine();
		         if (str.equals("red"))
		        	 Movement.elozo_szin = Color.red;
		         else
		        	 Movement.elozo_szin = Color.black;
		         str = br.readLine();
		         if (str.equals("red"))
		        	 Movement.levehet = Color.red;
		         else if (str.equals("black"))
		        	 Movement.levehet = Color.black;
		         else
		        	 Movement.levehet = null;
		         Movement.setTabla(t);
		         br.close();
		         in.close();
		         fileIn.close();
		      } catch (IOException i) {
		         i.printStackTrace();
		      } catch (ClassNotFoundException i2) {
				 i2.printStackTrace();
			  }
			
		}
		
	}
	
	public malomframe(Tabla T) throws IOException {
		t = T;
		BufferedImage malomimg = ImageIO.read(new File("malom.jpg"));;
		Image malom = malomimg.getScaledInstance(400, 400, Image.SCALE_DEFAULT);
		BufferedImage kukaimg = ImageIO.read(new File("kuka.jpg"));;
		Image kuka = kukaimg.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		
		setSize(600, 467);
		JLayeredPane lp = getLayeredPane();
		
		JButton b1 = new JButton("Új játék");
		b1.addActionListener(this);
		b1.setBounds(0, 0, 200, 30);
		b1.setBackground(Color.white);
		JButton b2 = new JButton("Mentett játék folytatása");
		b2.setBounds(200, 0, 200, 30);
		b2.setBackground(Color.white);
		JButton b3 = new JButton("Játék mentése");
		b3.setBounds(400, 0, 200, 30);
		b3.setBackground(Color.white);
		Szerializalas sz = new Szerializalas();
		b3.addActionListener(sz);
		Deszerializalas dsz = new Deszerializalas();
		b2.addActionListener(dsz);
		lp.add(b1, new Integer(0));
		lp.add(b2, new Integer(0));
		lp.add(b3, new Integer(0));
		
		
		
		tf = new JTextField();
		tf.setEditable(false);
		tf.setBounds(0, 50, 200, 20);
		lp.add(tf, new Integer(0));
		tf.setHorizontalAlignment(JTextField.CENTER);
		
		movement = new ArrayList<Movement>();
		fekete = new ArrayList<circle>();
		for(int i = 0; i < 9; i++) {
			fekete.add(new circle(25, Color.black, 5, 5));
		}
		piros = new ArrayList<circle>();
		for(int i = 0; i < 9; i++) {
			piros.add(new circle(25, Color.red, 5, 5));
		}
		for(circle x : fekete) {
			movement.add(new Movement(t, x, this));
			x.setBounds(50, 100, 25, 25);
			lp.add(x, new Integer(6));
		}
		for(circle x : piros) {
			movement.add(new Movement(t, x, this));
			x.setBounds(50, 200, 25, 25);
			lp.add(x, new Integer(6));
		}
		
	    JLabel kep = new JLabel();
	    ImageIcon malom_icon = new ImageIcon(malom);
	    kep.setIcon(malom_icon);
	    kep.setBounds(200, 30, 400, 400);
		lp.add(kep, new Integer (0));
		JLabel kep2 = new JLabel();
	    ImageIcon kuka_icon = new ImageIcon(kuka);
	    kep2.setIcon(kuka_icon);
	    kep2.setBounds(50, 300, 50, 50);
		lp.add(kep2, new Integer (0));
		
		setTitle("malom");
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e) {
		t.reset();
		Movement.reset();
		tf.setText("A fekete léphet!");
		for(circle x : fekete) {
			x.setLocation(50, 100);
			x.setVisible(true);
		}
		for(circle x : piros) {
			x.setLocation(50, 200);
			x.setVisible(true);
		}
	}
	
	public void setText(String s) {
		tf.setText(s);
	}
	
	public static void main(String args[]) throws IOException {
		Tabla tabla = new Tabla();
		malomframe m = new malomframe(tabla);
	}

	
}
