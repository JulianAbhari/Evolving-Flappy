package com.Julian.evolvFlappy.level;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.Julian.evolvFlappy.entities.Entity;
import com.Julian.evolvFlappy.gfx.Screen;
import com.Julian.evolvFlappy.level.tiles.Tile;

public class Level {

	// This is going to be an array of IDs for what tile resides in that specific
	// coordinate
	private byte[] tiles;

	public int width;
	public int height;
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	private String imagePath;
	private BufferedImage image;

	public Level(String imagePath) {
		if (imagePath != null) {
			this.imagePath = imagePath;
			this.loadLevelFromFile();
		} else {
			this.width = 64;
			this.height = 64;
			tiles = new byte[width * height];
			generateLevel();
		}
	}

	private void loadLevelFromFile() {
		try {
			this.image = ImageIO.read(Level.class.getResource(this.imagePath));
			this.width = image.getWidth();
			this.height = image.getHeight();
			tiles = new byte[width * height];
			this.loadTiles();
		} catch (IOException e) {
			// This will catch Input Output exceptions, so if the file can't be read or has
			// been deleted.
			e.printStackTrace();
		}
	}

	private void loadTiles() {
		// This translates the buffered image data into actual numbers
		int[] tileColors = this.image.getRGB(0, 0, width, height, null, 0, width);
		for (int y = 0; y < height; y += 1) {
			for (int x = 0; x < width; x += 1) {
				// This is going through all the tiles and matching it's level color with the
				// colors from the image that was stored in the "tileColors" array.
				tileCheck: for (Tile t : Tile.tiles) {
					// This is comparing the tile colors but also making sure that the tile isn't
					// null, because if it is null then that would waste time and processing power
					// to load nothing.
					if (t != null && t.getLevelImageColor() == tileColors[x + y * width]) {
						this.tiles[x + y * width] = t.getId();
						t.setXLevel(x);
						t.setYLevel(y);
						break tileCheck;
					}
				}
			}
		}
	}

	public void saveLevelToFile() {
		try {
			// This takes the image and inserts it into a new file
			ImageIO.write(image, "png", new File(Level.class.getResource(this.imagePath).getFile()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// This sets the image that was inserted into a new file with new RGB data.
	public void alterTile(int x, int y, Tile newTile) {
		this.tiles[x + y * width] = newTile.getId();
		image.setRGB(x, y, newTile.getLevelImageColor());
	}

	public void generateLevel() {
		for (int y = 0; y < height; y += 1) {
			for (int x = 0; x < width; x += 1) {
				// The index [x + y * width] is used to get the coordinate of the tile.
				if (x * y % 10 < 7) {
					tiles[x + y * width] = Tile.GRASS.getId();
				} else {
					tiles[x + y * width] = Tile.STONE.getId();
				}
			}
		}
	}

	public void tick() {
		for (int i = 0; i < entities.size(); i += 1) {
			entities.get(i).tick();
		}

		for (Tile t : Tile.tiles) {
			if (t == null) {
				break;
			}
			t.tick();
		}
	}

	// The xOffset and the yOffset are inputed from the Game class, and they're just
	// the position of the player centered on the screen.
	public void renderTiles(Screen screen, int xOffset, int yOffset) {
		// -----This is rendering the camera-------
		if (xOffset < 0) {
			xOffset = 0;
		}
		// The "width << 3" is the width of the total board.
		if (xOffset > ((width << 3) - screen.width)) {
			xOffset = ((width << 3) - screen.width);
		}

		if (yOffset < 0) {
			yOffset = 0;
		}
		// The "height << 3" is the height of the total board.
		if (yOffset > ((height << 3) - screen.height)) {
			yOffset = ((height << 3) - screen.height);
		}

		screen.setOffset(xOffset, yOffset);
		// -----End of rendering the camera--------

		for (int y = 0; y < height; y += 1) {
			for (int x = 0; x < width; x += 1) {
				// This is calling the tile (at x, y)'s render function and giving it the screen
				// that this render function has gotten, as well as the tile's x and y
				getTile(x, y).render(screen, this, x << 3, y << 3, false, false);
			}
		}
	}

	public void renderEntities(Screen screen) {
		for (int i = 0; i < entities.size(); i += 1) {
			entities.get(i).render(screen);
		}
	}

	public Tile getTile(int x, int y) {
		// If the tile is outside the bounds of the screen/level then it is
		// automatically a void tile.
		if (x < 0 || x >= width || y < 0 || y >= height) {
			return Tile.VOID;
		}
		return Tile.tiles[tiles[x + y * width]];
	}

	public void addEntity(Entity entity) {
		this.entities.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		this.entities.remove(entity);
	}
}
