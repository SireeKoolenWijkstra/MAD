package com.koolenwijkstra.siree.bucketlist;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxAdapter extends RecyclerView.Adapter<CheckBoxAdapter.ViewHolder> {
    BucketViewModel bucketViewModel;

    public CheckBoxAdapter(BucketViewModel bucketViewModel){this.bucketViewModel = bucketViewModel;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //Inflate maakt van je XML een javaobject, je moet dus een verwijzing hebben naar waar je XML staat
        View view = inflater.inflate(R.layout.item, null);

        CheckBoxAdapter.ViewHolder viewHolder = new CheckBoxAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.title.setText(bucketViewModel.getOverviewItems().get(i).getValue().getTitle());
        viewHolder.description.setText(bucketViewModel.getOverviewItems().get(i).getValue().getDescription());
        viewHolder.checkBox.setChecked(bucketViewModel.getOverviewItems().get(i).getValue().getChecked());
    }

    @Override
    public int getItemCount() {
        return bucketViewModel.getOverviewItems().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;
        public CheckBox checkBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView3);
            description = itemView.findViewById(R.id.textView4);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}
