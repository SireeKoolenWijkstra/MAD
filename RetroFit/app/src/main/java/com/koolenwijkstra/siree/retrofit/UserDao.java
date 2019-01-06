package com.koolenwijkstra.siree.retrofit;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user_table")
    public LiveData<User> getUser();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User user);

    @Update
    public void updateUser(User user);

    @Query("DELETE FROM user_table")
    public void deleteAll();


}