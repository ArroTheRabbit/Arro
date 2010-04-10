import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
 

public class Player implements GameObject {
	
	// Fields
	private String name;		// The name of the player
	private Image image;		// The player's sprite
	private int xPos, yPos, degree, speed, health; // Player info
	
	/**
	 * Constructor
	 * @param name		The name of the player
	 * @param image		The sprite of the player as a string
	 * @param xStart	The x position of the start location
	 * @param yStart	The y position of the start location
	 * @throws SlickException
	 */
	public Player(String name, String image, int xStart, int yStart) throws SlickException {
		// Set all fields
		this.name = name;
		this.image = new Image(image);
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

	// TODO
	@Override
	public int[] getDimension() {
		return null;
	}
	
	/**
	 * Get the player sprite
	 * @return the sprite as an Slick.Image
	 */
	@Override
	public Image getImage() {
		return image;
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

}
