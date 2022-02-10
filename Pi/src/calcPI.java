
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JComponent;
import javax.swing.JFrame;



public class calcPI extends JComponent{
	static JFrame frame = new JFrame("Fern Leaf");
	public static void main(String[] args) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0,0,1000,1000);
		frame.getContentPane().add(new calcPI());
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setVisible(true);
	}
	ArrayList<ArrayList<Double> > points = new ArrayList<ArrayList<Double> >();
	int n=0,cnt = 0,tot=0;
	double r = 2,d=300.0;
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
               RenderingHints.VALUE_RENDER_QUALITY);

        g2D.setRenderingHints(rh);
        g2D.translate(frame.getWidth()/2, frame.getHeight()/2);	
		points.add(new ArrayList<Double>());
		points.get(n).add(0,d*Math.random() - d/2);
		points.get(n).add(1,d*Math.random() - d/2);
		n++;
		float random = (float)(100.000 * Math.random());
		g2D.setStroke(new BasicStroke(2f));
		cnt = 0;
		for(ArrayList a : points) {
			double x = (double) a.get(0);
			double y = (double) a.get(1);
			if( x*x + y*y   <= d*d/4.0) cnt++;
			g2D.setColor(Color.RED);
			g2D.draw(new Ellipse2D.Double((double) a.get(0) - r/2, (double) a.get(1) - r/2, r, r));
		}
		g2D.setColor(Color.WHITE);
        g2D.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g2D.drawString("CNT: "+ (double)cnt, 200, 150);
        g2D.drawString("N: "+ (double)n, 200, 175);
        g2D.drawString("PI: "+ (double)cnt*4/n, 200, 200);
        g2D.draw(new Ellipse2D.Double(0-d/2.0, 0-d/2.0, d, d));
        g2D.draw(new Rectangle2D.Double(0-d/2.0, 0-d/2.0, d, d));
        
		repaint();
	}

}
