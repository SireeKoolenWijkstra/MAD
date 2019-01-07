package com.koolenwijkstra.siree.retrofit;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class DagFragment extends Fragment {
    private static final int NEW_FOODITEM_ACTIVITY_REQUEST_CODE = 1;

    DagAdapter dagAdapter;

    private DagUserViewModel viewModel;

    public static DagFragment newInstance() {
        return new DagFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dag_fragment, container, false);

        //bind de xml met het RecyclerView object in de code
        final RecyclerView mItemRecycler = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        //bepaal de layoutmanager van de main_activity
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        mItemRecycler.setLayoutManager(layoutManager);
        mItemRecycler.setHasFixedSize(true);


        viewModel = ViewModelProviders.of(this.getActivity()).get(DagUserViewModel.class);

        //dagAdapter voor activity_main
        dagAdapter = new DagAdapter(viewModel);
        mItemRecycler.setAdapter(dagAdapter);

        FloatingActionButton fabAdd = rootView.findViewById(R.id.floatingActionButton);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DagInput.class);
                startActivityForResult(intent, NEW_FOODITEM_ACTIVITY_REQUEST_CODE);
            }
        });

        //voor als de lijst met dagen is veranderd.
        viewModel.getOverviewDagen().observe(this, new Observer<List<Dag>>() {
            /**
             * Called when the data is changed.
             *
             * @param dags The new data
             */
            @Override
            public void onChanged(@Nullable List<Dag> dags) {

                dagAdapter.notifyDataSetChanged();
            }
        });

        //als de user is veranderd
        viewModel.getUser().observe(this, new Observer<User>() {
            /**
             * Called when the data is changed.
             *
             * @param user The new data
             */
            @Override
            public void onChanged(@Nullable User user) {

                dagAdapter.notifyDataSetChanged();
            }
        });

        return rootView;

    }

    /**
     * Wanneer er op de button wordt geklikt, wordt een nieuw activity gestart. Je wordt doorgezet
     * naar een DagInput waarna een Dag wordt geretourneerd.
     *
     * @param requestCode is of NEW_FOODITEM_ACTIVITY_REQUEST_CODE
     * @param resultCode  wordt gegenereerd door de app
     * @param data        instantie van Intent
     */

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_FOODITEM_ACTIVITY_REQUEST_CODE) {
            if (resultCode == AppCompatActivity.RESULT_OK) {

                viewModel.add(data.<Dag>getParcelableExtra("dag"));
                dagAdapter.notifyDataSetChanged();
            }
        }
    }

}
