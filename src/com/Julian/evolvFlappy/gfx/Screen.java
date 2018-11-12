package com.Julian.evolvFlappy.gfx;

public class Screen {

	public int[] pixels;

	// This is to offset the screeen because we need to render the screen as well as
	// a camera point
	public int xOffset = 0;
	public int yOffset = 0;

	public int width;
	public int height;

	// The SpriteSheet is local to the screen so we can have multiple screens with
	// multiple different spritesheets per screen
	public SpriteSheet sheet;

	public Screen(int width, int height, SpriteSheet sheet) {
		this.width = width;
		this.height = height;
		this.sheet = sheet;

		pixels = new int[width * height];
	}

	public void render(int xPos, int yPos, int tileIndex, int color, int scale) {
		render(xPos, yPos, tileIndex, color, false, false, scale);
	}

	public void render(int xPos, int yPos, int tileIndex, int color, boolean mirrorX, boolean mirrorY, int scale) {
		xPos -= xOffset;
		yPos -= yOffset;

		// This is used so that you can scale by having 1 be the default and it won't
		// interfere with the rest because it'll be 0. And so on. It just gives you 1
		// less so that the actual scale variable is easier to read.
		int scaleMap = scale - 1;
		int xTile = tileIndex % 32;
		int yTile = tileIndex / 32;

		int tileOffset = (xTile << 3) + (yTile << 3) * sheet.width;

		for (int y = 0; y < 8; y += 1) {
			int ySheet = y;
			if (mirrorY)
				ySheet = 7 - y;

			int yPixel = (y + yPos) + (y * scaleMap) - ((scaleMap << 3) / 2);

			for (int x = 0; x < 8; x += 1) {
				int xSheet = x;
				if (mirrorX)
					xSheet = 7 - x;
				int xPixel = x + xPos + (x * scaleMap) - ((scaleMap << 3) / 2);
				int col = (color >> (sheet.pixels[xSheet + ySheet * sheet.width + tileOffset] * 8)) & 255;
				if (col < 255) {
					for (int yScale = 0; yScale < scale; yScale += 1) {
						if (yPixel + yScale < 0 || yPixel + yScale >= height) {
							continue;
						}
						for (int xScale = 0; xScale < scale; xScale += 1) {
							if (xPixel + xScale < 0 || xPixel + xScale >= width) {
								continue;
							}
							pixels[(xPixel + xScale) + (yPixel + yScale) * width] = col;
						}
					}
				}
			}
		}
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}
