package com.Julian.evolvFlappy.level.tiles;

public class AnimatedTile extends BasicTile {

	// This is a multidimensional array which holds the coordinates of the tile's
	// different id's. The tick method will change the id of the current tile passed
	// to it's super class's (BasicTile) constructor so that when the super class
	// renders the tile, the id of the tile will change so it will appear to
	// animate.
	private int[][] animationTileCoords;
	private int currentAnimationIndex;
	private long lastIterationTime;
	private int animationSwitchDelay;

	public AnimatedTile(int id, int[][] animationTileCoords, int tileColor, int levelImageColor,
			int animationSwitchDelay) {
		super(id, animationTileCoords[0][0], animationTileCoords[0][1], tileColor, levelImageColor);
		this.animationTileCoords = animationTileCoords;
		this.currentAnimationIndex = 0;
		this.lastIterationTime = System.currentTimeMillis();
		this.animationSwitchDelay = animationSwitchDelay;

	}

	public void tick() {
		// This checks the current time to see if it's overlaped the delay
		if ((System.currentTimeMillis() - lastIterationTime) >= animationSwitchDelay) {
			lastIterationTime = System.currentTimeMillis();
			// This changes the current id of the tile so it will appear to animate.
			currentAnimationIndex = (currentAnimationIndex += 1) % animationTileCoords.length;
			this.tileId = (animationTileCoords[currentAnimationIndex][0]
					+ (animationTileCoords[currentAnimationIndex][1] * 32));
		}
	}
}
