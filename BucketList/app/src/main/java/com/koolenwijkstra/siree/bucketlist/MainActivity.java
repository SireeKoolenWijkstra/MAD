package com.koolenwijkstra.siree.bucketlist;

import android.arch.lifecycle.LiveData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BucketViewModel viewModel = new BucketViewModel();

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

        final CheckBoxAdapter adapter = new CheckBoxAdapter(viewModel);
        mItemRecycler.setAdapter(adapter);

        /**
        for(LiveData<Item> item:viewModel.getOverviewItems()){
            item.observe(this, bucketitem->{
                //TODO update UI


            });
        }**/

    }


}
