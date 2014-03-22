package com.xsp.timermusic;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class Alarmalert extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		Intent intent1 = new Intent(Alarmalert.this, PlayMusic.class);
		startService(intent1);
		new AlertDialog.Builder(Alarmalert.this)
				.setTitle("∂® ±“Ù¿÷")
				.setPositiveButton("Õ£÷π“Ù¿÷",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								Intent intent1 = new Intent(Alarmalert.this,
										PlayMusic.class);
								stopService(intent1);
								Alarmalert.this.finish();
							}
						}).show();
	}

	@Override
	protected void onDestroy() {
		Intent intent1 = new Intent(this, PlayMusic.class);
		stopService(intent1);
		super.onDestroy();
	}

}
