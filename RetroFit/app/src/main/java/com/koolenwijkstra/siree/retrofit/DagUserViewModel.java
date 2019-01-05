package com.koolenwijkstra.siree.retrofit;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DagUserViewModel extends ViewModel {
    private List<LiveData<Dag>> overviewDagen;
    private MutableLiveData<User> userLiveData;

    public DagUserViewModel() {
        ArrayList<FoodItemWeight> fiws1 = new ArrayList<>();
        ArrayList<FoodItemWeight> fiws2 = new ArrayList<>();

        fiws1.add(new FoodItemWeight(new FoodItem("appel", 100),20));
        fiws2.add(new FoodItemWeight(new FoodItem("appel", 100),20));
        fiws2.add(new FoodItemWeight(new FoodItem("biefstuk", 200), 40));

        Dag item1 = new Dag(new Date(), fiws1);
        Dag item2 = new Dag(new Date(), fiws2);

        overviewDagen = new ArrayList<LiveData<Dag>>();
        add(item1);
        add(item2);

        User me = new User(30, 84, 174);
        userLiveData = new MutableLiveData<User>();
        userLiveData.setValue(me);
    }


    public List<LiveData<Dag>> getOverviewDagen() {
        return overviewDagen;
    }

    public void setOverviewDagen(List<LiveData<Dag>> overviewDagen) {
        this.overviewDagen = overviewDagen;
    }

    public MutableLiveData<User> getUserLiveData() {
        return userLiveData;
    }

    public void setUserLiveData(MutableLiveData<User> userLiveData) {
        this.userLiveData = userLiveData;
    }

    public void add(Dag dag){
        MutableLiveData mld= new MutableLiveData<Dag>();
        mld.setValue(dag);
        overviewDagen.add(mld);
    }
}


