package com.koolenwijkstra.siree.taskone;

import android.support.constraint.Placeholder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class RecipePageAdapter extends FragmentPagerAdapter {

    RecipeList recipeList;

    public RecipePageAdapter(FragmentManager fm, RecipeList recipeList) {
        super(fm);
        this.recipeList = recipeList;
    }

    @Override
    public Fragment getItem(int position) {
        return RecipeDetail.newInstance(recipeList.getRecipes().get(position));
    }

    @Override
    public int getCount() {
        return recipeList.getRecipes().size();
    }
}
