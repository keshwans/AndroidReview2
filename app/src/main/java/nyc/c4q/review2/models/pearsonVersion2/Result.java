
package nyc.c4q.review2.models.pearsonVersion2;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Result {

    private String name;
    private String id;
    private String url;
    private String cuisine;

    @SerializedName("cooking_method")
    private String cookingMethod;
    private List<String> ingredients = new ArrayList<>();
    private String image;
    private String thumb;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getCookingMethod() {
        return cookingMethod;
    }

    public void setCookingMethod(String cookingMethod) {
        this.cookingMethod = cookingMethod;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    @Override
    public String toString() {
        return "Result[0]{\n" +
                "name='" + name + "'\n" +
                ", id='" + id + "'\n" +
                ", cuisine='" + cuisine + "'\n" +
                ", cookingMethod='" + cookingMethod + "'\n" +
                ", url='" + url + "'\n" +
                ", image='" + image + '\n' +
                ", thumb='" + thumb + "'\n\n" +
                '}';
    }
}
