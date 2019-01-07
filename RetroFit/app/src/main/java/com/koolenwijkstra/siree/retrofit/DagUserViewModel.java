package com.koolenwijkstra.siree.retrofit;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DagUserViewModel extends AndroidViewModel {

    private RetroFitRepository mRetroFitRepository;

    private MutableLiveData<List<Dag>> overviewDagen = new MutableLiveData<List<Dag>>();
    private LiveData<User> userLiveData;

    public DagUserViewModel(Application application) {
        super(application);
        mRetroFitRepository = new RetroFitRepository(application);
        userLiveData = mRetroFitRepository.getUser();
        overviewDagen.setValue(new ArrayList<Dag>());
        requestData();
    }

    LiveData<User> getUser() {
        return userLiveData;
    }

    public void setUser(User user) {
        mRetroFitRepository.update(user);
    }

    public LiveData<List<Dag>> getOverviewDagen() {
        return overviewDagen;
    }

    public void setUser(MutableLiveData<User> userLiveData) {
        this.userLiveData = userLiveData;
    }

    public void add(Dag dag) {
        overviewDagen.getValue().add(dag);
        overviewDagen.setValue(overviewDagen.getValue());
        writetodb();
    }

    DagApiService service = DagApiService.retrofit.create(DagApiService.class);

    private void requestData() {
        /**
         * Make an a-synchronous call by enqueing and definition of callbacks.
         * Call<Dag> getDagen();
         */
        Call<List<Dag>> call = service.getDagen();

        call.enqueue(new Callback<List<Dag>>() {
            @Override
            public void onResponse(Call<List<Dag>> call, Response<List<Dag>> response) {
                List<Dag> dagen = response.body();
                //Log.v("Lijst van Dag", dagen.toString());
                overviewDagen.setValue(dagen);
            }

            @Override
            public void onFailure(Call<List<Dag>> call, Throwable t) {
                Log.v("requestData niet goed", t.toString());
            }
        });


    }

    public void initFakeDB() {
        ArrayList<Dag> dagen = new ArrayList<Dag>();

        ArrayList<FoodItemWeight> foodItemWeights = new ArrayList<>();
        foodItemWeights.add(new FoodItemWeight(new FoodItem("appel", 40), 80));
        foodItemWeights.add(new FoodItemWeight(new FoodItem("rozijnen", 90), 30));

        dagen.add(new Dag(Date.valueOf("2019-01-01"), foodItemWeights));

        ArrayList<FoodItemWeight> foodItemWeights2 = new ArrayList<>();
        foodItemWeights2.add(new FoodItemWeight(new FoodItem("appel", 40), 80));
        foodItemWeights2.add(new FoodItemWeight(new FoodItem("rozijnen", 90), 30));
        foodItemWeights2.add(new FoodItemWeight(new FoodItem("biefstuk", 68), 80));

        dagen.add(new Dag(Date.valueOf("2019-01-02"), foodItemWeights2));


    }

    public void writetodb() {

        Call<List<Dag>> call1 = service.setDagen(overviewDagen.getValue());
        call1.enqueue(new Callback<List<Dag>>() {
            @Override
            public void onResponse(Call<List<Dag>> call, Response<List<Dag>> response) {
                Log.v("lijst van dagen ", "Success " + response.body());
            }

            @Override
            public void onFailure(Call<List<Dag>> call, Throwable t) {
                Log.v("lijst van dagen ", "So Sad " + t);
                throw new Error(t);
            }
        });
    }


}


