import java.awt.*;
import java.applet.*;


public class VanishingLetter extends Letter
{
	private int red, green, blue;  
	public VanishingLetter(Graphics g)
	{
		super(g);

		resetColor();
	}

	public void drawLetter()
	{
		if (!alive)  
			return;

		font = new Font("Arial",Font.BOLD,letterSize);
		g.setFont(font);
		Grfx.setColor(g,red,green,blue);
		g.drawString(letterString, x, y);

		if (count == 0) 
		{
			if (red > 0)   red--;     
			if (green > 0) green--;   
  			if (blue > 0)  blue--;    
  		}

		if (invisible())
			resetColor();

		if (exploding)  
			explode();
	}

	private void resetColor()
	{
		switch(Grfx.random(1,6))
		{
			case 1 : red = 255; green =   0; blue =   0; break;  // red
			case 2 : red =   0; green = 255; blue =   0; break;  // green
			case 3 : red =   0; green =   0; blue = 255; break;  // blue
			case 4 : red = 255; green = 255; blue =   0; break;  // yellow
			case 5 : red =   0; green = 255; blue = 255; break;  // cyan
			case 6 : red = 255; green =   0; blue = 255; break;  // magenta
		}
	}

	private boolean invisible()
	{
		return red == 0 && green == 0 && blue == 0;
	}
}


