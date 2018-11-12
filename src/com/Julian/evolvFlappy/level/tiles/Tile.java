package com.Julian.evolvFlappy.level.tiles;

import com.Julian.evolvFlappy.gfx.Colors;
import com.Julian.evolvFlappy.gfx.Screen;
import com.Julian.evolvFlappy.level.Level;

public abstract class Tile {

	// 256 is the maximum amount of tiles that can be in the board
	public static final Tile[] tiles = new Tile[256];

	public static final Tile VOID = new BasicSolidTile(0, 0, 0, Colors.get(000, -1, -1, -1), 0xFF000000);
	public static final Tile STONE = new BasicSolidTile(1, 1, 0, Colors.get(-1, 222, -1, -1), 0xFF555555);
	public static final Tile GRASS = new BasicTile(2, 2, 0, Colors.get(-1, 414, 515, -1), 0xFF00FF00);
	public static final Tile WATER = new AnimatedTile(3, new int[][] { { 0, 5 }, { 1, 5 }, { 2, 5 }, { 1, 5 } },
			Colors.get(-1, 004, 115, -1), 0xFF0000FF, 1000);
	public static final Tile AIR = new BasicTile(4, 1, 0, Colors.get(-1, 235, -1, -1), 0xFF77AAFF);


	protected byte id;
	// Collision detection
	protected boolean solid;
	// Light
	protected boolean emitter;
	// Interactive
	protected boolean interactive;
	// Trigger Tile
	protected boolean trigger;
	// This is the color ID for image to tile.
	private int levelImageColor;
	// This is the xCoordinate of the tile within the level
	protected int xLevel;
	// This is the yCoordinate of the tile within the level
	protected int yLevel;

	public Tile(int id, boolean isSolid, boolean isEmitter, boolean isInteractive, boolean isTrigger, int levelImageColor) {
		this.id = (byte) id;
		if (tiles[id] != null) {
			throw new RuntimeException("Duplicate tile id on " + id);
		}
		this.solid = isSolid;
		this.emitter = isEmitter;
		this.interactive = isInteractive;
		this.trigger = isTrigger;
		this.levelImageColor = levelImageColor;
		tiles[id] = this;
	}

	public byte getId() {
		return id;
	}

	public boolean isSolid() {
		return solid;
	}

	public boolean isEmitter() {
		return emitter;
	}

	public boolean isInteractive() {
		return interactive;
	}
	
	public boolean isTrigger() {
		return trigger;
	}

	public int getLevelImageColor() {
		return levelImageColor;
	}
	
	public int getXLevel() {
		return xLevel;
	}
	
	public void setXLevel(int xLevel) {
		this.xLevel = xLevel;
	}
	
	public int getYLevel() {
		return yLevel;
	}

	public void setYLevel(int yLevel) {
		this.yLevel = yLevel;
	}

	public abstract void tick();

	public abstract void render(Screen screen, Level level, int x, int y, boolean flipX, boolean flipY);

}
