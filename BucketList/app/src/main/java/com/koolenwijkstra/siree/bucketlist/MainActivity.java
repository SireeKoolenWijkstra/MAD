package com.koolenwijkstra.siree.bucketlist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private BucketViewModel viewModel;
    public static final int NEW_ITEM_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind de xml met het RecyclerView object in de code
        final RecyclerView mItemRecycler = (RecyclerView) findViewById(R.id.recyclerView);

        //bepaal de layoutmanager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mItemRecycler.setLayoutManager(layoutManager);
        mItemRecycler.setHasFixedSize(true);

        //Get the BucketViewModel
        viewModel = ViewModelProviders.of(this).get(BucketViewModel.class);

        viewModel.getAllItems().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable List<Item> items) {
                final CheckBoxAdapter adapter = new CheckBoxAdapter(viewModel);
                mItemRecycler.setAdapter(adapter);
            }
        });

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ItemAdderActivity.class);
              startActivityForResult(intent, NEW_ITEM_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_ITEM_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Item item = data.<Item>getParcelableExtra(ItemAdderActivity.EXTRA_REPLY);
            viewModel.insert(item);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}

