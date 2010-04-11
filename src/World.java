import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import org.newdawn.slick.*;
import java.util.HashMap;

public class World{
	private int screenHeight, screenWidth;
	
	// map matrix
	private int[][] tileWorld;
	// map matrix scaled up by factor DETAIL
	private int[][] denseWorld;
	// Number of rows in map matrix
	private int ROW_SIZE;
	// NUmber of columns in map matrix
	private int COLUMN_SIZE;
	// factor to scale up the tile matrix with
	private final int DETAIL = 50;
	// HashMap containing the bitmap Images making up the world
	private HashMap<Integer, Image> bitmaps;
	
	// Intervals for the bitmap tiles
	private static final int AIR = 0;
	private static final int DIRTSTART = 1;
	private static final int DIRTEND = 3;
	private static final int GRASSTART = 4;
	private static final int GRASSEND = 6;
	
	private String mapName;
	
	/**
	 * Constructor
	 * @param mapName name of the map file (in the Maps dir)
	 * @param screenWidth 
	 * @param screenHeight
	 * @throws IOException
	 * @throws SlickException
	 */
	public World(String mapName, int screenWidth, int screenHeight) throws IOException, SlickException{
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.mapName = mapName;
	}
	/**
	 * Reads the map file and constructs a map matrix from it.
	 * @throws IOException
	 */
	public void loadTileWorld() throws IOException{
		File file= new File("doc/Maps/" + mapName);
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		while(reader.ready()){
			String line = reader.readLine();
			Scanner tokenizer = new Scanner(line);
			if(tokenizer.next().equals("//")){
				continue;
			}
			else{
				String[] size = line.split(" ");
				if(size.length>0){
					ROW_SIZE = Integer.parseInt(size[0]);
					COLUMN_SIZE = Integer.parseInt(size[1]);
					break;
				}
			}
		}
		int[][] worldMatrix = new int[ROW_SIZE][COLUMN_SIZE];
		int row = 0;
		try{
			while(reader.ready()){
				String line = reader.readLine();
				Scanner tokenizer = new Scanner(line);
				if(tokenizer.next().equals("//")){
					continue;
				}
				else{
					String[] rowContents  = line.split(" ");
					for(int column = 0;column<rowContents.length;column++){
						worldMatrix[row][column] = Integer.parseInt(rowContents[column]);
					}
					row++;
				}
			}
		}catch(ArrayIndexOutOfBoundsException e){
			// TODO Error message window??
			System.err.println("Felformaterad karta!");
		}
		tileWorld = worldMatrix;
	}
	/**
	 * Creates a denser map matrix for use by the game objects
	 */
	public void loadDenseWorld(){
		int[][] denseMatrix = new int[ROW_SIZE*DETAIL][COLUMN_SIZE*DETAIL];
		int currentTileRow = 0;
		int currentTileColumn = 0;
		int countRowTiles = 0;
		int countColumnTiles = 0;
		for(int i = 0;i<tileWorld.length;i++){
			currentTileColumn = 0;
			countRowTiles++;
			if(countRowTiles%DETAIL == 0){
				currentTileRow++;
			}
			for(int j = 0;j<tileWorld.length;j++){
				countColumnTiles++;
				for(int q = 0;q<DETAIL;q++){
					denseMatrix[i][j*DETAIL + q] = tileWorld[currentTileRow][currentTileColumn];
				}
				currentTileColumn++;
			}
		}
		denseWorld = denseMatrix;
	}
	/**
	 * @return the denser map matrix
	 */
	public int[][] getDenseMatrix(){
		return denseWorld;
	}
	/**
	 * Loads the bitmap tile images from the Images dir into a hashMap, with the tile's corresponding value as the key. 
	 * Should be called as part of init()
	 * @throws SlickException
	 * @throws IOException
	 */
	public void loadBitmaps() throws SlickException, IOException{
		bitmaps = new HashMap<Integer, Image>();
		Image dirt1 = new Image("doc/Images/dirt1.png");
		Image dirt2 = new Image("doc/Images/dirt2.png");
		Image dirt3 = new Image("doc/Images/dirt3.png");
		Image grass1 = new Image("doc/Images/grass1.png");
		Image grass2 = new Image("doc/Images/grass2.png");
		Image grass3 = new Image("doc/Images/grass3.png");
		Image air = new Image("doc/Images/air.png");
		
		bitmaps.put(new Integer(AIR), air);
		bitmaps.put(new Integer(DIRTSTART), dirt1);
		bitmaps.put(new Integer(DIRTSTART + 1), dirt2);
		bitmaps.put(new Integer(DIRTEND), dirt3);
		bitmaps.put(new Integer(GRASSTART), grass1);
		bitmaps.put(new Integer(GRASSTART+1), grass2);
		bitmaps.put(new Integer(GRASSEND), grass3);
	}
	/**
	 * Render the world according to the map matrix.
	 */
	public void drawWorld(){
		// One tile's width in pixels
		int tileWidth = screenWidth/ROW_SIZE;
		// One tile's height in pixels
		int tileHeight = screenHeight/COLUMN_SIZE;
		// Draw the map according to the tileWorld matrix
		for(int i=0;i<tileWorld.length;i++){
			for(int j=0;j<tileWorld[0].length;j++){
				int key = tileWorld[i][j];
				bitmaps.get(new Integer(key)).draw(j*tileWidth, i*tileHeight, tileWidth, tileHeight);
			}
		}
	}
}