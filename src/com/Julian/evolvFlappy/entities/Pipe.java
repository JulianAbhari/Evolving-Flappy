package com.Julian.evolvFlappy.entities;

import com.Julian.evolvFlappy.gfx.Colors;
import com.Julian.evolvFlappy.gfx.Screen;
import com.Julian.evolvFlappy.level.Level;

public class Pipe extends Mob {

	public Boolean didFinish = false;
	
	private int color = Colors.get(-1, 131, 141, 151);
	public int scale = 2;
	private int gap;

	public Pipe(Level level, int x, int y, int speed, int gap) {
		super(level, "Pipe", x, y, speed);
		this.gap = gap;
	}

	@Override
	public boolean hasCollided(int xDir, int yDir) {
		return false;
	}

	@Override
	public void tick() {
		int xDir = 0;
		if (!(this.x < -16)) {
			xDir -= 1;

			if (xDir != 0) {
				move(xDir, 0);
			}
		} else {
			didFinish = true;
		}
	}

	@Override
	public void render(Screen screen) {
		for (int impliedY = 0; impliedY < y; impliedY += (8 * scale)) {
			screen.render(x, impliedY, 3 + 0 * 32, color, false, false, scale);
		}
		screen.render(x, y, 4 + 0 * 32, color, false, false, scale);
		
		
		for (int impliedY = (y + (8*scale)) + this.gap; impliedY < level.height * 8 + (8* scale); impliedY += (8 * scale)) {
			screen.render(x, impliedY, 3 + 0 * 32, color, false, false, scale);
		}
		
		screen.render(x, (y + (8*scale)) + this.gap, 4 + 0 * 32, color, false, false, scale);
	}
	
	public int getBottomPipeY() {
		return (y + (8*scale) + this.gap);
	}
	
	public int getTopPipeY() {
		return y;
	}

}
