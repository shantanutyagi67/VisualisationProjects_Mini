package clock;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Date;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JFrame;


public class  fast extends JComponent implements Runnable, MouseMotionListener{
	
	static JFrame frame = new JFrame("Analog Clock");
	
	public static void main(String args[]) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0,0,800,800);
		frame.getContentPane().add(new fast());
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setVisible(true);
	}
double ws=60,wm=1/1,wh=(double)1/12;
double t=0;
public void paint(Graphics g) {
	update(g);
}
	public void update(Graphics g)
	{
		addMouseMotionListener( this );
		String time[]= {"12","1","2","3","4","5","6","7","8","9","10","11"};
		Graphics2D g2D = (Graphics2D) g;
		g2D.translate(frame.getWidth()/2, frame.getHeight()/2);		
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    	rh.put(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
    	g2D.setRenderingHints(rh);
    	g2D.setColor(Color.WHITE);
//		Date date = new Date();
//		//System.out.println(date.toString());
//		int hh=Integer.parseInt(date.toString().substring(11, 13));
//		int mm=Integer.parseInt(date.toString().substring(14, 16));
//		int ss=Integer.parseInt(date.toString().substring(17, 19));
		
		//fast
		
//		System.out.println(""+hh+":"+mm+":"+ss);
//		Arc2D.Float harc = new Arc2D.Float();
//		Arc2D.Float hhand = new Arc2D.Float();
//		Arc2D.Float marc = new Arc2D.Float();
//		Arc2D.Float mhand = new Arc2D.Float();
//		Arc2D.Float sarc = new Arc2D.Float();
//		Arc2D.Float shand = new Arc2D.Float();
		g2D.fill(new Ellipse2D.Double(-150, -150, 300, 300));
		g2D.setStroke(new BasicStroke(7f));
//		//harc.setArcByCenter(0, 0, 100, 90, -30*(hh%12), 0);
//		hhand.setArcByCenter(0, 0, 80, 90-30*(hh%12)-(mm%60)/2-(ss%60)/120, 0, 2);
//		//marc.setArcByCenter(0, 0, 110, 90,-6*mm , 0);
//		mhand.setArcByCenter(0, 0, 100,90-6*(mm%60)-(ss%60)/10 , 0,2);
//		//sarc.setArcByCenter(0, 0, 120, 90, -6*ss, 0);
//		shand.setArcByCenter(0, 0, 125,90 -6*(ss%60), 0,2);
		g2D.setColor(Color.PINK);
		g2D.draw(new Line2D.Double(0, 0, 80*Math.cos(3.14159265/180*wh*t-3.14159265/2), 80*Math.sin(3.14159265/180*wh*t-3.14159265/2)));
//		//g2D.draw(harc);
//		g2D.draw(hhand);
		g2D.setColor(Color.ORANGE);
		g2D.draw(new Line2D.Double(0, 0, 100*Math.cos(3.14159265/180*wm*t-3.14159265/2), 100*Math.sin(3.14159265/180*wm*t-3.14159265/2)));
//		//g2D.draw(marc);
//		g2D.draw(mhand);
		g2D.setColor(Color.RED);
		g2D.draw(new Line2D.Double(0, 0, 125*Math.cos(3.14159265/180*ws*t-3.14159265/2), 125*Math.sin(3.14159265/180*ws*t-3.14159265/2)));
//		//g2D.draw(sarc);
//		g2D.draw(shand);
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
		t+=0.05;
		run();
		//repaint();
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(1);
			repaint();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//arg0.get
		
	}
	
}

