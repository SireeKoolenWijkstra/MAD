package com.koolenwijkstra.siree.retrofit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.List;

public class FoodItemWeightAdapter extends RecyclerView.Adapter<FoodItemWeightAdapter.ViewHolder> {

    List<FoodItemWeight> foodItemWeights;

    //constructor
    public FoodItemWeightAdapter(List<FoodItemWeight> foodItemWeights) {
        this.foodItemWeights = foodItemWeights;
    }

    @NonNull
    @Override
    public FoodItemWeightAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //Inflate maakt van je XML een javaobject, je moet dus een verwijzing hebben naar waar je XML staat
        View view = inflater.inflate(R.layout.onefooditem, null);
        FoodItemWeightAdapter.ViewHolder viewHolder = new FoodItemWeightAdapter.ViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final FoodItemWeightAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.setFoodItem.setText((foodItemWeights.get(i).getFooditem().getName()));
        viewHolder.setgram.setText(Integer.toString(foodItemWeights.get(i).getWeight()));
        viewHolder.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmptyLine();
            }
        });
        viewHolder.setFoodItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            /**
             * calDensity is -1 want dit is een bogus getal, calDensity staat vast in de database. User hoeft die
             * niet te weten en ook niet in tevullen. Is alleen nodig voor constructor FoodItem
             * @param s is string in editview
             */
            @Override
            public void afterTextChanged(Editable s) {
                foodItemWeights.get(i).setFooditem(new FoodItem(s.toString(), -1));
            }
        });

        viewHolder.setgram.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            /**
             * Bij compleet weghalen van de weight komt er een discrepantie tusssen de gui en de database.
             * De waarde van de weight wordt opgehaald uit de database en zal 1 zijn (want laatste nog niet weggehaalde
             * cijfer van 100)
             * @param s waarde van edittext
             */
            @Override
            public void afterTextChanged(Editable s) {
                try {
                    foodItemWeights.get(i).setWeight(Integer.parseInt(s.toString()));
                } catch (NumberFormatException e) {
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return foodItemWeights.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView setFoodItem;
        public TextView setgram;
        public FloatingActionButton fab;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            setFoodItem = itemView.findViewById(R.id.setFoodItem);
            setgram = itemView.findViewById(R.id.setgram);
            fab = itemView.findViewById(R.id.floatingActionButton2);
        }
    }

    public void addEmptyLine() {
        foodItemWeights.add(new FoodItemWeight(new FoodItem("Put name food here", 100), 100));
        notifyDataSetChanged();
    }
}

