import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class series extends JComponent implements Runnable{

	public void run() {
		try {
			Thread.sleep(15);
			repaint();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	static JFrame frame = new JFrame("fourier series");
	double time=0;
	int n=0;
	ArrayList<Float> points = new ArrayList<Float>();
	ArrayList<Float> points2 = new ArrayList<Float>();
	public static void main(String[] args) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(0,0,600,700);
		frame.getContentPane().add(new series());
		frame.setVisible(true);
	}
	float x,y;
	public void paint(Graphics g)
	{
		Graphics2D g2D = (Graphics2D) g;
		g2D.translate(frame.getWidth()/2, frame.getHeight()/2);
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    	rh.put(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
    	g2D.setRenderingHints(rh);
		//Loop:		
		//g2D.setColor(Color.WHITE);
		float radius =50;
		float disp=0;
		x=0;
		y=0;
		for(int j=0;j<2;j++) {
			float prevx=x;
			float prevy=y;
			float k=2*j+1;
			x += (float) (1.27*radius*Math.cos(k*time))/k;
			y += (float) (1.27*radius*Math.sin(k*time))/k;
			disp+=radius/k;
			g2D.setPaint(Color.WHITE);
			//first circle
			g2D.draw(new Ellipse2D.Float(-200-radius/k+radius+prevx,-100-radius/k+radius+prevy,2*radius/k,2*radius/k));
			//first radius
			g2D.draw(new Line2D.Float(-200+radius+prevx,-100+radius+prevy,radius+x-200,radius+y-100));
			//first point
			g2D.fill(new Ellipse2D.Float(radius+x-204,radius+y-104,8,8));
			//points.add(0,(float) y);
			//points2.add(0,(float) x);
		}
		points.add(0, y);
		//points2.add(0,(float) x);
		//point to curve connection line
		g2D.draw(new Line2D.Float(radius+x-200,radius+y-100,2*radius-102,points.get(0)+radius-100));
		for(int i=0;i<points.size();i++)
		{
			g2D.setPaint(Color.WHITE);
			//g2D.draw(new Line2D.Float(radius+x+frame.getWidth()/2-404,radius+y+frame.getHeight()/2-104,radius+i+frame.getWidth()/2-202,points.get(i)+radius+frame.getHeight()/2-102));
			//curve
			g2D.fill(new Ellipse2D.Float(2*radius+i-102,points.get(i)+radius-102,4,4));
			//g2D.setPaint(Color.RED);
			//g2D.fill(new Ellipse2D.Float(radius+points2.get(i)-201,points.get(i)+radius-101,2,2));
		}
		time+=0.05;
		if(points.size()>frame.getWidth()/2-2*radius) {
			points.remove(points.size()-1);
			//points2.remove(points2.size()-1);
		}
		run();
		
	}

}
