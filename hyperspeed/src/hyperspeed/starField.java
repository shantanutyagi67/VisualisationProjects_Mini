package hyperspeed;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

class Star{
	double x;
	double y;
	
	Star(double wid, double hei){
		x=(wid)*(new Random()).nextDouble() -(wid)/2;
		y=(hei)*(new Random()).nextDouble() -(hei)/2;
		//x=(wid)*Math.random()-(wid)/2;
		//y=(hei)*Math.random()-(hei)/2;
		if(x==0)x++;
		if(y==0)y++;
	}
	public void newLoc(double wid, double hei) {
		x=(wid)*(new Random()).nextDouble() -(wid)/2;
		y=(hei)*(new Random()).nextDouble() -(hei)/2;
		//x=(wid)*Math.random()-(wid)/2;
		//y=(hei)*Math.random()-(hei)/2;
		if(x==0)x++;
		if(y==0)y++;
	}
}

@SuppressWarnings("serial")
public class starField extends JComponent implements Runnable{
	
	static JFrame frame = new JFrame("Star Field Hyperspeed");
	static ArrayList<Star> stars = new ArrayList<Star>();
	static int n=200;
	double max = 0.5*Math.sqrt(frame.getWidth()*frame.getWidth() + frame.getHeight()*frame.getHeight());
	
	public static void main(String args[]) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0,0,800,800);
		frame.getContentPane().add(new starField());
//		ImageIcon icon = new ImageIcon("C:\\\\Users\\\\geekSA67\\\\OneDrive\\\\Desktop\\\\star.png");
//		JLabel label = new JLabel(icon);
//		frame.add(label);
		frame.getContentPane().setBackground(Color.BLACK);
		
		for(int i=0;i<n;i++) {
			stars.add(i, new Star( frame.getWidth(), frame.getHeight() ));
		}
		//frame.pack();
		frame.setVisible(true);
		
	}
	double speed=4;
	double scale = 40;
	public void paint(Graphics g)
	{
		Graphics2D g2D = (Graphics2D) g;
		g2D.translate(frame.getWidth()/2, frame.getHeight()/2);		
		RenderingHints rh = new RenderingHints(
		RenderingHints.KEY_ANTIALIASING,
      	RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING,
    	RenderingHints.VALUE_RENDER_QUALITY);
    	g2D.setRenderingHints(rh);
		g2D.setColor(Color.WHITE);
		
		for(int i=0;i<n;i++) {
			//linear mapping function for size as a function of coordinates with restricted domain and range
			//use gravity  mapping function with acceleration derived from boundry conditions
			//use exponential mapping function with coefficient and exponent derived from boundry conditions
			double d = Math.sqrt(stars.get(i).x*stars.get(i).x + stars.get(i).y*stars.get(i).y);
			double size = 1 + (7-1)*d/max;
			//g2D.fill(new Ellipse2D.Double(stars.get(i).x-size/2 , stars.get(i).y-size/2 , size , size ));
			g2D.setStroke(new BasicStroke((float) (1.5*size/7)));
			g2D.draw(new Line2D.Double(stars.get(i).x-scale*speed*stars.get(i).x/max,stars.get(i).y-scale*speed*stars.get(i).y/max,stars.get(i).x,stars.get(i).y));
			stars.get(i).x+=speed*stars.get(i).x/max;
			stars.get(i).y+=speed*stars.get(i).y/max;
			if(d>max) {
				stars.get(i).newLoc(frame.getWidth()-500, frame.getHeight()-500);
				//System.out.println("new");
			}
		}
		Toolkit t=Toolkit.getDefaultToolkit();  
        Image im=t.getImage("C:\\Users\\geekSA67\\OneDrive\\Desktop\\star.png");//.getScaledInstance(200, 100, Image.SCALE_DEFAULT); 
        g2D.drawImage(im, -150/2,-113/2,this);
		//repaint();
		run();
	}
	int cnt=-1700;
	@Override
	public void run() {
		cnt++;
		try {
			if(cnt>0&&cnt<1000) {
				Thread.sleep(2);
				if(scale>=1)
				scale=scale-0.1;
				speed=speed-0.001;
			}
			else if(cnt>1000) {
				Thread.sleep(10);
			}
			//Thread.sleep(1);
			repaint();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
