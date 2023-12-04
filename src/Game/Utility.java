package Game;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.sound.sampled.*;

/** 게임 실행에 유용한 기타 메소드를 담고 있는 클래스이다.*/
public class Utility {
	private static Clip sound;
	private static boolean isPlaying = false;
	private static long soundPosition = 0;
	
	static Color backcolor = new Color(248, 244, 235);
	/**기본 색깔*/
    static Color basecolor = new Color(237,227,206);
    /**주요 색깔*/
    static Color maincolor = new Color(125,159,104);
    /**포인트 색깔*/
    static Color pointcolor = new Color(80,102,67);
    
	/** 배경음악을 실행시켜주는 메소드이다. */
	public static void playMusic() {
		try {
			File audioFile = new File("src/music/sound.wav");
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

			sound = AudioSystem.getClip();
			sound.open(audioStream);
			sound.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
	}
	/** 음악을 재생하는 메소드이다. */
	public static void startMusic() {
		sound.setMicrosecondPosition(soundPosition);
		sound.start();
		isPlaying = true;
	}
	/** 음악을 정지하는 메소드이다. */
	public static void stopMusic() {
		if(sound != null && sound.isRunning()) {
			sound.stop();
		}
		isPlaying = false;
	}
	/** 폰트를 지정해주는 메소드이다. */
	public static Font yeongdeok_haeparang(float size) {
		Font yeongdeok_haeparang = null;
		try {
			File fontFile = new File("src/font/Yeongdeok Haeparang.ttf");
			yeongdeok_haeparang = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(size);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, fontFile));
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		return yeongdeok_haeparang;
	}
	/** 폰트를 지정해주는 메소드이다. */
	public static Font yeongdeok_sea(float size) {
		Font yeongdeok_sea = null;
		try {
			File fontFile = new File("src/font/Yeongdeok Sea.ttf");
			yeongdeok_sea = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(size);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, fontFile));
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		return yeongdeok_sea;
	}
}
