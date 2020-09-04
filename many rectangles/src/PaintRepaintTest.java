import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class PaintRepaintTest extends JPanel implements MouseListener {
   private Vector v;
   public PaintRepaintTest() {
      v = new Vector();
      setBackground(Color.white);
      addMouseListener(this);
   }
   public void paint(Graphics g) { // paint() method
      super.paint(g);
      g.setColor(Color.black);
      Enumeration enumeration = v.elements();
      while(enumeration.hasMoreElements()) {
         Point p = (Point)(enumeration.nextElement());
         g.drawRect(p.x-20, p.y-20, 40, 40);
      }
   }
  public void mousePressed(MouseEvent me) {
      v.add(me.getPoint());
      repaint(); // call repaint() method
   }
   public void mouseClicked(MouseEvent me) {}
   public void mouseEntered(MouseEvent me) {}
   public void mouseExited(MouseEvent me) {}
   public void mouseReleased(MouseEvent me) {
   }
   public static void main(String args[]) {
      JFrame frame = new JFrame();
      frame.getContentPane().add(new PaintRepaintTest());
      frame.setTitle("PaintRepaint Test");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);
      frame.setSize(375, 250);
      frame.setVisible(true);
   }
}