package com.Julian.evolvFlappy.gfx;

public class Font {
	private static String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ      0123456789.,:;'\"!?$%()-=+/\\_    "; 
	
	public static void render(String msg, Screen screen, int x, int y, int color, int scale) {
		msg = msg.toUpperCase();
		
		for (int i = 0; i < msg.length(); i += 1) {
			int charIndex = chars.indexOf(msg.charAt(i));
			if (charIndex >= 0) {
				screen.render(x + (i * 8), y, charIndex + 30 * 32, color, scale);
			}
		}
	}
}
