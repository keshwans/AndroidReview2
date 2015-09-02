package nyc.c4q.review2.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class AppService extends Service {
	private static final String TAG = AppService.class.getSimpleName();
	public static final String SERVICE_EXTRA_MSG_KEY = "nyc.c4q.review2.saying";

	private IBinder mBinder = null;
	public static final String NOTIFICATION = "nyc.c4q.review2.services.AppService called";
	
	public class LocalBinder extends Binder {
		public AppService getService() {
            // Return this instance of LocalService so clients can call public methods
            return AppService.this;
        }
		public int addNumbers(int x, int y) {
			return AppService.this.add(x, y);
		}
    }
	
	@Override
	 public void onCreate() {
	        super.onCreate();
	        Log.d(TAG, "AppService created");
		 mBinder = new LocalBinder();

	        Intent intent1 = new Intent(NOTIFICATION);
			  // You can also include some extra data.
			  intent1.putExtra(SERVICE_EXTRA_MSG_KEY, "This is AppService's message for Local Broadcast only");
			  LocalBroadcastManager.getInstance(this).sendBroadcast(intent1);
	 } 
	@Override
	public IBinder onBind(Intent intent) {
		Log.d(AppService.class.getName(), "Bound");
		return mBinder;
	}

	public int add(int x, int y) {
		return x + y;
	}
}
