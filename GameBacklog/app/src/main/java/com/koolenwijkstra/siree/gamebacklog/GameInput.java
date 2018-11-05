package com.koolenwijkstra.siree.gamebacklog;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class GameInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameinput);

        Spinner spinner = (Spinner) findViewById(R.id.inputStatus);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        FloatingActionButton fabUpload = findViewById(R.id.uploadNewGame);
        fabUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText naam = (EditText) findViewById(R.id.naam);
                EditText platform = (EditText) findViewById(R.id.platform);
                EditText notes = (EditText) findViewById(R.id.notes);
                Spinner status = (Spinner) findViewById(R.id.inputStatus);

                Intent intent = new Intent();
                intent.putExtra("naam", naam.getText().toString());
                intent.putExtra("platform", platform.getText().toString());
                intent.putExtra("notes", notes.getText().toString());
                intent.putExtra("status", status.getSelectedItemPosition());

                setResult(RESULT_OK, intent);
                //Go back to the previous activity
                finish();
            }
        });
    }
}
