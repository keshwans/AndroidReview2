package nyc.c4q.review2.models.pearsonVersion3;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class JsonHelperVersion3 {
    public static RecipesResponse loadStaticJsonRawUsingGson(Context context, int jsonResource) {

        InputStream raw = context.getResources().openRawResource(jsonResource);
        Reader rd = new BufferedReader(new InputStreamReader(raw));

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        RecipesResponse recipesResponse = gson.fromJson(rd, RecipesResponse.class);
        return recipesResponse;
    }

    public static String convertToJsonUsingGson(RecipesResponse recipesResponse) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json =  gson.toJson(recipesResponse);
        return json;
    }

}
