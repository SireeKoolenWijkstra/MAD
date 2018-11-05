package com.koolenwijkstra.siree.gamebacklog;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.ArrayList;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    private final Context context;
    ArrayList<Game> gameOverzicht;

    public GameAdapter(ArrayList<Game> gameOverzicht, Context context){
        this.gameOverzicht = gameOverzicht;
        this.context = context;
    }


    @NonNull
    @Override
    public GameAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //Inflate maakt van je XML een javaobject, je moet dus een verwijzing hebben naar waar je XML staat
        View view = inflater.inflate(R.layout.looksgame, null);

        // Return a new holder instance
        GameAdapter.ViewHolder viewHolder = new GameAdapter.ViewHolder(view);
        return viewHolder;
    }

    /**
     * @param i index van de verzameling data (hier genoemd gameOverzicht)
     */
    @Override
    public void onBindViewHolder(@NonNull GameAdapter.ViewHolder viewHolder, int i) {
        viewHolder.naam.setText(gameOverzicht.get(i).naam);
        viewHolder.platform.setText(gameOverzicht.get(i).platform);
        Resources res = context.getResources();
        String[] planets = res.getStringArray(R.array.planets_array);
        viewHolder.status.setText(planets[gameOverzicht.get(i).status]);

        //viewHolder.notes.setText(gameOverzicht.get(i).notes);
    }

    @Override
    public int getItemCount() {
        return gameOverzicht.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView naam;
        TextView platform;
        TextView status;
        //TextView notes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            naam = (TextView) itemView.findViewById(R.id.naam);
            platform = (TextView) itemView.findViewById(R.id.game_platform);
            //notes = (TextView) itemView.findViewById(R.id.notes);
            status = (TextView) itemView.findViewById(R.id.game_status);
        }
    }
}
