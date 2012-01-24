package com.shyamu.knockout;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.Toast;

public class Alarm extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(context, "ALARM!!!!", Toast.LENGTH_LONG).show();
		
		NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(R.raw.alarmclockicon,"WAKE UP!!!", System.currentTimeMillis());
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0, new Intent(context, KnockOutActivity.class), 0);
		notification.setLatestEventInfo(context, "Alarm", "Alarm", contentIntent);
		
		
		notification.flags = Notification.FLAG_INSISTENT | Notification.FLAG_SHOW_LIGHTS;
		notification.ledARGB = Color.GREEN;
		notification.ledOnMS = 1000;
		notification.ledOffMS = 500;
		notification.defaults = Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE;
		
		
		//notification.flags = Notification.FLAG_INSISTENT;
		
		//notification.sound = (Uri) intent.getExtras().get("Ringtone");
		//notification.vibrate = (long[]) intent.getExtras().get("vibrationPattern");
		
		final int NOTIFICATION_ID = 1;
		
		manager.notify(NOTIFICATION_ID, notification);
	}

}
