package com.shyamu.knockout;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.text.format.Time;

public class KnockOutActivity extends Activity {

	Button clickMe, b1, b2, b3;
	TextView text;
	long currentTime, time1, time2, time3;
	Time formattedTime = new Time();
	String strTime = null;

	Toast mToast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// set view elements
		setContentView(R.layout.main);
		b1 = (Button) findViewById(R.id.bTime1);
		b2 = (Button) findViewById(R.id.bTime2);
		b3 = (Button) findViewById(R.id.bTime3);
		clickMe = (Button) findViewById(R.id.bClickMe);
		text = (TextView) findViewById(R.id.tvText);

	}

	public void bClickMe(View view) { // Code for when Click me button has been
		// pressed
		getTimes();
		b1.setText(formatTime(time1));
		b2.setText(formatTime(time2));
		b3.setText(formatTime(time3));
	}

	public void bTime1(View view) { // Code for when the first time button has
									// been pressed
		createAlarmWhenButtonIsPressed(time1);
	}

	public void bTime2(View view) { // Code for when the second time button has
									// been pressed
		createAlarmWhenButtonIsPressed(time2);
	}

	public void bTime3(View view) { // Code for when the third time button has
									// been pressed
		createAlarmWhenButtonIsPressed(time3);
	}

	private String formatTime(long time) {
		formattedTime.set(time);
		int intHour = formattedTime.hour;
		String strMin = Integer.toString(formattedTime.minute);

		if (formattedTime.minute < 10) { // check to see if leading 0 is needed
											// on minutes
			strMin = "0" + formattedTime.minute; // add leading 0
		}

		if (formattedTime.hour > 12) {
			intHour = formattedTime.hour - 12; // non 24 hour
			return intHour + ":" + strMin + " PM";
		}

		else if (formattedTime.hour == 12) { // check for noon
			return intHour + ":" + strMin + "PM"; // special case at noon
		}

		else if (formattedTime.hour == 0) { // check for midnight
			intHour = formattedTime.hour + 12; // special case at midnight
			return intHour + ":" + strMin + " AM";
		}

		else
			return intHour + ":" + strMin + " AM";

	}

	private void getTimes() { // get current time in milliseconds
		currentTime = System.currentTimeMillis();

		// time1 = currentTime + 5000 ; //for debugging... alarm 1 for 5 sec after set
		// time2 = currentTime + 10000; //for debugging... alarm 2 for 10 sec after set
		// time3 = currentTime + 3600000; //for debugging... alarm 3 for 1 hour after set

		// add 15 min + 90 min intervals 3 times
		time1 = currentTime + 900000 + (5400000 * 5); // 15 min + 90 min (5 times)
		time2 = time1 + 5400000; // time1 + 90 min
		time3 = time2 + 5400000; // time2 + 90 min
	}

	private void createAlarmWhenButtonIsPressed(long time) {
		Intent intent = new Intent(this, Alarm.class);
		intent.putExtra("Ringtone", R.raw.alarm);
		intent.putExtra("vibrationPattern", new long[] { 200, 300 });
		PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, 0);

		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		am.set(AlarmManager.RTC_WAKEUP, time, sender);

		if (mToast != null) {
			mToast.cancel();
		}
		mToast = Toast.makeText(this, "Alarm has been set for: "
				+ formatTime(time), Toast.LENGTH_SHORT);
		mToast.show();
	}

}
