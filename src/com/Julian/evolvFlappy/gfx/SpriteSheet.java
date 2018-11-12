package com.Julian.evolvFlappy.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	// This is the path to the SpriteSheet image
	public String path;
	public int width;
	public int height;

	// This is the pixel data of the SpriteSheet
	public int[] pixels;

	public SpriteSheet(String path) {
		BufferedImage image = null;
		try {
			// This is setting the image to the SpriteSheet.png reference from the path
			image = ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (image == null) {
			return;
		}

		this.path = path;
		this.width = image.getWidth();
		this.height = image.getHeight();

		// This is setting the pixels array to the color data of all the pixels from the
		// image
		pixels = image.getRGB(0, 0, width, height, null, 0, width);
		
		for (int i = 0; i < pixels.length; i += 1) {
			// This is removing the alpha channel fromt the pixel data.
			// This is also setting it into only 4 different colors by dividing by the total amount of color shades by 4 
			pixels[i] = (pixels[i] & 0xff) / (256/4);
		}
	}
}
