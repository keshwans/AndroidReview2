package nyc.c4q.review2.ui;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import nyc.c4q.review2.R;
import nyc.c4q.review2.receivers.AlarmReceiver;
import nyc.c4q.review2.services.AppService;
import nyc.c4q.review2.receivers.MyRegisteredBroadcastReceiver;

public class MainActivity extends AppCompatActivity {

    public static final String INTENT_EXTRA_ALARM_KEY = "nyc.c4q.review2.alarm";
    BroadcastReceiver mReciever;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

//        RecipesResponse recipesResponse1a =
//                JsonHelperVersion1a.loadStaticJsonRaw(this, R.raw.pearson_recipes_chicken);
//        Log.d(TAG, "recipesResponse1:\n" + recipesResponse1a);
//
//        RecipesResponse recipesResponse1b = JsonHelperVersion1b.loadStaticJsonRawUsingGson(this, R.raw.pearson_recipes_chicken);
//        Log.d(TAG, "recipesResponse1b:\n" + recipesResponse1b);
//
//        nyc.c4q.review2.models.pearsonVersion2.RecipesResponse recipesResponse2 = JsonHelperVersion2.loadStaticJsonRawUsingGson(this, R.raw.pearson_recipes_chicken);
//        Log.d(TAG, "recipesResponse2:\n" + recipesResponse2);
//
//        nyc.c4q.review2.models.pearsonVersion3.RecipesResponse recipesResponse3 = JsonHelperVersion3.loadStaticJsonRawUsingGson(this, R.raw.pearson_recipes_chicken);
//        Log.d(TAG, "recipesResponse3:\n" + recipesResponse3);
//
//        String response2 = JsonHelperVersion2.convertToJsonUsingGson(recipesResponse2);
//        Log.d(TAG, "response2-json:" + response2);
//
//        String response3 = JsonHelperVersion3.convertToJsonUsingGson(recipesResponse3);
//        Log.d(TAG, "response3-json:" + response3);

    }

    @Override
    public void onResume() {
        super.onResume();
        mReciever = new MyRegisteredBroadcastReceiver();

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mReciever, new IntentFilter(AppService.NOTIFICATION));

        Log.d(TAG, "Registered");
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mReciever != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(mReciever);
            mReciever = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.start_alrarm){
            startAlarm();
        }

        return super.onOptionsItemSelected(item);
    }

    protected void startAlarm() {

        //check if alarm already set to fire AlarmReceiver
        // if not set then set it to wake up every 3 seconds.
        // AlarmReceiver is setup to start the background service.

        int AlarmIntentId = 102;
        Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        PendingIntent prevPendingIntent = PendingIntent.getBroadcast(this, AlarmIntentId, alarmIntent, PendingIntent.FLAG_NO_CREATE);
        boolean alarmRunning = (prevPendingIntent != null);

        if (!alarmRunning) {
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, AlarmIntentId, alarmIntent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.setRepeating( AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), 3 * 1000, pendingIntent );
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        Button btnStart;
        Button btnStartOrdered;
        Button btnStartService;
        Button btnSendSticky;
        EditText etText;

        Context mContext;

        private static String SOMETHING_HAPPENED = "nyc.c4q.review2.ui.somethinghappened";
        private static String EXTRA_INTEGER = "extra integer";

        boolean mBound = false;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {

            View fragmentView = inflater.inflate(R.layout.fragment_main, container, false);

            etText = (EditText) fragmentView.findViewById(R.id.et_time_sec);

            btnStart = (Button) fragmentView.findViewById(R.id.btn_start_timer);
            btnStartOrdered = (Button) fragmentView.findViewById(R.id.btn_ordered_start);
            btnStartService = (Button) fragmentView.findViewById(R.id.btn_local_broadcast);
            btnSendSticky = (Button) fragmentView.findViewById(R.id.btn_send_sticky);

            initializeView(fragmentView); //adds click listeners etc.

            return fragmentView;
        }
        private void setNotificationAlarmAfter(String message, int year, int month, int day, int hour, int min, int sec) {
            Calendar cal = Calendar.getInstance();
            long currentTimeInMillis = cal.getTimeInMillis();

            cal.add(Calendar.YEAR, year);
            cal.add(Calendar.MONTH, month);
            cal.add(Calendar.DATE, day);
            cal.add(Calendar.HOUR, hour);
            cal.add(Calendar.MINUTE, min);
            cal.add(Calendar.SECOND, sec);

            long futureTimeInMillis = cal.getTimeInMillis();
            int deltaMillis = (int) (futureTimeInMillis - currentTimeInMillis);

            // asking alarm manager to wake up after a timeout and send the intent to explicitly named
            // MyRegisteredBroadcastReceiver.class. The intent has extra

            Intent intent = new Intent(mContext, MyRegisteredBroadcastReceiver.class);
            intent.putExtra(INTENT_EXTRA_ALARM_KEY, message);

            int requestCode = 1000;
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    mContext.getApplicationContext(),
                    requestCode, intent, 0);

            AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, deltaMillis, pendingIntent);

            Toast.makeText(mContext, "Alarm set for " + (deltaMillis / 1000) + " seconds", Toast.LENGTH_LONG).show();
        }

        private void setNotificationAlarmAt(String message, int year, int month, int day, int hour, int min, int sec) {
            Calendar cal = Calendar.getInstance();
            long currentTimeInMillis = cal.getTimeInMillis();

            cal.set(year, month, day);
            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.MINUTE, min);
            cal.set(Calendar.SECOND, sec);

            long futureTimeInMillis = cal.getTimeInMillis();
            int deltaMillis = (int) (futureTimeInMillis - currentTimeInMillis);

            // asking alarm manager to wake up after a timeout and send the intent to explicitly named
            // MyRegisteredBroadcastReceiver.class. The intent has extra

            Intent intent = new Intent(mContext, MyRegisteredBroadcastReceiver.class);
            intent.putExtra(INTENT_EXTRA_ALARM_KEY, message);

            int requestCode = 1000;
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    mContext.getApplicationContext(),
                    requestCode, intent, 0);

            AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, deltaMillis, pendingIntent);

            Toast.makeText(mContext, "Alarm set for " + (deltaMillis / 1000) + " seconds", Toast.LENGTH_LONG).show();
        }

        private void initializeView(View view) {
            btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int timeoutSecs = 2;
                    try {
                        String value = etText.getText().toString();
                        timeoutSecs = Integer.parseInt(value); //Double.parseDouble(value) if a double was expected
                    } catch (Exception ignore) {
                    }

                    setNotificationAlarmAfter("Don't panic but your time is up!!!!.", 0, 0, 0, 0, 0, timeoutSecs);
                }
            });

            btnStartOrdered.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    IntentFilter filterInitial = new IntentFilter(SOMETHING_HAPPENED);
                    filterInitial.setPriority(2);

                    IntentFilter filterSecond = new IntentFilter(SOMETHING_HAPPENED);
                    filterSecond.setPriority(1);

                    //dynamically created broadcast receiver

                    BroadcastReceiver initialBroadcastReceiver = new BroadcastReceiver() {
                        @Override
                        public void onReceive(Context context, Intent intent) {
                            Bundle results = getResultExtras(true);
                            results.putInt(EXTRA_INTEGER, 100);
                            Toast.makeText(mContext, "In Initial Receiver: Put 'extra integer' = 100", Toast.LENGTH_LONG).show();
                        }
                    };

                    BroadcastReceiver secondBroadcastReceiver = new BroadcastReceiver() {
                        @Override
                        public void onReceive(Context context, Intent intent) {
                            Bundle results = getResultExtras(true);
                            int extraInt =  results.getInt(EXTRA_INTEGER, 0);
                            extraInt += 50;
                            results.putInt(EXTRA_INTEGER, extraInt);
                            Toast.makeText(mContext, "In second Receiver: Put 'extra integer' = " + extraInt, Toast.LENGTH_LONG).show();
                        }
                    };

                    BroadcastReceiver orderedResultReceiver = new BroadcastReceiver() {
                        @Override
                        public void onReceive(Context context, Intent intent) {
                            Bundle results = getResultExtras(true);
                            Toast.makeText(mContext,
                                    "In orderedResultReceiver: Got 'extra integer' = "
                                            + results.getInt(EXTRA_INTEGER, -1), Toast.LENGTH_LONG).show();
                        }
                    };

                    Intent intent = new Intent(SOMETHING_HAPPENED);
                    mContext.registerReceiver(initialBroadcastReceiver, filterInitial);
                    mContext.registerReceiver(secondBroadcastReceiver, filterSecond);

                    mContext.sendOrderedBroadcast(intent, null, orderedResultReceiver, null, Activity.RESULT_OK, null, null);
                }
            });

            btnStartService.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.d("btnStartService", "binding service");
                    Intent intent = new Intent(mContext, AppService.class);
                    mContext.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
                }
            });

            btnSendSticky.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent("pass.data.from.activities.withSticky");
                    intent.putExtra("message", "This is passing with Sticky Intents");
                    getActivity().sendStickyBroadcast(intent);
                    Intent myIntent = new Intent(getActivity(), SecondActivity.class);
                    startActivity(myIntent);
                }
            });

        }


        /**
         * Defines callbacks for service binding, passed to bindService()
         */
        private ServiceConnection mConnection = new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName className,
                                           IBinder service) {
                // We've bound to LocalService, cast the IBinder and get LocalService instance
                AppService.LocalBinder binder = (AppService.LocalBinder) service;
                mBound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName arg0) {
                mBound = false;
            }
        };

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            mContext = activity;
        }

        @Override
        public void onStop() {
            super.onStop();
            // Unbind from the service
            if (mBound) {
                this.getActivity().unbindService(mConnection);
                mBound = false;
            }
        }
    }

}
