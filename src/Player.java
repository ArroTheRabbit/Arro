import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
 

public class Player implements GameObject {
	private String name;
	private Image image;
	private int xPos, yPos;
	private int degree;
	private int v;
	private int health;
	
	public Player(String name, String image, int xStart, int yStart) throws SlickException {
		this.name = name;
		this.image = new Image(image);
		xPos = xStart;
		yPos = yStart;
		degree = 90;
		v = 0;
		health = 100;
		
	}

	@Override
	public int[] getDimension() {
		return null;
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getPosition() {
		int r[] = new int[4];
		
		r[0] = xPos;
		r[1] = yPos;
		r[2] = degree;
		r[3] = v;
		
		return r;
	}

	public boolean isAlive() {
		return health > 0 ? true : false;
	}

}
