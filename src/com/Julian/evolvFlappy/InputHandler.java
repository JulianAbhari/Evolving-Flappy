package com.Julian.evolvFlappy;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

	public InputHandler(Game game) {
		game.addKeyListener(this);
	}
	
	public class Key {
		public boolean pressed = false;
		
		public boolean isPressed() {
			return pressed;
		}
		
		public void toggle(boolean isPressed) {
			pressed = isPressed;
		}
	}
	
	//-----Movement Keys-----
	public Key up = new Key();
	
	@Override
	public void keyPressed(KeyEvent e) {
		toggleKey(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		toggleKey(e.getKeyCode(), false);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void toggleKey(int keyCode, boolean isPressed) {
		//-----Movement Keys-----
		if (keyCode == KeyEvent.VK_SPACE) {
			up.toggle(isPressed); 
		}
	}
	
}
