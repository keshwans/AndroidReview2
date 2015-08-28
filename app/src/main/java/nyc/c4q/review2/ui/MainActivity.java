package nyc.c4q.review2.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import nyc.c4q.review2.R;
import nyc.c4q.review2.models.pearsonVersion1.JsonHelperVersion1a;
import nyc.c4q.review2.models.pearsonVersion1.JsonHelperVersion1b;
import nyc.c4q.review2.models.pearsonVersion1.RecipesResponse;
import nyc.c4q.review2.models.pearsonVersion2.JsonHelperVersion2;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecipesResponse recipesResponse1 =
                JsonHelperVersion1a.loadStaticJsonRaw(this, R.raw.pearson_recipes_chicken);
        Log.d(TAG, "recipesResponse1:\n" + recipesResponse1);

        RecipesResponse recipesResponse2 = JsonHelperVersion1b.loadStaticJsonRawUsingGson(this, R.raw.pearson_recipes_chicken);
        Log.d(TAG, "recipesResponse2:\n" + recipesResponse2);

        nyc.c4q.review2.models.pearsonVersion2.RecipesResponse recipesResponse3 = JsonHelperVersion2.loadStaticJsonRawUsingGson(this, R.raw.pearson_recipes_chicken);
        Log.d(TAG, "recipesResponse3:\n" + recipesResponse3);

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
}
