package com.koolenwijkstra.siree.retrofit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DagInput extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final ArrayList<FoodItemWeight> foodItemWeights = new ArrayList<FoodItemWeight>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.daginput);
        // Er wordt een nieuwe dag aangemaakt
        //bind de xml met het RecyclerView object in de code
        final RecyclerView mItemRecycler = (RecyclerView) findViewById(R.id.oneFoodItemRecyclerView);

        //bepaal de layoutmanager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mItemRecycler.setLayoutManager(layoutManager);
        mItemRecycler.setHasFixedSize(true);

        final FoodItemWeightAdapter adapter = new FoodItemWeightAdapter(foodItemWeights);
        mItemRecycler.setAdapter(adapter);
        adapter.addEmptyLine();

        Button upload = findViewById(R.id.button);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText date = (EditText) findViewById(R.id.setDate);

                try {
                    Date realDate = Date.valueOf(date.getText().toString());

                    Dag dag = new Dag(realDate, foodItemWeights);

                    Intent intent = new Intent();
                    intent.putExtra("dag", dag);

                    setResult(RESULT_OK, intent);
                    //Go back to the previous activity
                    finish();
                } catch (IllegalArgumentException e) {

                    Context context = getApplicationContext();
                    CharSequence text = "Please enter date as yyyy-mm-dd";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }
}
