package com.koolenwijkstra.siree.taskone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeWrapper {

    @SerializedName("recipe")
    @Expose
    public RecipeItem recipeItem;

    public RecipeItem getRecipeItem() {
        return recipeItem;
    }

    public void setRecipeItem(RecipeItem recipeItem) {
        this.recipeItem = recipeItem;
    }
}
