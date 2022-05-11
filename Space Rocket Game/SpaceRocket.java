import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.Random;

public class SpaceRocket extends Applet implements KeyListener, FocusListener
{
	private int numLetters = 10;  
	private int numCities  =  8;	
	private int appletWidth;	
   
	private int appletHeight;		
	private Letter letters[];		
	private City cities[];			
	private Rocket rocket;	
   private int score = 0;
   private int cityNum = 8;
	private Graphics g, gBuffer;	
   private Image virtualMem;     

   private char keyFired;			
	private boolean focus;		
									
   private AudioClip audio;

	public void init()
	{
  		appletWidth = getWidth();
		appletHeight = getHeight();

		virtualMem = createImage(appletWidth,appletHeight);
		gBuffer = virtualMem.getGraphics();

		letters = new Letter[numLetters];       	
		for (int j = 0; j < numLetters; j++)
			letters[j] = getNewLetter(j,j,gBuffer);	

		cities = new City[numCities];  				
		for (int j = 0; j < numCities; j++)
			cities[j] = new City(gBuffer,j);		

		rocket = new Rocket(gBuffer);		 

		keyFired = ' ';
		addKeyListener(this);
		addFocusListener(this);
		focus = false;
     
	}


	public void paint(Graphics g)
	{
		this.g = g;

		if (!focus)
			titleScreen();  
  		else
		{
			Grfx.setStar(gBuffer,Color.black);
         drawLetters();
			drawCities();
			rocket.drawRocket();
         showScores();
         checkEnd();
		}

		g.drawImage (virtualMem,0,0,this);
		repaint(); 
	}


	public void titleScreen()
	{
		gBuffer.setColor(Grfx.darkBlue);
		gBuffer.fillRect(0,0,appletWidth, appletHeight);
		gBuffer.setFont(new Font("Algerian",Font.ITALIC,72));
		gBuffer.setColor(Color.white);
		gBuffer.drawString("Space Rocket Game",150,150);
		gBuffer.setFont(new Font("Times New Roman",Font.BOLD,24));
		gBuffer.drawString("By: Regina Zhou",250,200);
      gBuffer.setFont(new Font("Calibri",Font.BOLD,36));
      gBuffer.setColor(Color.yellow);
		gBuffer.drawString("How to play:",150,300);
      gBuffer.drawString("Type the corresponding letters fallig from the sky",200,350);
      gBuffer.drawString("to save cities from destruction.",150,400);
      gBuffer.drawString("Letters may change forms or vanish while falling, ",200,450);
      gBuffer.drawString("so react quickly before they trick you!",150,500);
      gBuffer.setColor(Color.white);
      gBuffer.setFont(new Font("Times New Roman",Font.BOLD,40));
      gBuffer.drawString("Click on screen to start game.",240,630);



	}


	public void drawLetters()
	{
		for (int j = 0; j < numLetters; j++)
		{
			letters[j].fall();         
         
         if (!letters[j].isExploding() && letters[j].getLetter() == keyFired && letters[j].onScreen())
			{
            letters[j].triggerExplosion();
            score = score +1;
         }

			if (!letters[j].isExploding() && letters[j].getLetter() == keyFired && letters[j].onScreen())
			{
            letters[j].triggerExplosion();
            score = score +1;
         }
            

			if (letters[j].isBeingTrackedAndShot())
				rocket.drawLaser(letters[j]);

			if (letters[j].hitBottom())
			{
				for (int c = 0; c < numCities; c++)
				{
               if (!letters[j].isExploding() && cities[c].isAlive() && cities[c].hit(letters[j].getColumn()))  // Did a letter hit a city when it hit bottom?
					{	
                  cities[c].triggerExplosion();
                  cityNum--;
               }
               
            }
           
				letters[j] = getNewLetter(j,numLetters,gBuffer);
			}

			if (!letters[j].isAlive())
			{
				letters[j] = getNewLetter(j,numLetters,gBuffer);
			}
         
         
         		}
	}

   public void showScores()  
   {
      String scoreStr = String.valueOf(score);
      gBuffer.setColor(Color.white);
      gBuffer.setFont(new Font("TimesRoman", Font.BOLD, 30)); 
      gBuffer.drawString("Score: " + scoreStr,15,30);   
	}

   public void checkEnd()  
   {
     if (cityNum <= 0)
     { 
         gBuffer.setColor(Color.blue);
         gBuffer.fillRect(0,0,4800,3600);
         gBuffer.setColor(Color.red);
         gBuffer.drawString("Game Over", 200,100);
         gBuffer.drawString("Score: " + String.valueOf(score), 200,200);
         
     }
	}

	public void update(Graphics g)
	{
		paint(g);
	}


	public Letter getNewLetter(int insertIndex, int currentArraySize, Graphics gBuffer)
	{
		Letter temp = getSpecialLetter();

		boolean duplicateColumn = true;
		while (duplicateColumn)
		{
		   	duplicateColumn = false;
		   	for (int k = 0; k < currentArraySize; k++)
		    	if (k != insertIndex && temp.getColumn() == letters[k].getColumn())
		    		duplicateColumn = true;
			if (duplicateColumn)
			    temp = getSpecialLetter();
		}
		return temp;
	}


	private Letter getSpecialLetter()
	{
		Letter temp;
		switch(Grfx.random(1,6))
		{
			case 1  : temp = new FastLetter(gBuffer); break;
			case 2  : temp = new VanishingLetter(gBuffer); break;
			case 3  : temp = new ShrinkingLetter(gBuffer); break;
			default : temp = new Letter(gBuffer);
		}
		return temp;
	}


	public void drawCities()
	{
		for (int x = 0; x < numCities; x++)
			cities[x].drawCity();
	}


	public void focusGained(FocusEvent e) { focus = true;  }

	public void focusLost(FocusEvent e) 	{ focus = false; }

	public void keyTyped(KeyEvent e)
	{
		keyFired = String.valueOf(e.getKeyChar()).toUpperCase().charAt(0);
		if (keyFired == '0') 
			keyFired = 'O';
	}


   public void keyReleased(KeyEvent e) { }

	public void keyPressed(KeyEvent e)  { }  
}