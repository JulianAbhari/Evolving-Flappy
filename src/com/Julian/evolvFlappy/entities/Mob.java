package com.Julian.evolvFlappy.entities;

import com.Julian.evolvFlappy.level.Level;
import com.Julian.evolvFlappy.level.tiles.Tile;

public abstract class Mob extends Entity {

	protected String name;
	protected int speed;
	protected int numSteps = 0;
	protected boolean isMoving;
	protected int movingDir = 1;
	protected int scale = 1;

	public Mob(Level level, String name, int x, int y, int speed) {
		super(level);
		this.name = name;
		this.x = x;
		this.y = y;
		this.speed = speed;
	}

	// xDir = How much the Mob is moving in a certain direction
	// yDir = How much the Mob is moving in a certain direction
	/*
	 * Imagine a standard cartesion coordinate plane, place positive (1) xDir in the
	 * whole positive side of xCoord and place negative (1) xDir in the whole
	 * negative side, and do the same for the yDir. Then you'll understand what
	 * these variables are (The range is an integer between -1 and 1). They're
	 * basically the equivalent of N,E,S,W
	 */
	public void move(int xDir, int yDir) {
		// This checks if they're not 0
		// They should only move in 1 direction at a time because if they move
		// diagonally they'll move 2 blocks at a time
		if (xDir != 0 && yDir != 0) {
			move(xDir, 0);
			move(0, yDir);
			// The reason why I'm removing one from the 'numSteps' is because it's counting
			// these two move functions diagonally as 2 steps which is not truer
			numSteps -= 1;
			return;
		}
		numSteps += 1;
		if (!hasCollided(xDir, yDir)) {
			// When the player is going up the movingDir is set to 0
			if (yDir < 0)
				movingDir = 0;
			// When the player is going down the movingDir is set to 1
			if (yDir > 0)
				movingDir = 1;
			// When the player is moving to the left the movingDir is set to 2
			if (xDir < 0)
				movingDir = 2;
			// When the player is moving to the right the movingDir is set to 3
			if (xDir > 0)
				movingDir = 3;
			// This is moving the players position by whatever the direction is (a value
			// between one and negative one) multiplied by the speed.
			x += xDir * speed;
			y += yDir * speed;
		}
	}

	public abstract boolean hasCollided(int xDir, int yDir);

	// This is going to get the last tile that the player was standing on and the
	// current tile and compare them, if the tile hasn't changed then nothing will
	// happen, but if it has changed and the tile is solid then it will return true.
	protected boolean isSolidTile(int xAmount, int yAmount, int x, int y) {
		if (level == null) {
			return false;
		}
		Tile lastTile = level.getTile((this.x + x) >> 3, (this.y + y) >> 3);
		Tile newTile = level.getTile((this.x + x + xAmount) >> 3, (this.y + y + yAmount) >> 3);
		if (!lastTile.equals(newTile) && newTile.isSolid()) {
			return true;
		}
		return false;
	}

	public String getName() {
		return name;
	}

}
