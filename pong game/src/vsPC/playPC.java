package vsPC;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

class Player {
	double ax=0,ay=0,bx=0,by=0,speed=10,lenby2;
	
	Player(float centerx, float centery, float lenBy2){
		lenby2 = lenBy2;
		ax = centerx;
		bx = centerx;
		ay = centery - lenBy2;
		by = centery + lenBy2;
	}

	public void reset() {
		ay = -30 - lenby2;
		by = -30 + lenby2;
	}
}
class ball{
	double x,y,diameter=20,speed=0.5;
	double dir = 2.000*(new Random()).nextDouble() -1;
	ball(double x, double y){
		this.x = x;
		this.y = y;
		while((dir>=-0.1&&dir<=0.1) || (dir>=0.35&&dir<=0.65) || (dir>=-0.65&&dir<=-0.35) || (dir>=0.9&&dir<=-0.9)) {
			dir = 2.000*(new Random()).nextDouble()-1;
		}
	}
	int collision(float wid, float hei) {
		if(y>=hei/2.000 -55) return 1;//bottom
		else if(y<=-hei/2.000+7) return 2;//top
		else if(x>=wid/2.000 -35) return 3;//right
		else if(x<=-wid/2.000 +15) return 4;//left
		else return 0;
	}
	int bounce(int i, Player p1, Player p2){
		if(i==1) {//bottom
			x += speed*Math.cos(dir*3.1415);
			y -= speed*Math.sin(dir*3.1415);
			dir*=-1;
		}
		else if(i==2) {//top
			x += speed*Math.cos(dir*3.1415);
			y -= speed*Math.sin(dir*3.1415);
			dir*=-1;
		}
		if(i==3) {//right
			if(y>=p2.ay&&y<=p2.by) {
				x -= speed*Math.cos(dir*3.1415);
				y += speed*Math.sin(dir*3.1415);
				//dir*=-1;
				if(dir>0) dir=1-dir;
				else if(dir<0) dir=-1-dir;
			}
			else return -1;//cause p1 won
		}
		else if(i==4) {//left
			if(y>=p1.ay&&y<=p1.by) {
				x -= speed*Math.cos(dir*3.1415);
				y += speed*Math.sin(dir*3.1415);
				//dir*=-1 +3.1415;
				if(dir>0) dir=1-dir;
				else if(dir<0) dir=-1-dir;
			}
			else return -2;//cause p2 won
		}
		return 0;
	}
	public void reset() {
		dir = 2.000*(new Random()).nextDouble() -1;
		while((dir>=-0.1&&dir<=0.1) || (dir>=0.35&&dir<=0.65) || (dir>=-0.65&&dir<=-0.35) || (dir>=0.9&&dir<=-0.9)) {
			dir = 2.000*(new Random()).nextDouble()-1;
		}
		x = -10;
		y = -30;
	}
}

public class playPC extends JComponent implements Runnable, KeyListener{
	playPC(){
		addKeyListener(this);
		setFocusable(true);
	}
	
	static JFrame frame = new JFrame("Playing Pong");
	Player p1 = new Player(10-frame.getWidth()/2,-30,30);
	Player p2 = new Player(-30+frame.getWidth()/2,-30,30);
	ball b = new ball(-10,-30);
	JButton butt = new JButton("Foo");
	boolean  start = false;
	int scoreP1=0,scoreP2=0;
	
	public static void main(String args[]) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0,0,1200,800);
		frame.getContentPane().add(new playPC());
		frame.getContentPane().setBackground(Color.BLACK);	
		frame.setVisible(true);
	}
	
	public void paint(Graphics g)
	{
		
		//addKeyListener(this);
		frame.add(butt);//to draw focus here
		frame.remove(butt);
		Graphics2D g2D = (Graphics2D) g;
		g2D.translate(frame.getWidth()/2, frame.getHeight()/2);		
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    	rh.put(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
    	g2D.setRenderingHints(rh);
		g2D.setColor(Color.WHITE);
		g2D.setStroke(new BasicStroke(8));
		g2D.draw(new Line2D.Double(p1.ax, p1.ay, p1.bx, p1.by));
		g2D.draw(new Line2D.Double(p2.ax, p2.ay, p2.bx, p2.by));
		g2D.fill(new Ellipse2D.Double(b.x-b.diameter/2.000, b.y-b.diameter/2.000, b.diameter, b.diameter));
		if(start==false) {
			g2D.setColor(Color.RED);
			g2D.setFont(new Font("TimesRoman", Font.PLAIN, 20));
			g2D.drawString("Press ENTER to Start", -100, -260);
		}
		else {
			int check = b.collision(frame.getWidth(),frame.getHeight());
			if(check==0) {
				b.x+=b.speed*Math.cos(b.dir*3.1415);
				b.y+=b.speed*Math.sin(b.dir*3.1415);
				//PC difficulty
				p2.ay = (b.y/1.000 - p2.lenby2);
				p2.by = (b.y/1.000 + p2.lenby2);
			}
			else {
				int res = b.bounce(check, p1, p2);
				if(res<0) {
					if(res==-1) scoreP1++;
					else if(res==-2) scoreP2++;
					b.reset();
					p1.reset();
					p2.reset();
					start = false;
					System.out.println("Player1: "+scoreP1+", PC: "+scoreP2);
				}
			}
		}
		//run();
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

	@Override
	public void keyPressed(KeyEvent e) {
		//start game
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			start = true;
		}
//        if (e.getKeyCode()==KeyEvent.VK_UP && b.x>-10.000) {
//        	if(p2.ay>=-frame.getHeight()/2) {
//        		p2.ay-=p2.speed;
//        		p2.by-=p2.speed;
//        	}
//        }
//        else if (e.getKeyCode()==KeyEvent.VK_DOWN && b.x>-10.000) {
//        	if(p2.by<=frame.getHeight()/2-50) {
//        		p2.ay+=p2.speed;
//        		p2.by+=p2.speed;
//        	}
//        }
        //use multithreading so that two keyboard evets run together
        if (e.getKeyCode()==KeyEvent.VK_W) {
        	if(p1.ay>=-frame.getHeight()/2) {
        		p1.ay-=p1.speed;
        		p1.by-=p1.speed;
        	}
        }
        else if (e.getKeyCode()==KeyEvent.VK_S) {
        	if(p1.by<=frame.getHeight()/2-50) {
        		p1.ay+=p1.speed;
        		p1.by+=p1.speed;
        	}
        }
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
}
