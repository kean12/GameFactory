package com.game.assist.client;

import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;//jl1.jar 

public class Mp3Player {
	static Thread pt = null;
	public static void play(final File af) {
		if (pt != null && pt.isAlive())
			return;
		pt = new Thread() {
			public void run() {
				try {
					new Player(new FileInputStream(af)).play();
				} catch (Exception e) {
				}
			}
		};
		pt.start();
	}

	public static void play(String fs) {
		play(new File(fs));
	}

	public static void play() {
		try {
			new Player(Mp3Player.class.getResourceAsStream("yy.MP3")).play();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		play();// 播放d://yy.mp3
	}
}