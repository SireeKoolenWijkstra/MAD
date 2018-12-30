package com.koolenwijkstra.siree.taskone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipeList {

    @SerializedName("recipes")
    @Expose
    private List<RecipeItem> recipes;

    public List<RecipeItem> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeItem> recipes) {
        this.recipes = recipes;
    }

}
