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
		if (mouseY <= m.scrolly + height/80 && mouseY >= m.scrolly - height/80) m.hoverScroll = true;
		else m.hoverScroll = false;
		m.draw(this);

	}
	
	// In general, it's better to use mouse interactions for interacting with the grid (like placing a piece).
	// Key interactions can be used if it really makes sense, such as for uncommon tasks (like resetting the game).
	// If you'd like to do basic gameplay interaction via keyboard, consider asking Mr Shelby about it.
	public void keyPressed() {
		
		if (key == ' ') {
			m.togglePDisplay();
			m.toggleP();
		}
		else if (keyCode == KeyEvent.VK_DOWN) {
			m.setVol(-5);
		} 
		else if (keyCode == KeyEvent.VK_UP) {
			m.setVol(5);
		}
		else if (keyCode == KeyEvent.VK_RIGHT) {
			m.skipForwardDisplay();
			m.skipForward(this);
		}
		else if (keyCode == KeyEvent.VK_LEFT) {
			m.skipBackwardDisplay();
			m.skipBackward(this);
		}
		else if (keyCode == KeyEvent.VK_N) {
			m.nextSong();
		} 
	}
	
	public void keyReleased() {
		m.release();
	}
	
	
	public void mousePressed() {
		
		// Play/Pause
		if (m.checkP(this, mouseX, mouseY)) m.togglePDisplay();
		
		// Loop Playlist
		if (m.checkLoop(this, mouseX, mouseY)) m.loopPlaylistDisplay();

		// Shuffle Playlist
		if (m.checkShuffle(this, mouseX, mouseY)) m.shuffleDisplay();
		
		// Replay Song
		if (m.checkRestart(this, mouseX, mouseY)) m.restartSongDisplay();
		
		// Skip Forward
		if (m.checkSF(this, mouseX, mouseY)) m.skipForwardDisplay();
		// Skip Backward
		if (m.checkSB(this, mouseX, mouseY)) m.skipBackwardDisplay();
	
		if (m.hoverScroll == true) m.scrolling((double)mouseX/width);
	}
	public void mouseDragged() {
		if (m.hoverScroll == true) m.scrolling((double)mouseX/width);
	}
	public void mouseReleased() {
		
		// Play/Pause
		if (m.checkP(this, mouseX, mouseY)) m.toggleP();
		
		// Loop Playlist
		else if (m.checkLoop(this, mouseX, mouseY)) m.loopPlaylist();
		
		// Shuffle Playlist
		else if (m.checkShuffle(this, mouseX, mouseY)) m.shuffle();
		
		// Replay Song
		else if (m.checkRestart(this, mouseX, mouseY)) m.restartSong();
		
		// Skip Forward
		else if (m.checkSF(this, mouseX, mouseY)) m.skipForward(this);
		// Skip Backward
		else if (m.checkSB(this, mouseX, mouseY)) m.skipBackward(this);
	
		m.release();
	}

	
}










