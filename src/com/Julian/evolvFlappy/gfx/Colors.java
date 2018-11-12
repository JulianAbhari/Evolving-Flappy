package com.Julian.evolvFlappy.gfx;

public class Colors {

	public static int get(int color1, int color2, int color3, int color4) { // color1 is darker, then it gets lighter
																			// from there

		return (get(color4) << 24) + (get(color3) << 16) + (get(color2) << 8) + (get(color1));
	}

	private static int get(int color) {
		if (0 > color) {
			return 255;
		}
		// This is getting the red value of the color int.
		// ex: Say you give it the color "340". The 100's place is the R value,
		// the 10's place is the B value, and the 1's place is the G value.
		int r = color / 100 % 10;
		int b = color / 10 % 10;
		int g = color % 10;
		return r * 36 + b * 6 + g;
	}

}
