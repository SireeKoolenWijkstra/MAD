package com.koolenwijkstra.siree.taskone;

import android.widget.ImageView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeItem {
    @SerializedName("image_url")
    @Expose
    private String image_url;

    @SerializedName("title")
    @Expose
    private String title;

    //geeft url waar de ingredienten inztten
    @SerializedName("f2f_url")
    @Expose
    private String f2f_url;

    public RecipeItem(String image, String nameRecipe) {
        this.image_url = image;
        this.title = nameRecipe;
    }

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

    public String getIngredients() {
        return f2f_url;
    }

    public void setIngredients(String ingredients) {
        this.f2f_url = ingredients;
    }
}
