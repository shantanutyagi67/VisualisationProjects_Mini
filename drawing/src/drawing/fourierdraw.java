package drawing;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JComponent;
import javax.swing.JFrame;
class fourier
{
	double freq;
	double amp;
	double phase;
	fourier(double real,double imag, double kk)
	{
		freq= kk;
		amp= Math.sqrt(real*real + imag*imag);
		phase= Math.atan2(imag, real);
	}
}
public class fourierdraw extends JComponent implements Runnable{

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
	ArrayList<Double> points = new ArrayList<Double>();
	static ArrayList<Double> signaly = new ArrayList<Double>();
	static ArrayList<Double> signalx = new ArrayList<Double>();
	ArrayList<Double> points2 = new ArrayList<Double>();
	static ArrayList<fourier> fourierY = new ArrayList<fourier>();
	static ArrayList<fourier> fourierX = new ArrayList<fourier>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		//signal.clear();
		try {
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\geekSA67\\OneDrive\\Desktop\\vegg.txt"));
		    while (br.ready()) {
		        signaly.add(300.000*Double.parseDouble(br.readLine()));
		    }
		}
		 catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		double angle =0;
//		for(int i=0;i<100;i++)
//		{
//			signaly.add(i, 100.0*(Math.sin(angle)+(double)1/3*Math.sin(3*angle)+(double)1/5*Math.sin(5*angle)+(double)1/7*Math.sin(7*angle)));
//			angle+=2*3.141592/100;
//		}
		fourierY = dft(signaly);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(0,0,600,700);
		frame.getContentPane().add(new fourierdraw());
		frame.setVisible(true);
	}
	static ArrayList<fourier> dft(ArrayList<Double> x) {
		int N = x.size();
		ArrayList<fourier> X = new ArrayList<fourier>();
		for(int k=0;k<N;k++)
		{
			double re=0;
			double im=0;
			for(int n=0;n<N;n++)
			{
				double phi = 2*3.14159265*k*n/N;
				re += x.get(n)*Math.cos(phi);
				im -= x.get(n)*Math.sin(phi);
			}
			re=re/N;
			im=im/N;
			//double freq=k;
			//double amp = Math.sqrt(re*re + im*im);
			//double phase = Math.atan2(im, re);
			X.add(k, new fourier(re,im,k)) ;
		}
		return X;
	}

	double x,y;
	public void paint(Graphics g)
	{
		Graphics2D g2D = (Graphics2D) g;
		g2D.translate(frame.getWidth()/2, frame.getHeight()/2);
//		RenderingHints rh = new RenderingHints(
//      	RenderingHints.KEY_ANTIALIASING,
//          	RenderingHints.VALUE_ANTIALIAS_ON);
//
//    	rh.put(RenderingHints.KEY_RENDERING,
//      	RenderingHints.VALUE_RENDER_QUALITY);
//      g2D.setRenderingHints(rh);
		//Loop:		
		//g2D.setColor(Color.WHITE);
		//double radius =50;
		//float disp=0;
		x=0;
		y=0;
		for(int j=0;j<fourierY.size();j++) {
			double prevx=x;
			double prevy=y;
			double k=fourierY.get(j).freq;
			double radius=0.7*fourierY.get(j).amp;
			double phase=fourierY.get(j).phase;
			x += (float) (radius*Math.cos(k*time + phase + 3.141592/2));
			y += (float) (radius*Math.sin(k*time + phase + 3.141592/2));
			//disp+=radius/k;
			g2D.setPaint(Color.WHITE);
			//first circle
			g2D.draw(new Ellipse2D.Double(-200-radius+fourierY.get(0).amp+prevx,-100-radius+fourierY.get(0).amp+prevy,2*radius,2*radius));
			//first radius
			g2D.draw(new Line2D.Double(-200+prevx,-100+prevy,+x-200,+y-100));
			//first point
			g2D.fill(new Ellipse2D.Double(+x-204,+y-104,8,8));
			//points.add(0,(float) y);
			//points2.add(0,(float) x);
		}
		points.add(0, y);
		//points2.add(0,(float) x);
		//point to curve connection line
		g2D.draw(new Line2D.Double(50+x-200,50+y-100,2*50-102,points.get(0)+50-100));
		for(int i=0;i<points.size();i++)
		{
			g2D.setPaint(Color.WHITE);
			//g2D.draw(new Line2D.Float(radius+x+frame.getWidth()/2-404,radius+y+frame.getHeight()/2-104,radius+i+frame.getWidth()/2-202,points.get(i)+radius+frame.getHeight()/2-102));
			//curve
			g2D.fill(new Ellipse2D.Double(2*50+i-102,points.get(i)+50-102,4,4));
			//g2D.setPaint(Color.RED);
			//g2D.fill(new Ellipse2D.Float(radius+points2.get(i)-201,points.get(i)+radius-101,2,2));
		}
		time+=2*3.141592/fourierY.size();
		if(points.size()>frame.getWidth()/2-100) {
			points.remove(points.size()-1);
			//points2.remove(points2.size()-1);
		}
		run();
		//repaint();
		
	}

}
