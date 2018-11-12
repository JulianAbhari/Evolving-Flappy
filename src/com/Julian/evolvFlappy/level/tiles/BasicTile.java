package com.Julian.evolvFlappy.level.tiles;

import com.Julian.evolvFlappy.gfx.Screen;
import com.Julian.evolvFlappy.level.Level;

public class BasicTile extends Tile {

	protected int tileId;
	protected int tileColor;

	public BasicTile(int id, int x, int y, int tileColor, int levelImageColor) {
		super(id, false, false, false, false, levelImageColor);
		this.tileId = x + y * 32;
		this.tileColor = tileColor;
	}

	public void tick() {
		
	}
	
	public void render(Screen screen, Level level, int x, int y) {
		screen.render(x, y, tileId, tileColor, false, false, 1);
	}
	
	public void render(Screen screen, Level level, int x, int y, boolean flipX, boolean flipY) {
		screen.render(x, y, tileId, tileColor, flipX, flipY, 1);
	}

}
