import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class Sound extends JFrame implements ActionListener, MouseListener {

	JFrame window;
	JPanel panel;
	JButton playButton, pauseButton, resButton, loop1B, loopAllB;
	String playMusic;
	int frame;
	Color button = Color.red;
	// ButtonHandler bH = new ButtonHandler();
	Music m = new Music();
	boolean play = true;
	ImageIcon iL = new ImageIcon("src/play.png"), iU = new ImageIcon("src/pause.png");

	public static void main(String[] args) {

		new Sound();
	}

	public Sound() {
		frame = 0;
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(500, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// window.getContentPane().add(this);
		playMusic = "src/Din Shagna Da (Phillauri).wav";

		playButton = new JButton(iL);
		playButton.setBounds(getWidth() / 2 - 70 / 2 + 3, (int) (0.7 * getHeight()), 70, 50);
		playButton.setContentAreaFilled(false);
		//playButton.setBackground(button);
		playButton.setBorderPainted(false);
		playButton.setOpaque(false);
		playButton.setVisible(true);
		// playButton.setBackground(Color.black);
		playButton.addActionListener(this);
		//playButton.addMouseListener(this);

		pauseButton = new JButton(iU);
		pauseButton.setBounds(getWidth() / 2 - 70 / 2, (int) (0.7 * getHeight()), 70, 50);
		pauseButton.setContentAreaFilled(false);
		//pauseButton.setBackground(button);
		pauseButton.setBorderPainted(false);
		pauseButton.setOpaque(false);
		pauseButton.setVisible(false);
		// pauseButton.setBackground(Color.black);
		pauseButton.addActionListener(this);
		//pauseButton.addMouseListener(this);

		resButton = new JButton(iL);
		resButton.setBounds(getWidth() / 2 - 70 / 2 + 3, (int) (0.7 * getHeight()) - 50, 70, 50);
		resButton.setContentAreaFilled(false);
		//resButton.setBackground(button);
		resButton.setBorderPainted(false);
		resButton.setOpaque(false);
		// resButton.setBackground(Color.black);
		resButton.addActionListener(this);
		
		loop1B = new JButton(iL);
		loop1B.setBounds((int) (0.75 * getWidth()) - 35, (int) (0.7 * getHeight()), 70, 50);
		loop1B.setContentAreaFilled(false);
		//loop1B.setBackground(button);
		loop1B.setBorderPainted(false);
		loop1B.setOpaque(false);
		// loop1B.setBackground(Color.black);
		loop1B.addActionListener(this);
		
		loopAllB = new JButton(iL);
		loopAllB.setBounds((int) (0.25 * getWidth()) - 35, (int) (0.7 * getHeight()), 70, 50);
		loopAllB.setContentAreaFilled(false);
		//loopAllB.setBackground(button);
		loopAllB.setBorderPainted(false);
		loopAllB.setOpaque(false);
		// loopAllB.setBackground(Color.black);
		loopAllB.addActionListener(this);
		
		
		panel.add(playButton);
		panel.add(loopAllB);
		panel.add(pauseButton);
		getContentPane().add(panel);

		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		panel.setVisible(true);
		//add(loopAllB);
		//this.addMouseListener(this);
		// buttonPanel = new JPanel();
		// buttonPanel.setBounds(300, 300, 200, 100);
		// buttonPanel.setBackground(Color.black);
		// con.add(buttonPanel);

		// playButton = new JButton("Music");
		// playButton.setFocusPainted(false);
		// playButton.addActionListener(this);
		// playButton.setBackground(Color.black);
		// playButton.setForeground(Color.white);
		// playButton.setOpaque(true);
		// window.add(playButton);
		// window.setSize(800, 800);
		// window.getContentPane().setBackground(Color.black);
		// window.setLayout(null);
		// con = window.getContentPane();
		// window.setVisible(true);

	}

//	public void paint(Graphics g) {
//		super.paintComponents(g);
//		g.setColor(button);
//		g.fillOval(getWidth() / 2 - 70/2, (int) (0.5 * getHeight()), 70, 70);
//	}

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
		}

		public void restart() {
			clip.setFramePosition(0);
			clip.start();
		}

		public void play() {
			clip.setFramePosition(frame);
			clip.start();
		}

		public void loop() {
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}

		public void stop() {
			frame = clip.getFramePosition();
			clip.stop();
			clip.close();
		}

	}

	public void actionPerformed(ActionEvent e) {
		if (play && e.getSource().equals(playButton)) {
			// playButton.setText("Turn Music Off");
			playButton.setVisible(false);
			pauseButton.setVisible(true);
			m.setFile(playMusic);
			m.play();
			m.loop();
			play = false;
		} 
		else if (!play && e.getSource().equals(pauseButton)){
			m.stop();
			pauseButton.setVisible(false);
			playButton.setVisible(true);
			// playButton.setText("Turn Music On");
			play = true;
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}