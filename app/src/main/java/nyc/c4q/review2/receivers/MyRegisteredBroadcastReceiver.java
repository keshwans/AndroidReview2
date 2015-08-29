package nyc.c4q.review2.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import nyc.c4q.review2.services.AppService;

public class MyRegisteredBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = MyRegisteredBroadcastReceiver.class.getSimpleName();

    @Override
	public void onReceive(Context context, Intent intent) {

		Log.d(TAG, "received broadcast");
        String msg = intent.getStringExtra(AppService.SERVICE_EXTRA_MSG_KEY);
        if (msg == null || msg.isEmpty()) {
            msg = "empty";
        }

		Toast.makeText(context, msg,
		        Toast.LENGTH_LONG).show();

		    // Vibrate the mobile phone
		    Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		    vibrator.vibrate(2000);

	}

}
