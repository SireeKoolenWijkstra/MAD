package com.koolenwijkstra.siree.bucketlist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.persistence.room.Entity;

import java.util.ArrayList;
import java.util.List;


public class BucketViewModel extends AndroidViewModel {
    private LiveData<List<Item>> overviewItems;
    private BucketRepository mBucketRepository;

    public BucketViewModel(Application application) {

        super(application);
        mBucketRepository = new BucketRepository(application);
        overviewItems = mBucketRepository.getAllItems();
    }

    public LiveData<List<Item>> getAllItems(){
        return overviewItems;
    }

    public void insert(Item item){
        mBucketRepository.insert(item);
    }

    public void setChecked(int i, boolean isChecked) {
        Item itemnewvalue = overviewItems.getValue().get(i);
        if (itemnewvalue.getChecked() == isChecked){
            return;
        }else {
            itemnewvalue.setChecked(isChecked);
            mBucketRepository.update(itemnewvalue);
        }
    }

    /**

        Item item1 = new Item("poo", "nogal", false);
        Item item2 = new Item("Yo", "Kost wat", true);

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

    public void setChecked(int i, boolean isChecked) {
        overviewItems.get(i).getValue().setChecked(isChecked);
    }
         **/
}
