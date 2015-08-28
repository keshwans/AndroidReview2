package nyc.c4q.review2.models.pearsonVersion1;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class JsonHelperVersion1a {
    public static RecipesResponse loadStaticJsonRaw(Context context, int jsonResource) {

        RecipesResponse recipesResponse = null;

        InputStream inputStream = context.getResources().openRawResource(jsonResource);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while (true) {
            try {
                line = reader.readLine();

                if (line == null) {
                    break;
                }
                stringBuilder.append(line + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String jsonString = stringBuilder.toString();
        try {
            JSONObject topLevelObject = new JSONObject(jsonString);

            recipesResponse = new RecipesResponse();

            try {
                recipesResponse.setKind(topLevelObject.getString("kind"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                recipesResponse.setCount(topLevelObject.getInt("count"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                recipesResponse.setLimit(topLevelObject.getInt("limit"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                recipesResponse.setOffset(topLevelObject.getInt("offset"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                recipesResponse.setUrl(topLevelObject.getString("url"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                recipesResponse.setTotal(topLevelObject.getInt("total"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                JSONArray resultsJsonArray = topLevelObject.getJSONArray("results");
                List<Result> results = recipesResponse.getResults();

                for (int index = 0; index < resultsJsonArray.length(); index++) {

                    try {
                        JSONObject resultJson = resultsJsonArray.getJSONObject(index);
                        Result result = new Result();
                        results.add(result);

                        try {
                            result.setId(resultJson.getString("id"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            result.setName(resultJson.getString("name"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            result.setUrl(resultJson.getString("url"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            result.setCuisine(resultJson.getString("cuisine"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            result.setCookingMethod(resultJson.getString("cooking_method"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            result.setImage(resultJson.getString("image"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            result.setThumb(resultJson.getString("thumb"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        List<String> ingredientsList = result.getIngredients();
                        JSONArray ingredientsJsonArray = resultJson.getJSONArray("ingredients");

                        for (int ingIndex = 0; ingIndex < ingredientsJsonArray.length(); ingIndex++) {
                            try {
                                String ingredient = ingredientsJsonArray.getString(ingIndex);
                                ingredientsList.add(ingredient);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return recipesResponse;
    }
}

/*
            try {
                recipesResponse.setKind(topLevelObject.getString("kind"));
            } catch (JSONException e) {
                e.printStackTrace();
            }


                        try {
                            result.setImage(resultJson.getString("image"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            result.setThumb(resultJson.getString("thumb"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
      */