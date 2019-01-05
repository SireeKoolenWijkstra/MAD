package com.koolenwijkstra.siree.retrofit;

import android.media.Image;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

public class User {

    private int age;
    private int weight;
    private int length;

    public User(int age, int weight, int length) {
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
