import java.awt.*;
import java.applet.*;

abstract public class GameThing
{
	protected static final int appletWidth  = 1000;  	
  	protected static final int appletHeight =  650; 	

	protected static final int letterSize   =   29;

	protected static final int numColumns   = appletWidth / letterSize;	   

	protected static final int centerOffset = (appletWidth - numColumns * letterSize) / 2;
									 	
	protected static final int laserCanTipX = 500;	
	protected static final int laserCanTipY = 550;	


    
   protected Graphics g;				

	protected int column; 	  		

	protected boolean alive;   	

	protected boolean exploding; 	
    	
	protected int maxExplodingCount; 	

	protected int halfExplodingCount;  

	protected int explodingCountDown;  	

	protected int xc, yc;             

	public GameThing(Graphics g)
	{
		alive = true;
		exploding = false;
		this.g = g;     
  	}

	public int getColumn()	  		{ return column; 	}

	public boolean isExploding()  	{ return exploding; }

	public boolean isAlive()  		{ return alive;  	}

}


