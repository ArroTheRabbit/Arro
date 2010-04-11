import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;


public class Start {
	
	private static final int SCREEN_WIDTH = 800;
	private static final int SCREEN_HEIGHT = 600;
	private static final int NUMBER_OF_PLAYERS = 2;
	
	public static void main(String[] args) throws IOException, SlickException{
		AppGameContainer app = new AppGameContainer(new TheGame(SCREEN_WIDTH, SCREEN_HEIGHT, NUMBER_OF_PLAYERS));
		app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
		app.setShowFPS(false);
		app.setMinimumLogicUpdateInterval(10);
		app.start();
		
	}
	
	

}
