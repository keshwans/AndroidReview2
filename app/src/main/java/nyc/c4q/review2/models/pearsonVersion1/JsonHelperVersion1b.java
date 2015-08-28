package nyc.c4q.review2.models.pearsonVersion1;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class JsonHelperVersion1b {
    public static RecipesResponse loadStaticJsonRawUsingGson(Context context, int jsonResource) {

        InputStream raw = context.getResources().openRawResource(jsonResource);
        Reader rd = new BufferedReader(new InputStreamReader(raw));
        Gson gson = new Gson();
        RecipesResponse recipesResponse = gson.fromJson(rd, RecipesResponse.class);
        return recipesResponse;
    }


}
