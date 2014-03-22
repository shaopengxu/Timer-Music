package com.xsp.timermusic;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {
	private TextView textView;
	private Button setbtn, unsetbtn;

	Calendar c = Calendar.getInstance();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textView = (TextView) findViewById(R.id.textview1);
		setbtn = (Button) findViewById(R.id.setclock);
		unsetbtn = (Button) findViewById(R.id.unsetclock);

		String text_1 = "";
		textView.setText(text_1);

		setbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				c.setTimeInMillis(System.currentTimeMillis());
				int mHour = c.get(Calendar.HOUR_OF_DAY);
				int mMinute = c.get(Calendar.MINUTE);
				new TimePickerDialog(MainActivity.this,
						new TimePickerDialog.OnTimeSetListener() {
							boolean callOnTimeSet = false;

							@Override
							public void onTimeSet(TimePicker view,
									int hourOfDay, int minute) {
								if (!callOnTimeSet) {
									callOnTimeSet = true;
								} else {
									return;
								}
								c.setTimeInMillis(System.currentTimeMillis());
								c.set(Calendar.HOUR_OF_DAY, hourOfDay);
								c.set(Calendar.MINUTE, minute);
								c.set(Calendar.SECOND, 0);
								c.set(Calendar.MILLISECOND, 0);
								Intent intent = new Intent(MainActivity.this,
										CallAlarm.class);
								PendingIntent sender = PendingIntent
										.getBroadcast(
												MainActivity.this,
												1,
												intent,
												PendingIntent.FLAG_UPDATE_CURRENT);
								AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
								am.set(AlarmManager.RTC_WAKEUP,
										c.getTimeInMillis(), sender);
								String tmpS = format(hourOfDay) + ":"
										+ format(minute);
								textView.setText(tmpS);
								Toast.makeText(MainActivity.this,
										"设定的时间为:" + tmpS, Toast.LENGTH_LONG)
										.show();

							}
						}, mHour, mMinute, true).show();
			}
		});
		unsetbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, CallAlarm.class);
				PendingIntent sender = PendingIntent.getBroadcast(
						MainActivity.this, 0, intent, 0);
				AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
				am.cancel(sender);
				textView.setText("");
				Toast.makeText(MainActivity.this, "音乐已经取消", Toast.LENGTH_LONG)
						.show();
			}
		});

	}

	private String format(int x) {
		String s = "" + x;
		if (s.length() == 1)
			s = "0" + s;
		return s;
	}

	@Override
	protected void onStop() {
		super.onStop();
	}
}
