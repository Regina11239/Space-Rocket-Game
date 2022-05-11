import java.awt.*;
import java.applet.*;


public class ShrinkingLetter extends Letter
{
	private boolean goingRight;    
  	private int     visibleSize;   

	public ShrinkingLetter(Graphics g)
	{
		super(g);

		visibleSize = (appletHeight - y) / 30 + 12;
		goingRight = Grfx.random(1,2) == 1;
	}

	public void drawLetter()
	{
		if (!alive)  
			return;

		visibleSize = (appletHeight - y) / 30 + 12;
		font = new Font("Arial",Font.BOLD,visibleSize);
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
			if (goingRight)
		    	x++;
			else
				x--;

			if (x < 0)
				goingRight = true;

			if (x + visibleSize > appletWidth)
				goingRight = false;

			column = x / letterSize;
			count = 0;
		}
		drawLetter();
	}
}


