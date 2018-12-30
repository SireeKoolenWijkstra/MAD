package com.koolenwijkstra.siree.taskone;

import android.widget.ImageView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipeItem {
    @SerializedName("image_url")
    @Expose
    private String image_url;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("recipe_id")
    @Expose
    private String id;

    @SerializedName("ingredients")
    @Expose
    private List<String> ingredient;

    public String getImage() {
        return image_url;
    }
    public void setImage(String image) {
        this.image_url = image;
    }

    public String getNameRecipe() {
        return title;
    }
    public void setNameRecipe(String nameRecipe) {
        this.title = nameRecipe;
    }

    public List<String> getIngredient() {
        return ingredient;
    }
    public void setIngredient(List <String> ingredients) {
        this.ingredient = ingredients;
    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
}
