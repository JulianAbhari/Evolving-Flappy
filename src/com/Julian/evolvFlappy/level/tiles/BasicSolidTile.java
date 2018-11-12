package com.Julian.evolvFlappy.level.tiles;

public class BasicSolidTile extends BasicTile {

	public BasicSolidTile(int id, int x, int y, int tileColor, int levelImageColor) {
		super(id, x, y, tileColor, levelImageColor);
		this.solid = true;
	}

}
