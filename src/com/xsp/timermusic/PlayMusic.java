package com.xsp.timermusic;

import java.io.File;
import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.IBinder;

public class PlayMusic extends Service {

	private MediaPlayer myMediaPlayer;
	File file = new File("/mnt/ext_sdcard/Music");
	File[] files = file.listFiles();
	int index = 0;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {

		// myMediaPlayer = MediaPlayer.create(this, R.raw.tx);
		myMediaPlayer = new MediaPlayer();
		myMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		try {
			myMediaPlayer.setDataSource(files[index++].getAbsolutePath());
			myMediaPlayer.prepareAsync();
			myMediaPlayer.setOnPreparedListener(new OnPreparedListener() {
				@Override
				public void onPrepared(MediaPlayer arg0) {
					myMediaPlayer.start();
				}
			});
			myMediaPlayer.setOnCompletionListener(new OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer arg0) {
					if (index < files.length) {

					} else {
						index = 0;
					}
					try {
						myMediaPlayer.stop();
						myMediaPlayer.reset();
						myMediaPlayer.setDataSource(files[index++]
								.getAbsolutePath());
						myMediaPlayer.prepareAsync();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		super.onCreate();

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		myMediaPlayer.stop();
		myMediaPlayer.release();
		myMediaPlayer = null;
		super.onDestroy();
	}

}
