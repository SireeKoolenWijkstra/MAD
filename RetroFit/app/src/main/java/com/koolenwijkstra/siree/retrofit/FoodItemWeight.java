package com.koolenwijkstra.siree.retrofit;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Property;

public class FoodItemWeight implements Parcelable {

    private FoodItem fooditem;
    //gaat in grammen
    private int weight;

    public FoodItemWeight(FoodItem fooditem, int weight) {
        this.fooditem = fooditem;
        this.weight = weight;
    }

    protected FoodItemWeight(Parcel in) {
        fooditem = in.readParcelable(FoodItem.class.getClassLoader());
        weight = in.readInt();
    }

    public static final Creator<FoodItemWeight> CREATOR = new Creator<FoodItemWeight>() {
        @Override
        public FoodItemWeight createFromParcel(Parcel in) {
            return new FoodItemWeight(in);
        }

        @Override
        public FoodItemWeight[] newArray(int size) {
            return new FoodItemWeight[size];
        }
    };

    public FoodItem getFooditem() {
        return fooditem;
    }

    public void setFooditem(FoodItem fooditem) {
        this.fooditem = fooditem;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * methode rekent de calorien per gegeten hoeveelheid grammen uit. Omdat de calDensity per 100 gram gaat
     * moet dit gedeeld worden door 100.
     * @return calorieen in totaal van hoeveelheid gegeten fooditem in grammen
     */
    public int getCalories() {
        return (fooditem.getCalDensity() * weight)/100;
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
        dest.writeParcelable(fooditem, flags);
        dest.writeInt(weight);
    }
}
