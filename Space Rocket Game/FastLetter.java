import java.awt.*;
import java.applet.*;


public class FastLetter extends Letter
{
	public FastLetter(Graphics g)
	{
		super(g);
	}

	public void fall()  
  	{
		y++;
		drawLetter();
	}
}


