package nyc.c4q.review2.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by keshwans on 8/29/15.
 */
public class BackgroundService extends Service {
    private static final String TAG = BackgroundService.class.getSimpleName();
    boolean isRunning; //if long background has not finished yet when alarm fires, then we do not want to start another service instance
    Context context;
    private Thread backgroundThread;
    private Runnable runnableTask = new Runnable() {
        @Override
        public void run() {
            //do something
           Log.d(TAG, "BackgroundService is running");

            //stop the service
            stopSelf();
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        isRunning = false;

        backgroundThread = new Thread(runnableTask);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!isRunning) {
            isRunning = true;
            backgroundThread.start();
        }
        else {
            System.out.println("BackgroundService is already running");
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }
}
