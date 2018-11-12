package com.Julian.evolvFlappy.entities;

import com.Julian.NeuralNet.Network;
import com.Julian.evolvFlappy.Game;
import com.Julian.evolvFlappy.JMath;
import com.Julian.evolvFlappy.gfx.Colors;
import com.Julian.evolvFlappy.gfx.Screen;
import com.Julian.evolvFlappy.level.Level;

public class BirdAI extends Mob {

	private int color = Colors.get(-1, 000, 214, 555);
	private int scale = 1;
	public Network brain;

	public double score;
	public double fitness;

	public boolean hasCrashed = false;

	public BirdAI(Level level, int x, int y, Network brain) {
		super(level, "BirdAI", x, y, 1);
		this.brain = brain;
		score = 0;
		fitness = 0;
	}

	// This updates the game, it updates the internal variables and the logic of the
	// game
	public void tick() {
		int yDir = 0;

		Pipe closestPipe = Game.pipes.get(0);
		int closestDistance = Game.WIDTH;

		for (int i = 0; i < Game.pipes.size(); i += 1) {
			int distance = (Game.pipes.get(i).x + (4 * Game.pipes.get(i).scale)) - this.x;
			if (distance < closestDistance && distance > 0) {
				closestPipe = Game.pipes.get(i);
				closestDistance = distance;
			}
		}

		double[] inputs = new double[5];
		inputs[0] = JMath.map(this.y, Game.HEIGHT, 0, 0, 1);
		inputs[1] = JMath.map(closestPipe.getTopPipeY(), Game.HEIGHT, 0, 0, 1);
		inputs[2] = JMath.map(closestPipe.getBottomPipeY(), Game.HEIGHT, 0, 0, 1);
		inputs[3] = JMath.map(closestPipe.x, 0, Game.WIDTH, 0, 1);
		inputs[4] = -2;

		if (brain.calculate(inputs)[0] > brain.calculate(inputs)[1]) {
			yDir -= 2;
		} else {
			yDir += 1;
		}

		if ((this.y + 9 > closestPipe.getBottomPipeY() || this.y - 9 < closestPipe.getTopPipeY())
				&& (closestPipe.x > this.x - 9 && closestPipe.x < this.x + 9)) {
			hasCrashed = true;
		} else {
			hasCrashed = false;
		}

		score += 1;

		if (yDir != 0) {
			move(0, yDir);
			isMoving = true;
		} else {
			isMoving = false;
		}
	}

	public void render(Screen screen) {
		int xTile = 0;
		int yTile = 28;
		int walkingSpeed = 4;

		// When the player is facing towards the camera the x place for getting the Tile
		// pixels increases by 2 (because the player is 2 tiles wide)
		if (movingDir == 1) {
			xTile += 2;
		} else if (movingDir > 1) {
			xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 2;
		}

		int modifier = 8 * scale;
		int xOffset = x - modifier / 2;
		int yOffset = y - modifier / 2 - 4;

		screen.render(xOffset + modifier - (modifier), yOffset, xTile + yTile * 32, color, false, false, scale);
		screen.render(xOffset + (modifier), yOffset, (xTile + 1) + yTile * 32, color, false, false, scale);

		screen.render(xOffset + modifier - (modifier), yOffset + modifier, xTile + (yTile + 1) * 32, color, false,
				false, scale);
		screen.render(xOffset + (modifier), yOffset + modifier, (xTile + 1) + (yTile + 1) * 32, color, false, false,
				scale);
	}

	public boolean hasCollided(int xDir, int yDir) {
		int xMin = 0;
		int xMax = 7;
		int yMin = -5;
		int yMax = 5;

		for (int x = xMin; x < xMax; x += 1) {
			if (isSolidTile(xDir, yDir, x, yMin)) {
				return true;
			}
		}
		for (int x = xMin; x < xMax; x += 1) {
			if (isSolidTile(xDir, yDir, x, yMax)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y += 1) {
			if (isSolidTile(xDir, yDir, xMin, y)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y += 1) {
			if (isSolidTile(xDir, yDir, xMax, y)) {
				return true;
			}
		}
		return false;
	}
	
	public void setBrain(Network brain) {
		this.brain = brain;
	}
	
	public Network getBrain() {
		return this.brain;
	}
}
