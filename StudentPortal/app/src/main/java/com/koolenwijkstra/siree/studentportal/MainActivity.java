package com.koolenwijkstra.siree.studentportal;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final int SECOND_ACTIVITY_REQUEST_CODE = 66;

    private ArrayAdapter<URLView> mAdapter;

    GridView mGridView;
    List<URLView> mURLView = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridView = (GridView) findViewById(R.id.gridView);

        FloatingActionButton fabAdd = findViewById(R.id.addPortal);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddPortal.class);
                startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
            }
        });

        mAdapter = new ArrayAdapter<URLView>(this, android.R.layout.simple_list_item_1,mURLView);
        mGridView.setAdapter(mAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, GateToPortal.class);
                intent.putExtra("url",mURLView.get(position).getUrl().toString());
                startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);

            }
        });

        addURLAndTitle("https://www.google.com", "");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                // get String data from Intent
                String returnUrl = data.getStringExtra("url");
                String returnTitle = data.getStringExtra("title");
                Log.v("Mainactivity", "waarde van returnString is  " + returnUrl + " " + returnTitle);
                addURLAndTitle(returnUrl, returnTitle);
            }
        }
    }

    public void addURLAndTitle(String url, String title) {
        mURLView.add(new URLView(url, title));
        mAdapter.notifyDataSetChanged();
    }



}
