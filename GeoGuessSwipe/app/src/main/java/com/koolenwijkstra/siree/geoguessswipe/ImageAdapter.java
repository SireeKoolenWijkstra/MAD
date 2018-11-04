package com.koolenwijkstra.siree.geoguessswipe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    //bron van alle data die gebind moeten worden gaan naar de Viewholders
    ArrayList <Plaatje> fotoOverzicht;

    public ImageAdapter(ArrayList<Plaatje> fotoOverzicht){
        this.fotoOverzicht = fotoOverzicht;
    }

    /**
     * @param i de index van de plaats in de recyclerview (als er maar 3 plaatsen zijn, dan is dit 0,1 of 2)
     * @return Viewholder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //Inflate maakt van je XML een javaobject, je moet dus een verwijzing hebben naar waar je XML staat
        View view = inflater.inflate(R.layout.imagemakeup, null);

        // Return a new holder instance
        ImageAdapter.ViewHolder viewHolder = new ImageAdapter.ViewHolder(view);
        return viewHolder;
    }

    /**
     * @param i index van de verzameling data (hier genoemd fotoOverzicht)
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.image.setImageResource(fotoOverzicht.get(i).getFoto());
    }

    @Override
    public int getItemCount() {
        return fotoOverzicht.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;

        //constructor
        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.plaatjes);
        }


    }
}
