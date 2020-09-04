package noise;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class circle extends JComponent implements Runnable{
	static ArrayList<Double> rad = new ArrayList<Double>();
	static JFrame frame = new JFrame("Perimeter noise");
	public static void main(String[] args) {
		for(int j=0;j<50;j++) {
			for(int i=0;i<5;i++) {
				rad.add((double) j);
			}
		}
		for(int j=49;j>=0;j--) {
			for(int i=0;i<5;i++) {
				rad.add((double) j);
			}
		}
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0,0,900,900);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.getContentPane().add(new circle());
		frame.setVisible(true);

	}
	int cnt=0;
	int cnt2=0;
	ArrayList<Double> x = new ArrayList<Double>();
	ArrayList<Double> y = new ArrayList<Double>();
	
	public void paint(Graphics g)
	{
		Graphics2D g2D = (Graphics2D) g;
		
		RenderingHints rh = new RenderingHints(
  	RenderingHints.KEY_ANTIALIASING,
      	RenderingHints.VALUE_ANTIALIAS_ON);

	rh.put(RenderingHints.KEY_RENDERING,
  	RenderingHints.VALUE_RENDER_QUALITY);
  g2D.setRenderingHints(rh);		
		
		double radius =300;
		x.clear();
		y.clear();
		g2D.translate(frame.getWidth()/2, frame.getHeight()/2);
		g2D.setStroke(new BasicStroke(2f));
		g2D.setColor(Color.WHITE);
		//g2D.fill(new Ellipse2D.Double(-radius,-radius, 2*radius, 2*radius));
		//g2D.setColor(Color.RED);
		for(double i=0;i<2*3.14159265;i+=0.01)
		{
			
//			int random1 = (int) (10.0*Math.random());
//			int random2 = (int) (10.0*Math.random());
//			if(random1%2==0) {
				x.add(radius*Math.cos(i)+30.00*(new Random()).nextDouble()-15.00);//+random1);
				y.add(radius*Math.sin(i)+30.00*(new Random()).nextDouble()-15.00);//+random2);
//			}
//			else {
//				y.add(radius*Math.sin(i));//-random1);
//				x.add(radius*Math.cos(i));//-random2);
//				}
			//g2D.fill(new Ellipse2D.Double(x-3,y-3, 6, 6));
			
		}
		Polygon polygon = new Polygon();
		for(int i=cnt;i<x.size();i+=5)//i+=5)
		{
			polygon.addPoint((int)(x.get(i)+0),(int)(y.get(i)+0));
			//polygon.addPoint((int)(x.get(i)),(int)(y.get(i)));
			//g2D.fill(new Ellipse2D.Double(x.get(i)+rad.get(cnt2)*Math.cos(30*3.141*i/x.size())-3,y.get(i)+rad.get(cnt2)*Math.sin(30*3.141*i/x.size())-3, 6, 6));
		}
		g2D.fillPolygon(polygon);
		cnt = (cnt + 1)%5; 
		cnt2 = (cnt2 + 1)%rad.size();
		//System.out.println(cnt);
		run();
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
