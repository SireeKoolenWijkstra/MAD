package com.koolenwijkstra.siree.retrofit;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

@Entity(tableName = "user_table")
public class User {



    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "age")
    private int age;

    @NonNull
    @ColumnInfo(name = "weight")
    private int weight;

    @NonNull
    @ColumnInfo(name = "length")
    private int length;

    public User(@NonNull int age, @NonNull int weight, @NonNull int length) {
        this.age = age;
        this.weight = weight;
        this.length = length;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int neededCalories(){
        int bmr = (int) ((10.0 * weight + 6.25 * length - 5.0 * age) * 1.4);

        return bmr;
    }
}
