package com.xsp.timermusic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CallAlarm extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub

		Intent i = new Intent(context, Alarmalert.class);
		// Bundle bundleRet = new Bundle();
		// bundleRet.putString("STR_CALLER", "");
		// i.putExtras(bundleRet);
		// Ĭ�ϵ���ת����,��Activity�ŵ�һ���µ�Task��
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);
	}

}
