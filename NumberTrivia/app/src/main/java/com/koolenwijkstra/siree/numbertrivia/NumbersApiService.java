package com.koolenwijkstra.siree.numbertrivia;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NumbersApiService {
    String BASE_URL = "http://numbersapi.com/";

    /**
     * Create a retrofit client.
     */

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    @GET("/random/trivia?json")
    Call<TriviaItem> getTrivia();

}
