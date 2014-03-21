package com.xsp.timermusic;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {
	   private TextView textView;
	   private Button setbtn,unsetbtn;
	   //�ظ����ӵİ�ť��TextView   
	   private Button setbtns,unsetbtns;
	   private TextView textView1;
	   private static final int ButtonAlarm =1;
	   //�����ļ���
	   private String setAlarmFloder = "/sdcard/music/alarms";
	   

	   private static final String TEMP_1="temp_1";
	   
	   Calendar c = Calendar.getInstance();
	   private static int timeID= 0;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        
	        textView = (TextView)findViewById(R.id.textview1);
	        setbtn = (Button)findViewById(R.id.setclock);
	        unsetbtn = (Button)findViewById(R.id.unsetclock);
	        setbtns = (Button)findViewById(R.id.setclocks);
	        unsetbtns = (Button)findViewById(R.id.unsetclocks);
	        textView1 = (TextView)findViewById(R.id.textview4);
	        
	        //��ȡSharedPreferences
	        SharedPreferences per = getSharedPreferences(TEMP_1, MODE_WORLD_READABLE);
	        //�õ�text����
	        String text_1 =per.getString("text_1", "��ǰû����������!");
	        String text_2 =per.getString("text_2", "��ǰû����������!");
	        //��text����ʾ����
	        textView.setText(text_1);
	        textView1.setText(text_2);
	        //����������ť
	        //setbell = (Button)findViewById(R.id.btn3);
	        
	        
	        setbtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// ȡ�ð��°�ť��ʱ����ΪTimePickerDialog��Ĭ��ֵ
					c.setTimeInMillis(System.currentTimeMillis());
					//�����ȡʱ��
					int mHour = c.get(Calendar.HOUR_OF_DAY);
					int mMinute = c.get(Calendar.MINUTE);
					//����TimePickerDialog���趨ʱ��
					new TimePickerDialog(MainActivity.this,
							new TimePickerDialog.OnTimeSetListener() {
								
								@Override
								public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
									// ȡ���趨���ʱ�� ���������Ϊ0
									c.setTimeInMillis(System.currentTimeMillis());
									c.set(Calendar.HOUR_OF_DAY, hourOfDay);
									c.set(Calendar.MINUTE, minute);
									c.set(Calendar.SECOND, 0);
									c.set(Calendar.MILLISECOND, 0);
									//�������趨ʱ�䵽ʱҪִ��CallAlarm.class
									Intent intent  = new Intent(MainActivity.this,CallAlarm.class);
									PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
									//��ȡAlarmManager 
									//AlarmManager.RTC_WAKEUP�趨������ϵͳ����ʱͬ����ִ��
									//
									AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
									//set()�趨��PendingIntentֻ��ִ��һ��
									am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),sender);
									//��ʾ�趨������ʱ��
									String tmpS = format(hourOfDay)+":"+ format(minute);
									textView.setText(tmpS);
									//toas��ʾ�趨��ʱ��
									Toast.makeText(MainActivity.this, "�趨��ʱ��Ϊ:"+tmpS, Toast.LENGTH_LONG).show();
								}
							},mHour, mMinute, true).show();
					}
			});
	        //ȡ������
	        
	        unsetbtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new  Intent (MainActivity.this,CallAlarm.class);
					PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
					//��AlarmManager���Ƴ�
					AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
					am.cancel(sender);
					textView.setText("��ǰû����������!");
					//��Toast��ʾ��ɾ���趨����������ʾ������ʱ��
					Toast.makeText(MainActivity.this, "�����Ѿ�ȡ��",Toast.LENGTH_LONG).show();
				}
			});
	        
	        
	        //һ�������ظ�����
	        /* create�ظ���������ӵ����û��� */  
	        /* ����timeset.xmlΪLayout */
	        /* LayoutInflater������Layout�µ�xml�ļ������ҽ�xml�ļ�ʵ���� */
	        LayoutInflater layoutInflater =LayoutInflater.from(this);
	        final View setView = layoutInflater.inflate(R.layout.timetest, null);
	        final TimePicker tPicker = (TimePicker)setView.findViewById(R.id.tPicker);
	        tPicker.setIs24HourView(true);
	        
	        final AlertDialog di = new AlertDialog.Builder(MainActivity.this)
	        .setTitle("�趨").setView(setView).setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				
				@Override
				
				public void onClick(DialogInterface dialog, int which) {
					// ȡ���趨�ļ������
					EditText ed = (EditText)setView.findViewById(R.id.mEdit);
					int times = Integer.parseInt(ed.getText().toString())*1000;
					//ȡ���趨��ʱ��
					c.setTimeInMillis(System.currentTimeMillis());
					c.set(Calendar.HOUR_OF_DAY, tPicker.getCurrentHour());
					c.set(Calendar.MINUTE, tPicker.getCurrentMinute());
					c.set(Calendar.SECOND, 0);
		            c.set(Calendar.MILLISECOND, 0);
		            //ָ�������趨ʱ�䵽��Ҫִ��callalarm
		            Intent intent = new Intent(MainActivity.this,CallAlarm.class);
		            PendingIntent sender = PendingIntent.getBroadcast(
		            		MainActivity.this, 1, intent, 0);
		            //setRepeating()���������ظ�ִ�� */
		            AlarmManager am;
		            am = (AlarmManager)getSystemService(ALARM_SERVICE);
		            am.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), times, sender);
		            /* ������ʾ���趨����ʱ�� */
		            String tmps = format(tPicker.getCurrentHour())+":"+format(tPicker.getCurrentMinute());
		            textView1.setText(tmps);
		            /* ��Toast��ʾ�趨����� */
		            Toast.makeText(MainActivity.this, "�趨����ʱ��Ϊ" + tmps + "��ʼ���ظ����Ϊ" + times / 1000 + "��", Toast.LENGTH_LONG).show();
				}
			}).setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			}).create();
	        
	        //�ظ���������ӵ��趨Button
	        setbtns.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// ȡ�ð��°�ťʱ��ʱ����ΪtPicker��Ĭ��ֵ
					c.setTimeInMillis(System.currentTimeMillis());
					tPicker.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
					tPicker.setCurrentMinute(c.get(Calendar.MINUTE));
					//�����趨����di
					di.show();
					
				}
			});
	        //�ظ���������ӵ��Ƴ�Button
	        
	        unsetbtns.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(MainActivity.this,CallAlarm.class);
					PendingIntent sender = PendingIntent.getBroadcast(
		            		MainActivity.this, 1, intent, 0);
					AlarmManager am;
		            am = (AlarmManager)getSystemService(ALARM_SERVICE);
		            am.cancel(sender);
		            Toast.makeText(MainActivity.this, "����ʱ����", Toast.LENGTH_LONG).show();
		            textView1.setText("��ǰû����������!");
				}
			});
	    
//	        setbell.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					if (bFolder(setAlarmFloder)) {
//						//��ϵͳ��������
//						Intent intent =  new  Intent (RingtoneManager
//								.ACTION_RINGTONE_PICKER);
//						//�����������ͺ�title
//						intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALARM);
//						intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "������������");
//						//������ɺ�,���ص�ǰ��activity
//						startActivityForResult(intent, ButtonAlarm);
//					}
//				}
//			});
	    
	    }
	    
	    //����������֮��Ļص�����
//	    @Override
//		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//			// TODO Auto-generated method stub
//	    	if (requestCode != RESULT_OK) {
//				return;
//			}
//	    	switch (requestCode) {
//			case ButtonAlarm:
//				Uri pickedUri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
//				//��ѡ�����������ΪĬ��
//				if (pickedUri != null) {
//					RingtoneManager.setActualDefaultRingtoneUri(MainActivity.this, RingtoneManager
//							.TYPE_ALARM, pickedUri);
//				}
//				break;
	//
//			default:
//				break;
//			}
//	    	
//	    	
//			super.onActivityResult(requestCode, resultCode, data);
//		}
	    
	    //����Ƿ����ָ�����ļ���,���������,�򴴽�
//	    private boolean bFolder (String serbFolder){
//	    	boolean btmp =false;
//	    	File f = new File(serbFolder);
//	    	if (!f.exists()) {
//				if (f.mkdirs()) {
//					btmp = true;
//				}else {
//					btmp = false;
//				}
//			}else {
//				btmp =true;
//			}
//	    	return btmp;
//	    }
	//    
	    


		private String format(int x)
	    {
	      String s = "" + x;
	      if (s.length() == 1)
	        s = "0" + s;
	      return s;
	    }

		@Override
		protected void onStop() {
			// TODO Auto-generated method stub
			super.onStop();
			//��ȡ������
			SharedPreferences.Editor editor = 
					getSharedPreferences(TEMP_1, MODE_WORLD_WRITEABLE).edit();
			//��text������ӵ������� 
			editor.putString("text_1", textView.getText().toString());
			editor.putString("text_2", textView1.getText().toString());
			//�ύ�༭����
			editor.commit();
		}}
