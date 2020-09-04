package clock;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.util.Date;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JFrame;


public class  clock extends JComponent implements Runnable{
	
	static JFrame frame = new JFrame("Digital Clock");
	
	public static void main(String args[]) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0,0,800,800);
		frame.getContentPane().add(new clock());
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setVisible(true);
	}
	
	public void paint(Graphics g)
	{
		String time[]= {"12","1","2","3","4","5","6","7","8","9","10","11"};
		Graphics2D g2D = (Graphics2D) g;
		g2D.translate(frame.getWidth()/2, frame.getHeight()/2);		
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    	rh.put(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
    	g2D.setRenderingHints(rh);
    	g2D.setColor(Color.WHITE);
		Date date = new Date();
		//System.out.println(date.toString());
		int hh=Integer.parseInt(date.toString().substring(11, 13));
		int mm=Integer.parseInt(date.toString().substring(14, 16));
		int ss=Integer.parseInt(date.toString().substring(17, 19));
		hh=5;
		mm=30;
//		System.out.println(""+hh+":"+mm+":"+ss);
		Arc2D.Float harc = new Arc2D.Float();
		Arc2D.Float hhand = new Arc2D.Float();
		Arc2D.Float marc = new Arc2D.Float();
		Arc2D.Float mhand = new Arc2D.Float();
		Arc2D.Float sarc = new Arc2D.Float();
		Arc2D.Float shand = new Arc2D.Float();
		g2D.fill(new Ellipse2D.Double(-150, -150, 300, 300));
		g2D.setStroke(new BasicStroke(10f));
		harc.setArcByCenter(0, 0, 100, 90, -30*(hh%12), 0);
		hhand.setArcByCenter(0, 0, 100, 90-30*(hh%12), 0, 2);
		marc.setArcByCenter(0, 0, 110, 90,-6*mm , 0);
		mhand.setArcByCenter(0, 0, 110,90-6*mm , 0,2);
		sarc.setArcByCenter(0, 0, 120, 90, -6*ss, 0);
		shand.setArcByCenter(0, 0, 120,90 -6*ss, 0,2);
		g2D.setColor(Color.PINK);
		//g2D.draw(harc);
		g2D.draw(hhand);
		g2D.setColor(Color.ORANGE);
		//g2D.draw(marc);
		g2D.draw(mhand);
		g2D.setColor(Color.RED);
		//g2D.draw(sarc);
		g2D.draw(shand);
		g2D.setColor(Color.DARK_GRAY);

//		g2D.setFont(new Font("TimesRoman", Font.BOLD, 25));
//		g2D.drawString(""+hh%12+" : "+mm+" : "+ss, -55, 12);
		//run();
		double x=0,y=0;//center
    	for(int i=0;i<time.length;i++) {
    		x=140*Math.cos(-3.14159265/2+3.14159265/6*(i));
    		y=140*Math.sin(-3.14159265/2+3.14159265/6*(i));
//    		if(x<0&&y>0);
//    		if(x<0&&y<0) {
//    		}
//    		if(x>0&&y<0);
    		
    		g2D.setFont(new Font("TimesRoman", Font.BOLD, 25));
    		g2D.drawString(time[i], (float)(x-7.5), (float)(y+9));
    		
    	}
		g2D.fill(new Ellipse2D.Double(-10, -10, 20, 20));
		repaint();
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(100);
			repaint();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

