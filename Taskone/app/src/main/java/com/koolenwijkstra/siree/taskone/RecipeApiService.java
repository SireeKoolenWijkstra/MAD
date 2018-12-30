package com.koolenwijkstra.siree.taskone;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RecipeApiService {
    String BASE_URL = "https://www.food2fork.com";

    /**
     * Create a retrofit client.
     */

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    @GET("/api/search?key=33f91fb4664bf96295360fe254d4abca&sort=r&count=3")
    Call<RecipeList> getRecipeList();

    @GET("/api/get?key=33f91fb4664bf96295360fe254d4abca")
    Call<RecipeWrapper> getRecipeWrapper(@Query("rId") String id);

    /*
    @GET("/{month}/{dayOfMonth}/date?json")
    /**
     * "DayQuoteTime" is the name of the helper class just defined, defining the datamodel, and given as argument.
     * "getTodaysQuote" is the name of the symbol get method. It can be chosen at wish, as long as it is invoked
     * with the same name.
     Call<DayQuoteItem> getTodaysQuote(@Path("month") int monthNumber, @Path("dayOfMonth") int dayOfMonth);
     */





}
