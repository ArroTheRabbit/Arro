import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
 

public class Player implements GameObject {
	
	// Fields
	private String name;		// The name of the player
	private Animation sprite;		// The player's sprite
	private int xPos, yPos, degree, speed, health; // Player info
	// type of magic bunny to use!(air, fire...)
	private String playerType;
	
	// number of frames in each animation
	private int numberOfFrames = 3;
	// milliseconds(?) to show each frame
	private int animationSpeed = 300;
	
	private int width, height; // player's dimensions
	private int scale = 20;  // factor to scale player graphics(if 1, height&width == screen size)
	
	/**
	 * Constructor
	 * @param name		The name of the player
	 * @param type		The sprite of the player as a string
	 * @param xStart	The x position of the start location
	 * @param yStart	The y position of the start location
	 * @param screenHeight the current height of the game window
	 * @param screenWidth the current height of the game window
	 * @throws SlickException0
	 */
	public Player(String name, String type, int xStart, int yStart, int screenHeight, int screenWidth) throws SlickException {
		// Set all fields
		height = screenHeight/scale;
		width = screenWidth/scale;
		this.name = name;
		playerType = type;
		xPos = xStart;
		yPos = yStart;
		degree = 90;
		speed = 0;
		health = 100;
	}

	
	/**
	 * Get the current position of the player
	 * @return the current position of the player as an array.
	 * 			index	type
	 * 			0		xPos
	 * 			1		yPos
	 * 			2		degree
	 * 			3		speed
	 */
	@Override
	public int[] getPosition() {
		int r[] = new int[4];
		
		r[0] = xPos;
		r[1] = yPos;
		r[2] = degree;
		r[3] = speed;
		
		return r;
	}
	@Override
	public int[] getDimension() {
		return new int[]{width, height};
	}
	
	/**
	 * Get the player sprite
	 * @return the sprite as an Slick.Animation
	 */
	@Override
	public Animation getImage() {
		return sprite;
	}

	/**
	 * Get the current health of the player
	 * @return the players health as an integer
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Is the player alive?
	 * @return true / false
	 */
	public boolean isAlive() {
		return health > 0 ? true : false;
	}
	/**
	 * Load the graphics into the player animation. Should be called as part of init()
	 * @throws SlickException
	 */
	public void loadGraphic() throws SlickException{
		Image[] frames = new Image[numberOfFrames];
		String mage = playerType;
		for(int i=1, j=0;i<=numberOfFrames;i++,j++){
			frames[j] = new Image("doc/Images/" + mage + "MageFrames/" + mage + i +".png", new Color(255,255,255)); // placeholder graphics
		}
		sprite = new Animation(frames, animationSpeed);
			
	}
	/**
	 * Draw the player animation on the screen according to the current position and size of the player.
	 */
	public void drawPlayer(){
		// TODO does this belong here or in another class?
		sprite.draw(xPos, yPos, width, height);
	}

}
