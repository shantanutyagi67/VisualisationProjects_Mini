package animation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.JFrame;


public class function extends JComponent implements Runnable{

	private static final long serialVersionUID = 1L;
	static JFrame frame = new JFrame("Animation");
	static Vector<Double> Y = new Vector<Double>();
	double X =0;
	static double a =0.5;
	static double b = 0.7;
	static int range = 5001;
	static double scale = 2;
	public static void main(String args[]) {
		for(int i=0;i<range;i++) {
			Y.add((double) 0);
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0,0,1600,800);
		frame.getContentPane().add(new function());
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setVisible(true);
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2D = (Graphics2D) g;
		g2D.translate(frame.getWidth()/2, 200);		
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    	rh.put(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
    	g2D.setRenderingHints(rh);
		g2D.setColor(Color.WHITE);
		for(int i=0;i<range;i++) {	
			Y.set(i, calculate(i));
			g2D.fill(new Ellipse2D.Double((i - (range-1)/2)/4,scale*Y.elementAt(i)-1, 2, 2));
		}
		b+=0.0025;
		//scale-=0.01;
		run();
		//repaint();
	}
	
	private double calculate(double i) {
		X = (i - (range-1)/2)/(range-1)/2;
		double sum=0;
		for(int j=0;j<5;j++) {
			sum+= Math.pow(a, j)*Math.cos(Math.PI*X*Math.pow(b, j));
		}
		return 100*sum;
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
