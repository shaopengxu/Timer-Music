package com.xsp.timermusic;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class Alarmalert extends Activity {

	private static  Alarmalert alarmAlert;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Intent intent1= new Intent(Alarmalert.this,PlayMusic.class);
		startService(intent1);
		//ƒ÷÷”œ‘ æª≠√Ê
		new AlertDialog.Builder(Alarmalert.this).setTitle("ƒ÷÷”œÏ¡À!!").setMessage("∆¥≤¿≤!!")
		.setPositiveButton("πÿ±’ƒ÷÷”", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent intent1= new Intent(Alarmalert.this,PlayMusic.class);
				stopService(intent1);
				Alarmalert.this.finish();
			}
		}).show();
	}
	
	private static Alarmalert getInstance(){
		return alarmAlert;
	}

}
