import java.awt.*;
import java.applet.*;
import java.util.Random;


public class City extends GameThing
{
	private int index;      
 	private int x1,y1;      
	private int	x2,y2;     
	private int column;     
	private int numLights; 
	private int seed;    
   protected int x,y;             
   protected int centerAmount; 
   protected int count;

	public City(Graphics g, int index)
	{
		super(g);
		seed = Grfx.random(1,2000000000);
		this.index = index;
		numLights = 9;

		if (index < 4) 
		{
			x1 = 4 * index * letterSize + centerOffset;
			x2 = 4 * index * letterSize + 3 * letterSize + centerOffset - 1;
			column = 4 * index;
		}
		else 
  		{
			x1 = 4 * index * letterSize + 3 * letterSize + centerOffset;
			x2 = 4 * index * letterSize + 6 * letterSize + centerOffset - 1;
			column = 4 * index + 3;
		}
		y1 = appletHeight - letterSize - 1;  
		y2 = appletHeight - 1;
		xc = (x1 + x2)/2;  
  		yc = (y1 + y2)/2;
      maxExplodingCount  = 30;
		explodingCountDown = maxExplodingCount;
      halfExplodingCount = maxExplodingCount / 2;
      count = 0;
	}

	public void drawCity()
	{
		if (!alive) 
			return;

		g.setColor(Grfx.orange);
		Grfx.fillRectangle(g,x1,y1,x2,y2);
		g.setColor(Grfx.darkGreen);
		Grfx.drawThickRectangle(g,x1,y1,x2,y2,2);

		int s = 9;
		int buildingBase = appletHeight - letterSize - 1;
		Random rand = new Random(seed);
		for(int j = 0; j < numLights; j++)
		{
			int lightX = x1 + (j+1) * s;
			int lightY = appletHeight - letterSize/2 - 1;
			g.setColor(Grfx.white);
			g.fillRect(lightX-3,lightY-1,4,4);  

			Grfx.setColor(g,rand.nextInt(9) + 1);
			int height = rand.nextInt(20) + 10;
			int buildingHeight = buildingBase - height;
			g.fillRect(lightX-4,buildingHeight,6,height);  
		}
      
      if (exploding) 
			explode();
	}

	public boolean hit(int letterColumn) 
	{
		return 	this.column   == letterColumn ||  
		      	this.column+1 == letterColumn ||  
				this.column+2 == letterColumn;   
	}



	public void triggerExplosion()
	{
		exploding=true;   
	}


	public void explode()
	{
      int maxR = maxExplodingCount - explodingCountDown;
		for (int r = 0; r < maxR; r++)
		{
		  	if ((r / 5) % 2 == 0)
				g.setColor(Grfx.red);
			else
				g.setColor(Grfx.orange);
         	Grfx.drawCircle(g, xc, yc, r);    
  		}
      for (int r = 0; r < maxR; r++)
		{
		  	if ((r / 5) % 2 == 0)
				g.setColor(Grfx.red);
			else
				g.setColor(Grfx.orange);
         	Grfx.drawCircle(g, xc, yc, r);    
  		}
      
  		if (explodingCountDown <= 0)
		alive = false;  
      else if (count == 0)
		explodingCountDown--;
	}
}


