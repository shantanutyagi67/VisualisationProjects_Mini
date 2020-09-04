package rainfall;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JComponent;
import javax.swing.JFrame;

class drop{
	
	double x;
	double y;
	double length=5.0*(new Random()).nextDouble()+10;
	double speed=4.0*(new Random()).nextDouble()+2;
	double speedi=speed;
	drop(double x, double y){
		this.x=(x)*(new Random()).nextDouble();
		this.y=(y)*(new Random()).nextDouble()-y;
	}
	void reLoc(double x, double y) {
		this.x=(x)*(new Random()).nextDouble();
		this.y=(y)*(new Random()).nextDouble()-y;
		length = 5.0*(new Random()).nextDouble()+10;
		speed=4.0*(new Random()).nextDouble()+2;
		speedi=speed;
	}
}

@SuppressWarnings("serial")
public class rain extends JComponent implements Runnable{
	
	static int n = 700;
	static JFrame frame = new JFrame("RainFall");
	static ArrayList<drop> drops = new ArrayList<drop>();
	
	public static void main(String args[]) {
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0,0,1920,1080);
		frame.getContentPane().add(new rain());
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setVisible(true);
		
		for(int i=0;i<n;i++) {
			drops.add(i, new drop( frame.getWidth(), frame.getHeight() ));
		}
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2D = (Graphics2D) g;
		//g2D.translate(frame.getWidth()/2, frame.getHeight()/2);
		
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    	rh.put(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
    	g2D.setRenderingHints(rh);
    	
		g2D.setColor(Color.WHITE);
    	g2D.setColor(Color.GREEN);
		for(int i=0;i<drops.size();i++) {
			g2D.setStroke(new BasicStroke((float) (3.0*drops.get(i).speedi/5)));
			g2D.setFont(new Font("TimesRoman", Font.PLAIN, 25));
	        int ran=(int) (10.0*Math.random());
			g2D.drawString(""+ran, (float)drops.get(i).x, (float)drops.get(i).y);
			g2D.draw(new Line2D.Double(drops.get(i).x,drops.get(i).y,drops.get(i).x,drops.get(i).y+drops.get(i).length));
			drops.get(i).y+=drops.get(i).speed;
			drops.get(i).speed+=0.015;
			drops.get(i).speed+=0.0015;
			drops.get(i).length+=0.08*drops.get(i).y/frame.getHeight();
			if(drops.get(i).y>frame.getHeight()) {
				drops.get(i).reLoc(frame.getWidth(), frame.getHeight());
			}
		}
		
		//run();
		repaint();
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
	
}
