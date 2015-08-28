package nyc.c4q.review2.helpers.version1;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import nyc.c4q.review2.models.pearsonVersion2.RecipesResponse;

public class JsonHelper {
    public static RecipesResponse loadStaticJsonRaw(int jsonResource, Context context) {
        InputStream raw = context.getResources().openRawResource(jsonResource);
        Reader rd = new BufferedReader(new InputStreamReader(raw));
        Gson gson = new Gson();
        RecipesResponse recipesResponse = gson.fromJson(rd, RecipesResponse.class);
        return recipesResponse;
    }

    public static RecipesResponse loadStaticJsonAsset(String jsonFilename, Context context) throws IOException {
        InputStream raw = context.getAssets().open(jsonFilename);
        Reader rd = new BufferedReader(new InputStreamReader(raw));
        Gson gson = new Gson();
        RecipesResponse recipesResponse = gson.fromJson(rd, RecipesResponse.class);
        return recipesResponse;
    }
}
