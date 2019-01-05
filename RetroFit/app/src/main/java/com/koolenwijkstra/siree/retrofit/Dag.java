package com.koolenwijkstra.siree.retrofit;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Dag implements Parcelable {
    
    private Date date;
    private ArrayList<FoodItemWeight> foodItemWeights;

    public Dag(Date date, ArrayList<FoodItemWeight> foodItemWeights) {
        this.date = date;
        this.foodItemWeights = foodItemWeights;
    }

    protected Dag(Parcel in) {
        // Read Long value and convert to date
        date = new Date(in.readLong());
        foodItemWeights = in.createTypedArrayList(FoodItemWeight.CREATOR);
    }

    public static final Creator<Dag> CREATOR = new Creator<Dag>() {
        @Override
        public Dag createFromParcel(Parcel in) {
            return new Dag(in);
        }

        @Override
        public Dag[] newArray(int size) {
            return new Dag[size];
        }
    };

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<FoodItemWeight> getFoodItemWeights() {
        return foodItemWeights;
    }

    public void setFoodItemWeights(ArrayList<FoodItemWeight> foodItemWeights) {
        this.foodItemWeights = foodItemWeights;
    }
    
    public int getCalories(){
        int totalCalories = 0;
        for (FoodItemWeight food:foodItemWeights) {
             totalCalories = totalCalories + food.getCalories();
        }
        return totalCalories;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // Write long value of Date
        dest.writeLong(date.getTime());
        dest.writeTypedList(foodItemWeights);
    }
}
