package com.koolenwijkstra.siree.retrofit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;

public class DagAdapter extends RecyclerView.Adapter<DagAdapter.ViewHolder> {
    DagUserViewModel dagUserViewModel;

    //constructor
    public DagAdapter(DagUserViewModel dagUserViewModel) {
        this.dagUserViewModel = dagUserViewModel;
    }

    @NonNull
    @Override
    public DagAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //Inflate maakt van je XML een javaobject, je moet dus een verwijzing hebben naar waar je XML staat
        View view = inflater.inflate(R.layout.dag, null);
        DagAdapter.ViewHolder viewHolder = new DagAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DagAdapter.ViewHolder viewHolder, int i) {
        viewHolder.date.setText(DateFormat.getDateInstance().format(dagUserViewModel.getOverviewDagen().get(i).getValue().getDate()));
        viewHolder.eatenCal.setText(Integer.toString(dagUserViewModel.getOverviewDagen().get(i).getValue().getCalories()));
        viewHolder.neededCal.setText(Integer.toString(dagUserViewModel.getUserLiveData().getValue().neededCalories()));

    }

    @Override
    public int getItemCount() {
        return dagUserViewModel.getOverviewDagen().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView date;
        public TextView eatenCal;
        public TextView neededCal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            eatenCal = itemView.findViewById(R.id.eatenCal);
            neededCal = itemView.findViewById(R.id.neededCal);
        }
    }
}
