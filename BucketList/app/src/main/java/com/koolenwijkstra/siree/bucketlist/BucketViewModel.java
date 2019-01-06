package com.koolenwijkstra.siree.bucketlist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class BucketViewModel extends ViewModel {
    private List<LiveData<Item>> overviewItems;

    public BucketViewModel() {

        Item item1 = new Item("poo", "nogal", false);
        Item item2 = new Item("Yo", "Kost wat", false);

        overviewItems = new ArrayList<LiveData<Item>>();
        final MutableLiveData mld= new MutableLiveData<Item>();
        final MutableLiveData mld2= new MutableLiveData<Item>();
        mld.setValue(item1);
        overviewItems.add(mld);
        mld2.setValue(item2);
        overviewItems.add(mld2);
        }

    public List<LiveData<Item>> getOverviewItems() {
        return overviewItems;
    }

    public void setOverviewItems(List<LiveData<Item>> overviewItems) {
        this.overviewItems = overviewItems;
    }

}
