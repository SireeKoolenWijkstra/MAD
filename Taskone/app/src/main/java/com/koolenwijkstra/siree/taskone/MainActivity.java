package com.koolenwijkstra.siree.taskone;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecipeList recipeList;
    private RecipePageAdapter mRecipePageAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestData();

    }

    public void setRecipeList(RecipeList recipeList) {

        //Create adapter that will give a fragment (one fragment per page)
        mRecipePageAdapter = new RecipePageAdapter(getSupportFragmentManager(), recipeList);

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mRecipePageAdapter);

    }

    private void requestData()
    {
        RecipeApiService service = RecipeApiService.retrofit.create(RecipeApiService.class);
        /**
         * Make an a-synchronous call by enqueing and definition of callbacks.
         * Call<RecipeList> getRecipeList();
         */

        Call<RecipeList> call = service.getRecipeList();
        call.enqueue(new Callback<RecipeList>() {

            @Override
            public void onResponse(Call<RecipeList> call, Response<RecipeList> response) {
                RecipeList recipeList = response.body();
                setRecipeList(recipeList);
            }


            @Override
            public void onFailure(Call<RecipeList> call, Throwable t) {
                Log.d("error", t.toString());

            }
        });
    }
}
