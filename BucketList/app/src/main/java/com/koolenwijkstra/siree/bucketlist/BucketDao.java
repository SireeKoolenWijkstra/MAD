package com.koolenwijkstra.siree.bucketlist;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface BucketDao {

    @Query("SELECT * FROM item")

    public LiveData<List<Item>> getAllItems();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertItems (Item item);

    @Update
    public void updateItems (Item item);

    @Query("DELETE FROM item")
    public void deleteAll();
}
