import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import processing.core.PApplet;

public class pMusic {

	ArrayList<String> list = new ArrayList<String>();
	ArrayList<String> no = new ArrayList<String>();
	String playMusic, repImg, reapImg;
	int frame;
	double w, h, r, w2, h2;
	Color button = Color.red;
	// ButtonHandler bH = new ButtonHandler();
	Music m = new Music();
	boolean fullStart = false, play = false, repeat = false, peated = false, pToggled = false, looped = false;
	ImageIcon iL = new ImageIcon("src/play.png"), iU = new ImageIcon("src/pause.png");

	public pMusic() {
		w = 50;
		h = 50;
		w2 = 40;
		h2 = 40;
		r = 1.5 * w;
		list.add("src/s1.wav");
		list.add("src/s2.wav");
		list.add("src/s3.wav");
		playMusic = list.get((int) (Math.random() * list.size()));
		repImg = "src/replay.png";
		reapImg = "src/repeat.png";
	}

	public void draw(PApplet p) {
		if (!pToggled) p.ellipse((float) (p.width / 2), (float) (0.8 * p.height), (float) r, (float) r);
		else p.ellipse((float) (p.width / 2), (float) (0.8 * p.height), (float) (0.9 * r), (float) (0.9 * r));

		if (!peated) p.ellipse((float) (p.width / 2), (float) (0.8 * p.height - (1.75 * h)), (float) r, (float) r);
		else p.ellipse((float) (p.width / 2), (float) (0.8 * p.height - (1.75 * h)), (float) (0.9 * r),
					(float) (0.9 * r));

		if (!looped) p.ellipse((float) (p.width / 2), (float) (0.8 * p.height - (3.5 * h)), (float) r, (float) r);
		else p.ellipse((float) (p.width / 2), (float) (0.8 * p.height - (3.5 * h)), (float) (0.9 * r), (float) (0.9 * r));

		
		p.fill(255);
		if (!play) p.triangle((float) (p.width / 2 - (3 * w / 8) + 5), (float) (0.8 * p.height - h / 2),
					(float) (p.width / 2 - (3 * w / 8)) + 5, (float) (0.8 * p.height + h / 2),
					(float) (p.width / 2 + w / 2), (float) (0.8 * p.height));
		else {
			p.rect((float) (p.width / 2 - (3 * w / 8)), (float) (0.8 * p.height - h / 2), (float) (w / 4), (float) h);
			p.rect((float) (p.width / 2 + w / 8), (float) (0.8 * p.height - h / 2), (float) (w / 4), (float) h);
		}

		if (!looped && repeat) p.image(p.loadImage(reapImg), (float) (p.width / 2 - w / 2), (float) (0.8 * p.height - (3.5 * h) - h / 2));
		else if (looped && repeat) p.image(p.loadImage("src/smallR.png"), (float) (p.width / 2 - w2 / 2), (float) (0.8 * p.height - (3.5 * h) - h2 / 2));
		else if (!looped && !repeat) p.image(p.loadImage("src/reap2.png"), (float) (p.width / 2 - w / 2), (float) (0.8 * p.height - (3.5 * h) - h / 2));
		else  p.image(p.loadImage("src/smallR2.png"), (float) (p.width / 2 - w2 / 2), (float) (0.8 * p.height - (3.5 * h) - h2 / 2));
		
		if (!peated) p.image(p.loadImage(repImg), (float) (p.width / 2 - w / 2), (float) (0.8 * p.height - (1.75 * h) - h / 2));
		else p.image(p.loadImage(repImg), (float) (p.width / 2 - w2 / 2), (float) (0.8 * p.height - (1.75 * h) - h2 / 2));
	}

	public boolean change(double x, double y, PApplet p) {
		if (x <= p.width / 2 + r / 2 && x >= p.width / 2 - r / 2 && y <= 0.8 * p.height + r / 2
				&& y >= 0.8 * p.height - r / 2) {
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
			return true;
		} 
		else if (x <= p.width / 2 + r / 2 && x >= p.width / 2 - r / 2 && y <= (0.8 * p.height - (3.5 * h)) + r / 2
				&& y >= (0.8 * p.height - (3.5 * h)) - r / 2) {
			if (repeat) repeat = false;
			else repeat = true;
			return true;
		}
		else return false;
	}

	public void blink(double x, double y, PApplet p, boolean start) {
		if (start) {
			if (x <= p.width / 2 + r / 2 && x >= p.width / 2 - r / 2 && y <= (0.8 * p.height - (1.75 * h)) + r / 2
					&& y >= (0.8 * p.height - (1.75 * h)) - r / 2) {
				repImg = "src/smallReplay.png";
				peated = true;
			} 
			else if (x <= p.width / 2 + r / 2 && x >= p.width / 2 - r / 2 && y <= 0.8 * p.height + r / 2
					&& y >= 0.8 * p.height - r / 2) pToggled = true;
			else if (x <= p.width / 2 + r / 2 && x >= p.width / 2 - r / 2 && y <= (0.8 * p.height - (3.5 * h)) + r / 2
					&& y >= (0.8 * p.height - (3.5 * h)) - r / 2) 
				looped = true;
		} 
		else {
			if (x <= p.width / 2 + r / 2 && x >= p.width / 2 - r / 2 && y <= (0.8 * p.height - (1.75 * h)) + r / 2
					&& y >= (0.8 * p.height - (1.75 * h)) - r / 2) {
				repImg = "src/replay.png";
				peated = false;
				if (fullStart) m.restart();
			} 
			else if (x <= p.width / 2 + r / 2 && x >= p.width / 2 - r / 2 && y <= 0.8 * p.height + r / 2
					&& y >= 0.8 * p.height - r / 2) pToggled = false;
			else if (x <= p.width / 2 + r / 2 && x >= p.width / 2 - r / 2 && y <= (0.8 * p.height - (3.5 * h)) + r / 2
					&& y >= (0.8 * p.height - (3.5 * h)) - r / 2)
				looped = false;
		}
	}

	public class Music {
		Clip clip;

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
						next();
					}

				}

			});
		}

		public void restart() {
			clip.setFramePosition(0);
			clip.start();
		}

		public void play() {
			clip.setFramePosition(frame);
			clip.start();
		}

//		public void loop() {
//			clip.loop(Clip.LOOP_CONTINUOUSLY);
//		}

		public void stop() {
			frame = clip.getFramePosition();
			clip.stop();
			clip.close();
		}

	}

	public void next() {
		no.add(list.remove(findPlace()));
		if (!repeat && list.size() == 0) m.stop();
		else if (repeat && list.size() == 0) {
			for (int i = 0; i < no.size(); i++) {
				list.add(no.remove(i));
				i--;
			}
		}
		if (list.size() != 0){
			int n = (int) (Math.random() * list.size());
			playMusic = list.get(n);
			m.setFile(playMusic);
			m.play();
		}
	}
	
	private int findPlace() {
		for (int i = 0; i < list.size(); i++) {
			if (playMusic.equals(list.get(i))) return i;
		}
		return -1;
	}

}