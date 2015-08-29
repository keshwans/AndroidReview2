package nyc.c4q.review2.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import nyc.c4q.review2.services.BackgroundService;


public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //when AlarmReceiver fires, start the service
        Intent serviceStartIntent = new Intent(context, BackgroundService.class);
        context.startService(serviceStartIntent);
    }
}
