import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import processing.core.PApplet;
import processing.core.PConstants;

public class pMusic {

	private static ArrayList<String> list = new ArrayList<String>();
	private static ArrayList<String> no = new ArrayList<String>();
	private static String playMusic;
	private String repImg, reapImg, reap2Img, rewImg, forImg, skipF, skipB, plImg, paImg, shuffImg, unShuff;
	private static int frame;
	private double w, h, r, r2, w2, h2, sw, sh, sw2, sh2;
	public double scrollx, scrolly;
	// ButtonHandler bH = new ButtonHandler();
	private static Music m = new Music();
	private static boolean fullStart = false, play = false, repeat = false, peated = false, pToggled = false, 
			looped = false, rew = false, for2 = false, skippedF = false, skippedB = false, shuffled = true, shuff = true;
	public boolean hoverScroll = true;
	ImageIcon iL = new ImageIcon("src/play.png"), iU = new ImageIcon("src/pause.png");

	public pMusic() {
		w = 50;
		h = 50;
		w2 = 40;
		h2 = 40;
		
		sw = 25;
		sh = 25;
		sw2 = 20;
		sh2 = 20;
		r = 1.5 * w;
		r2 = 1.25 * w2;
		list.add("src/Owl City - New York City (Lyrics).wav");
		//list.add("src/7 years old.wav");
		list.add("src/Do Or Die.wav");
		//list.add("src/Maroon 5 - Memories (Lyrics).wav");
		list.add("src/\"Fires of a Revolution\" Incredible Fast Piano Music (EPIC).wav");
		//list.add("src/BadBoyHalo, CG5, Hyper Potions - MUFFIN (feat Skeppy, CaptainPuffy) (Official Music Video).wav");
		list.add("src/CG5 - Vibrant Eyes [Dream SMP original song].wav");
		list.add("src/Din Shagna Da (Phillauri).wav");
		list.add("src/Dream & Alec Benjamin - Change My Clothes (Official Lyric Video).wav");
		list.add("src/world’s smallest violin - skeppy animatic.wav");
		//list.add("src/How To Train Your Dragon Theme | EPIC ORCHESTRAL MEDLEY V2 (Test Drive, This is Berk, & More).wav");
		//list.add("src/Nuvole Bianche.wav");
		list.add("src/Owl City - Fireflies (Official Video).wav");
		
		if (shuff) playMusic = list.get((int) (Math.random() * list.size()));
		else playMusic = list.get(0);
		
		plImg = "src/play.png";
		paImg = "src/pause.png";
		
		repImg = "src/replay.png"; //Replay Song
		
		reapImg = "src/repeat.png"; //Loop On
		reap2Img = "src/reap2.png"; //Loop Off
		
		rewImg = "src/rewind.png"; //Previous Song
		forImg = "src/for1.png"; //Next Song
		
		skipF = "src/skipF.png"; //Skip back
		skipB = "src/skipB.png"; //Skip forward
		
		shuffImg = "src/shuffle.png"; //Shuffle Songs
		unShuff = "src/shuffle2.png"; //Unshuffle Songs
	}
	
	public double getR() {
		return r;
	}
	
	public double getH() {
		return h;
	}

	public void draw(PApplet p) {
		p.background(0);
		
		
		updateProgress(p);
		scrolly = p.height*0.5f - 3*(float)h;
		
		
		
		p.fill(255);
		p.textAlign(PConstants.CENTER, PConstants.CENTER);
		p.text(playMusic.substring(4, playMusic.length()-4), p.width/2, p.height/5);
		p.fill(50);
		//Play Cp.fill(50);ircle
		if (!pToggled) p.ellipse((float) (p.width / 2), (float) (0.8 * p.height), (float) r, (float) r);
		else p.ellipse((float) (p.width / 2), (float) (0.8 * p.height), (float) (0.9 * r), (float) (0.9 * r));

		//Replay Song Circle
		if (!peated) p.ellipse((float) (p.width / 2), (float) (0.8 * p.height - (1.75 * h)), (float) r, (float) r);
		else p.ellipse((float) (p.width / 2), (float) (0.8 * p.height - (1.75 * h)), (float) (0.9 * r), (float) (0.9 * r));

		//Loop Songs Circle
		if (!looped) p.ellipse((float) (p.width / 2), (float) (0.8 * p.height - (3.5 * h)), (float) r, (float) r);
		else p.ellipse((float) (p.width / 2), (float) (0.8 * p.height - (3.5 * h)), (float) (0.9 * r), (float) (0.9 * r));
		
		//Shuffle Songs Circle
		if (!shuffled) p.ellipse((float) (p.width - sw*2), (float) (sh), (float) r2, (float) r2);
		else p.ellipse((float) (p.width - sw*22), (float) (sh), (float) (0.9 * r2), (float) (0.9 * r2));
		
		//Skip Backward Circle
		if (!skippedB) p.ellipse((float) (0.25 * p.width), (float) (0.8 * p.height), (float) r, (float) r);
		else p.ellipse((float) (0.25 * p.width), (float) (0.8 * p.height), (float) (0.9 * r), (float) (0.9 * r));
		//Skip Forward Circle
		if (!skippedF) p.ellipse((float) (0.75 * p.width), (float) (0.8 * p.height), (float) r, (float) r);
		else p.ellipse((float) (0.75 * p.width), (float) (0.8 * p.height), (float) (0.9 * r), (float) (0.9 * r));
		
		

		//Loop Songs
		if (!looped && repeat) p.image(p.loadImage(reapImg), (float) (p.width / 2 - w / 2), (float) (0.8 * p.height - (3.5 * h) - h / 2), (float)w, (float)h);
		else if (looped && repeat) p.image(p.loadImage(reapImg), (float) (p.width / 2 - w2 / 2), (float) (0.8 * p.height - (3.5 * h) - h2 / 2), (float)w2, (float)h2);
		else if (!looped && !repeat) p.image(p.loadImage(reap2Img), (float) (p.width / 2 - w / 2), (float) (0.8 * p.height - (3.5 * h) - h / 2), (float)w, (float)h);
		else  p.image(p.loadImage(reap2Img), (float) (p.width / 2 - w2 / 2), (float) (0.8 * p.height - (3.5 * h) - h2 / 2), (float)w2, (float)h2);
		
		//Shuffle Songs
		p.imageMode(PConstants.CENTER);
		if (!shuffled && shuff) p.image(p.loadImage(shuffImg), (float) (p.width - sw*2), (float) (sh), (float)sw, (float)sh);
		else if (shuffled && shuff) p.image(p.loadImage(shuffImg), (float) (p.width - sw*2), (float) (sh), (float)sw2, (float)sh2);
		else if (!shuffled && !shuff) p.image(p.loadImage(unShuff), (float) (p.width - sw*2), (float) (sh), (float)sw, (float)sh);
		else  p.image(p.loadImage(unShuff), (float) (p.width - sw*2), (float) (sh), (float)sw2, (float)sh2);
		p.imageMode(PConstants.CORNER);
		//Play/Pause
		if (!play && !pToggled) p.image(p.loadImage(plImg), (float) (p.width / 2 - w / 2), (float) (0.8 * p.height - h / 2), (float)w, (float)h);
		else if (!play && pToggled) p.image(p.loadImage(plImg), (float) (p.width / 2 - w2 / 2), (float) (0.8 * p.height - h2 / 2), (float)w2, (float)h2);
		else if (play && !pToggled) p.image(p.loadImage(paImg), (float) (p.width / 2 - w / 2), (float) (0.8 * p.height - h / 2), (float)w,(float)h);
		else p.image(p.loadImage(paImg), (float) (p.width / 2 - w2 / 2), (float) (0.8 * p.height - h2 / 2), (float)w2, (float)h2);
		
		//Repeat Song
		if (!peated) p.image(p.loadImage(repImg), (float) (p.width / 2 - w / 2), (float) (0.8 * p.height - (1.75 * h) - h / 2), (float)w, (float)h);
		else p.image(p.loadImage(repImg), (float) (p.width / 2 - w2 / 2), (float) (0.8 * p.height - (1.75 * h) - h2 / 2), (float)w2, (float)h2);
	
		//Skip Backward
		if (!skippedB) p.image(p.loadImage(skipB), (float) (p.width*0.25 - w / 2), (float) (0.8 * p.height - h / 2), (float)w, (float)h);
		else p.image(p.loadImage(skipB), (float) (p.width*0.25 - w2 / 2), (float) (0.8 * p.height - h2 / 2), (float)w2, (float)h2);
		//Skip Forward
		if (!skippedF) p.image(p.loadImage(skipF), (float) (p.width*0.75 - w / 2), (float) (0.8 * p.height - h / 2), (float)w, (float)h);
		else p.image(p.loadImage(skipF), (float) (p.width*0.75 - w2 / 2), (float) (0.8 * p.height - h2 / 2), (float)w2, (float)h2);
		
		
	}
	
	public void updateProgress(PApplet p) {
		p.fill(255);
		p.strokeCap(PConstants.SQUARE);
		p.strokeWeight(4);
		if(hoverScroll) p.stroke(100);
		else p.stroke(255);
		p.line(0, (float) scrolly, p.width, (float) scrolly);
		
		if(Music.clip!=null && Music.clip.getFrameLength() !=0) {
			p.stroke(255, 0, 0);
			p.fill(255, 0, 0);
			scrollx = p.width * m.progress();
			p.line(0, (float) scrolly, (float) scrollx, (float) scrolly);
			if (hoverScroll) p.ellipse((float) scrollx, (float) scrolly, 10, 10);
		}
		p.noStroke();
	}
	
	public void togglePDisplay() {
		pToggled = true;
	}
	
	public void toggleP() {
		if (play) {
			m.stop();
			play = false;
		} else {
			m.setFile(playMusic);
			m.play();
			if (!fullStart)
				fullStart = true;
			play = true;
		}
	}
	
	public void loopPlaylistDisplay() {
		looped = true;
	}
	
	public void loopPlaylist() {
		if (repeat) repeat = false;
		else repeat = true;
	}
	
	public void shuffleDisplay() {
		shuffled = true;
	}
	
	public void shuffle() {
		if (shuff) shuff = false;
		else shuff = true;
	}
	
	public void restartSongDisplay() {
		peated = true;
	}
	
	public void restartSong() {
		if (fullStart) m.restart();
	}
	
	public void skipForwardDisplay() {
		skippedF = true;
	}
	
	public void skipBackwardDisplay() {
		skippedB = true;
	}
	
	public void skipForward(PApplet p) {
		if(play)m.stop();
		frame +=100000;
		if(play) {
			m.setFile(playMusic);
			m.play();
		}
		updateProgress(p);
	}
	
	public void skipBackward(PApplet p) {
		if(play)m.stop();
		frame -=100000;
		if(play) {
			m.setFile(playMusic);
			m.play();
		}
		updateProgress(p);
	}
	
	public void release() {
		pToggled = false;
		
		looped = false;
		
		shuffled = false;
		
		peated = false;
		
		skippedF = false;
		
		skippedB = false;
		
		if(play && m.started == false) {
			m.setFile(playMusic);
			m.play();
		}
	}
	
	
	
	public void nextSong() {
		pMusic.Music.clip.setFramePosition(pMusic.Music.clip.getFrameLength());	
	}
	
	public void setVol(float v) {
		m.setVol(v);
	}
	
	public void scrolling(double num) {
		m.scrolling(num);
	}

	public static class Music {
		static Clip clip;
		static float vol = -10;
		boolean started = false;

		public void setFile(String soundFileName) {
			try {
				File file = new File(soundFileName);
				AudioInputStream sound = AudioSystem.getAudioInputStream(file);
				clip = AudioSystem.getClip();
				clip.open(sound);
			} catch (Exception e) {
				System.out.println(e);
			}

			clip.addLineListener(new LineListener() {

				@Override
				public void update(LineEvent e) {
					if (e.getType() == LineEvent.Type.STOP) {
						if(clip.getFramePosition() >= clip.getFrameLength()) {
							frame = 0;
							next();
						}
					}

				}

			});
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(vol);
		}

		public void setVol(float v) {
			if (vol + v <= 6.0206 && vol + v >= -80) {
				vol += v;
				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(vol);
			}
			System.out.println("Volume is at " + (int)((vol+80)/86.0206 * 100) + "%");
		}
		public void restart() {
			frame = 0;
			play();
		}

		public void play() {
			clip.setFramePosition(frame);
			clip.start();
			started = true;
		}
		
		public float progress() {
			if(clip.getFramePosition() <= 0 && clip.getFrameLength() !=0)  return (float)frame/clip.getFrameLength();
			else if(clip.getFrameLength() !=0) return (float)clip.getFramePosition()/clip.getFrameLength();
			else return 1;
		}
		
		public void scrolling(double num) {
			frame = (int) (clip.getFrameLength() * num);
			clip.stop();
			clip.close();
			started = false;
		}
		
//		public void loop() {
//			clip.loop(Clip.LOOP_CONTINUOUSLY);
//		}

		public void stop() {
			frame = clip.getFramePosition();
			clip.stop();
			clip.close();
		}

	} //end of Music

	private static void next() {
		no.add(list.remove(findPlace()));
		if (!repeat && list.size() == 0) m.stop();
		else if (repeat && list.size() == 0) putBack();
		if (list.size() != 0){
			if (shuff) {
				int n = (int) (Math.random() * list.size());
				playMusic = list.get(n);
			}
			else playMusic = list.get(0);
			m.setFile(playMusic);
			m.play();
		}
		System.out.println("Currently playing " + playMusic);
	}
	
	private static int findPlace() {
		for (int i = 0; i < list.size(); i++) {
			if (playMusic.equals(list.get(i))) return i;
		}
		return -1;
	}
	
	private static void putBack() {
		for (int i = 0; i < no.size(); i++) {
			list.add(no.remove(i));
			i--;
		}
	}
	
	
	public boolean checkP(PApplet p, int x, int y) {
		if (x <= p.width / 2 + r / 2 && x >= p.width / 2 - r / 2 && y <= 0.8 * p.height + r / 2
				&& y >= 0.8 * p.height - r / 2) return true;
		return false;
	}
	
	public boolean checkLoop(PApplet p, int x, int y) {
		if (x <= p.width / 2 + r / 2 && x >= p.width / 2 - r / 2 && y <= (0.8 * p.height - (3.5 * h)) + r / 2
				&& y >= (0.8 * p.height - (3.5 * h)) - r / 2) return true;
		return false;
	}
	
	public boolean checkShuffle(PApplet p, int x, int y) {
		if (x >= p.width - sw*2 && x <= p.width - sw && y <= sh*2 && y >= sh) return true;
		return false;
	}
	
	public boolean checkRestart(PApplet p, int x, int y) {
		if (x <= p.width / 2 + r / 2 && x >= p.width / 2 - r / 2 && y <= (0.8 * p.height - (1.75 * h)) + r / 2
				&& y >= (0.8 * p.height - (1.75 * h)) - r / 2) return true;
		return false;
	}

	public boolean checkSF(PApplet p, int x, int y) {
		if (x <= p.width*0.75 + r / 2 && x >= p.width*0.75 - r / 2 && y <= 0.8 * p.height + r / 2 && 
				y >= 0.8 * p.height - r / 2) return true;
		return false;
	}

	public boolean checkSB(PApplet p, int x, int y) {
		if (x <= p.width*0.25 + r / 2 && x >= p.width*0.25 - r / 2 && y <= 0.8 * p.height + r / 2 && 
				y >= 0.8 * p.height - r / 2) return true;
		return false;
	}

}