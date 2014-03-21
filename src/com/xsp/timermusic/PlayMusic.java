package com.xsp.timermusic;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.MediaStore.Audio.Media;

public class PlayMusic extends Service {

	private MediaPlayer myMediaPlayer;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		myMediaPlayer = MediaPlayer.create(this, R.raw.tx);
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		myMediaPlayer.stop();
		super.onDestroy();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		myMediaPlayer.start();
		super.onStart(intent, startId);
	}

}
