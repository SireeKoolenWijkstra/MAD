package com.koolenwijkstra.siree.taskone;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface RecipeApiService {
    String BASE_URL = "https://www.food2fork.com";

    /**
     * Create a retrofit client.
     */

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    @GET("/api/search?key=265f39470019fca0a3c859d1f2aa0bad&sort=r&count=3")
    Call<RecipeList> getRecipeList();

}
