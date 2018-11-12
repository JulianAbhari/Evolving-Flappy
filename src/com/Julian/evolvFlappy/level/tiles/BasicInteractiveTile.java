package com.Julian.evolvFlappy.level.tiles;

public abstract class BasicInteractiveTile extends BasicTile {

	public BasicInteractiveTile(int id, int x, int y, int tileColor, int levelImageColor) {
		super(id, x, y, tileColor, levelImageColor);
		this.interactive = true;
	}

	public abstract void doAction();

}
