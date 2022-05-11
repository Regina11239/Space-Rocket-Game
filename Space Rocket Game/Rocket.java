import java.awt.*;
import java.applet.*;
import java.util.*;
import java.awt.geom.*;

public class Rocket extends GameThing
{
     Image virtualMem;
	  Graphics gBuffer;
   
   public Rocket(Graphics g)  { super(g); }

	public void drawRocket()
	{
      // draws the body
      g.setColor(Color.red);
      g.fillArc(laserCanTipX - 25, laserCanTipY, 50, 50, 0, 180);
      g.setColor(Color.yellow);
      g.fillRect(laserCanTipX - 25, laserCanTipY + 25, 50, 75);
      g.setColor(Color.blue);
      g.fillOval(laserCanTipX - 20, laserCanTipY + 30, 40, 40);
      g.setColor(Color.cyan);
      g.fillOval(laserCanTipX - 15, laserCanTipY + 35, 30, 30);
      
      // draw the flames
      g.setColor(Color.red);
      int x [] = {laserCanTipX + 21, laserCanTipX - 20, laserCanTipX};
      int y [] = {laserCanTipY + 100, laserCanTipY + 100, laserCanTipY + 150};
      g.fillPolygon(x, y, 3);
      Graphics2D g2 = (Graphics2D) g;
      g2.setColor(Color.red);
      QuadCurve2D curve = new QuadCurve2D.Float();
      curve.setCurve(laserCanTipX - 20, laserCanTipY + 100, laserCanTipX - 25, laserCanTipY + 125, laserCanTipX, laserCanTipY + 150);
      g2.fill(curve);
      curve.setCurve(laserCanTipX + 20, laserCanTipY + 100, laserCanTipX + 25, laserCanTipY + 125, laserCanTipX, laserCanTipY + 150);
      g2.fill(curve); 
      
      g2.setColor(Color.yellow);
      int x1 [] = {laserCanTipX + 11, laserCanTipX - 10, laserCanTipX};
      int y1 [] = {laserCanTipY + 100, laserCanTipY + 100, laserCanTipY + 140};
      g.fillPolygon(x1, y1, 3);
      curve.setCurve(laserCanTipX - 10, laserCanTipY + 100, laserCanTipX - 20, laserCanTipY + 120, laserCanTipX, laserCanTipY + 140);
      g2.fill(curve);
      curve.setCurve(laserCanTipX + 10, laserCanTipY + 100, laserCanTipX + 20, laserCanTipY + 120, laserCanTipX, laserCanTipY + 140);
      g2.fill(curve); 

      //draws the 3 wings
      g.setColor(Color.red);
      int xCoord [] = {laserCanTipX, laserCanTipX - 10, laserCanTipX + 10};
      int yCoord [] = {laserCanTipY + 70, laserCanTipY + 110, laserCanTipY + 110};
      g.fillPolygon(xCoord, yCoord, 3);
      int xCoord1 [] = {laserCanTipX + 25, laserCanTipX + 25, laserCanTipX + 35};
      int yCoord1 [] = {laserCanTipY + 70, laserCanTipY + 110, laserCanTipY + 110};
      g.fillPolygon(xCoord1, yCoord1, 3);
      int xCoord2 [] = {laserCanTipX - 25, laserCanTipX - 25, laserCanTipX - 35};
      int yCoord2 [] = {laserCanTipY + 70, laserCanTipY + 110, laserCanTipY + 110};
      g.fillPolygon(xCoord2, yCoord2, 3);
     
      g.setColor(Color.black);
      g.drawPolygon(xCoord, yCoord, 3);
      g.drawPolygon(xCoord1, yCoord1, 3);
      g.drawPolygon(xCoord2, yCoord2, 3);
	}

	public void drawLaser(Letter letter)
	{
		Grfx.setColor(g,Grfx.random(1,9));
		g.drawLine(laserCanTipX, laserCanTipY,
		           letter.getCenterX(), letter.getCenterY());
	}
}


