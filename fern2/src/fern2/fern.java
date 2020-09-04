package fern2;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class fern extends JComponent{
	public static void main(String[] args) {
		JFrame frame = new JFrame("Fern Leaf");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0,0,1000,1000);
		frame.getContentPane().add(new fern());
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setVisible(true);
	}
	ArrayList<ArrayList<Float> > points = new ArrayList<ArrayList<Float> >();
	int n=0;
	float x=0,y=0;
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
               RenderingHints.VALUE_RENDER_QUALITY);

        g2D.setRenderingHints(rh);
		points.add(new ArrayList<Float>());
		points.get(n).add(0,x);
		points.get(n).add(1,y);
		n++;
		float random = (float)(100.000 * Math.random());
		g2D.setStroke(new BasicStroke(2f));
		for(ArrayList a : points) {
			g2D.setColor(Color.RED);
			g2D.draw(new Ellipse2D.Float(131*(float)a.get(0)+500, 900 -87*(float)a.get(1)-5, (float)0.05, (float)0.05));
			
		}
		if(random<=1) {
			x=0;
			y=(float) (0.1600*y);
		}
		else if(random>=2&&random<=86) {
			x=(float) (0.8500*x + 0.0400*y);
			y=(float) (-0.0400*x + 0.8500*y + 1.600);
		}
		else if(random>=87&&random<=93) {
			x=(float) (0.2000*x - 0.2600*y);
			y=(float) (0.2300*x + 0.2200*y + 1.600);
		}
		else {
			x=(float) (-0.1500*x + 0.2800*y);
			y=(float) (0.2600*x + 0.2400*y + 0.4400);
		}
		Toolkit t=Toolkit.getDefaultToolkit();  
        Image im=t.getImage("C:\\Users\\geekSA67\\Downloads\\fernimg.png");
        g2D.drawImage(im, 0,0,this);
		repaint();
	}

}
