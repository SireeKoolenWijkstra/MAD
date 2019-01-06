package com.koolenwijkstra.siree.retrofit;

import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DagApiService {
    String BASE_URL = "https://retrofit-queenbe.firebaseio.com";

    /**
     * Create a retrofit client
     */

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd").create()))
            .build();

    @GET("/dagen.json")
    Call<List<Dag>> getDagen();

    @PUT("/dagen.json")
    Call<List<Dag>> setDagen(@Body List<Dag> dag);

}


