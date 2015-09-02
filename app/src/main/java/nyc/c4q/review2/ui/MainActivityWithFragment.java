package nyc.c4q.review2.ui;

import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import nyc.c4q.review2.R;

public class MainActivityWithFragment extends AppCompatActivity {

    public static final String INTENT_EXTRA_ALARM_KEY = "nyc.c4q.review2.alarm";
    BroadcastReceiver mReciever;

    private static final String TAG = MainActivityWithFragment.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //1)  add the actvity's own layout
        setContentView(R.layout.activity_main_with_fragment);



        // 2) now add the child - fragment,
        // we have two choice:
        //      long format where we do each step at a time
        //      terse format where we use builder pattern to do the same steps

        //long version
        if (savedInstanceState == null) {
            Fragment fragment = new PlaceholderFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft = ft.add(R.id.container, fragment);
            ft.commit();
        }

        // terse version using builder pattern
        /*
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        */

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
        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {

        // the minimum override needed to visually show a layout of the fragment
        // a) inflate the layout for the fragment into a view
        // b) return the inflated view

        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {

            View fragmentView = inflater.inflate(R.layout.fragment_main, container, false);


            return fragmentView;
        }


    }
}

