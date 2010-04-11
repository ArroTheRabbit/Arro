import java.io.IOException;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class TheGame implements Game{
	// The game world
	private World world;
	// Array of the players in the current game
	private Player[] players;
	
	private int screenWidth, screenHeight;
	// Name of the database file, should probably be given as an argument or similar, putting it here for now
	private final String pathName = "Test10x10map.txt";
	
	/**
	 * constructor
	 * @param screenWidth width of current game screen
	 * @param screenHeight height of current game screen
	 * @param numberOfPlayers well, duh.
	 * @throws IOException
	 * @throws SlickException
	 */
	public TheGame(int screenWidth, int screenHeight, int numberOfPlayers) throws IOException, SlickException{
		players = new Player[numberOfPlayers];
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		players[0] = new Player("playerOne", "air", 100,100,screenWidth,screenHeight);  // Player one, air mage (purple) (TESTGUBBE)
		players[1] = new Player("playerTwo", "fire", 600,100,screenWidth,screenHeight);	// Player two, fire mage (red) (TESTGUBBE)
		world = new World(pathName, screenWidth, screenHeight);
		// Read and initialize world matrix
		world.loadTileWorld();
		world.loadDenseWorld();
		
		
	}
	
	@Override
	/**
	 * Enables the game to close from pressing X
	 */
	public boolean closeRequested() {
		return true;
	}
	@Override
	public String getTitle() {
		return "ArrO the magic bunny!";
	}

	@Override
	public void init(GameContainer container){
		// These lines initializes the images for the world and game objects
			try {
				world.loadBitmaps();
				for(Player player : players){
					player.loadGraphic();
				}
			} catch (SlickException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		// render world
		world.drawWorld();
		// render players
		for(int i=0;i<players.length;i++){
			players[i].drawPlayer();
		}
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}

}
