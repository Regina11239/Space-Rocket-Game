import java.awt.*;
import java.applet.*;
import java.util.*;

public class Letter extends GameThing
{
	protected int x,y;              
	protected Font font;            
  	protected char letter;       	
	protected String letterString;  
	protected int colorNum;         
	protected int count;            
  	protected int speed;	  		  
	protected int centerAmount;    

	public Letter(Graphics g)
	{
		super(g);

		do
		{
			column = Grfx.random(0, numColumns-1); 
		}
		while (column == 16 || column == 17);
		

		x = column * letterSize + centerOffset; 
		y = Grfx.random(-30,-1) * 10;   
		int letNum = Grfx.random(1,35);    
		if (letNum < 10)
			letter = (char) (((int) '0') + letNum);
		else
		    letter = (char) (Grfx.random(65,90));   
		letterString = String.valueOf(letter);

		colorNum = Grfx.random(1,9);

		count = 0;
		speed = 15;

		centerAmount = 2*letterSize/5;  

		maxExplodingCount  = 30;
		explodingCountDown = maxExplodingCount;
		halfExplodingCount = maxExplodingCount / 2;
	}

	public void WarpingLetter()
   {
     if (y == 200)
      {
        Random rand = new Random();
        column = rand.nextInt(34);
        x = column * letterSize + centerOffset;
        font = new Font("Arial",Font.BOLD,letterSize);
		  g.setFont(font);
		  Grfx.setColor(g,colorNum);
		  g.drawString(letterString, x, y); 
      }
   }
   
   public void MorphingLetter()
   {
     if (y == 240)
      {
        Random rand2 = new Random();
        int newLetter = rand2.nextInt(35)+1;
        if (newLetter < 10)
			letter = (char) (((int) '0') + newLetter);  
		else
		    letter = (char) (Grfx.random(65,90));
        letterString = String.valueOf(letter);
        font = new Font("Arial",Font.BOLD,letterSize);
		  g.setFont(font);
		  Grfx.setColor(g,colorNum);
		  g.drawString(letterString, x, y); 
      }
   }
   
   public void triggerExplosion()
	{
		exploding = true;
	}

	public void explode()
	{
		if (!alive)			return;   

		xc = x + centerAmount;
		yc = y - centerAmount;
		int maxR = maxExplodingCount - explodingCountDown;
		for (int r = 0; r < maxR; r++)
		{
		  	if ((r / 5) % 2 == 0)
				g.setColor(Grfx.red);
			else
				g.setColor(Grfx.yellow);
			Grfx.drawCircle(g, xc, yc, r);   
		}

		if (explodingCountDown <= 0)
			alive = false;  
		else if (count == 0)
			explodingCountDown-=1;
			
	}

	public void drawLetter()
	{
		if (!alive)  
			return;
      Random numberW = new Random();
      int letterNum = numberW.nextInt(100);
      if (letterNum>=95)
      {
         WarpingLetter();
      }
      if (letterNum<=5)
      {
         MorphingLetter();
      }

		font = new Font("Arial",Font.BOLD,letterSize);
		g.setFont(font);
		Grfx.setColor(g,colorNum);
		g.drawString(letterString, x, y);

		if (exploding)  
			explode();
	}

	public void fall()
	{
		count++;
		if (count >= speed)
		{
			y++;
			count = 0;
		}
      Random numberM = new Random();
      int MorNum = numberM.nextInt(100);
      if (MorNum<=5)
      {
         MorphingLetter();
      }

      drawLetter();
	}

	public boolean hitBottom() 
  	{
		return y >= appletHeight;
	}

	public char getLetter()   {  return letter;  }

	public int getCenterX()
	{
		xc = x + centerAmount;
		return xc;
	}

	public int getCenterY()
	{
		yc = y - centerAmount;
		return yc;
	}

	public boolean onScreen()
	{
		return y > 10;  
  	}

	public boolean isBeingTrackedAndShot()
	{
		return exploding && explodingCountDown > halfExplodingCount;
	}

}


