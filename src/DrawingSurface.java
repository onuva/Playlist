import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import processing.core.PApplet;

public class DrawingSurface extends PApplet {

	private pMusic m;

	
	
	public DrawingSurface() {
		// Consider using the file reading code for testing purposes. Sometimes, it's nice to run the program and have some grid contents already present that you can
		// mess with to see if things are working. 
		m = new pMusic();    
		
	}
	
	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {
		

	}
	
	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() { 
		background(255);   // Clear the screen with a white background
		fill(0);
		textAlign(LEFT);
		textSize(12);
		
		//text("Give basic instructions: ", height+20, 30);
		
		m.draw(this);

	}
	
	// In general, it's better to use mouse interactions for interacting with the grid (like placing a piece).
	// Key interactions can be used if it really makes sense, such as for uncommon tasks (like resetting the game).
	// If you'd like to do basic gameplay interaction via keyboard, consider asking Mr Shelby about it.
	public void keyPressed() {
		
		if (keyCode == KeyEvent.VK_DOWN) {
			//board.moveDown();
		} 
		else if (keyCode == KeyEvent.VK_UP) {
			//board.moveUp();
		}
		else if (keyCode == KeyEvent.VK_RIGHT) {
			//board.moveRight();
		}
		else if (keyCode == KeyEvent.VK_LEFT) {
			//board.moveLeft();
		}
	}
	
	
	public void mousePressed() {
		m.blink(mouseX,  mouseY,  this, true);
	}
	
	public void mouseReleased() {
		m.blink(mouseX,  mouseY,  this, false);
		m.change(mouseX,  mouseY,  this);
	}

	
}










