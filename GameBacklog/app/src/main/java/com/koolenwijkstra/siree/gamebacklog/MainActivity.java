package com.koolenwijkstra.siree.gamebacklog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.support.design.widget.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GameAdapter.GameClickListener{
    // NEW_GAME_ACTIVITY_REQUEST_CODE wordt meegestuurd bij het klikken op de FAB
    // EDIT_GAME_ACTIVITY_REQUEST_CODE wordt meegestuurd bij onclick op de cardview

    private static final int NEW_GAME_ACTIVITY_REQUEST_CODE = 66;
    private static final int EDIT_GAME_ACTIVITY_REQUEST_CODE = 69;
    final ArrayList<Game> gameOverzicht = new ArrayList<>();
    GameAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind de xml met het RecyclerView object in de code
        final RecyclerView mCardObject = (RecyclerView) findViewById(R.id.alleGamesOverzicht);
        adapter = new GameAdapter(gameOverzicht, getApplicationContext(), this);
        mCardObject.setAdapter(adapter);

        /*
        Wanneer op de fab wordt geklikt, dan opent een nieuw activity (GameInput)

        */
        FloatingActionButton fabAdd = findViewById(R.id.voegToeGame);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GameInput.class);
                startActivityForResult(intent, NEW_GAME_ACTIVITY_REQUEST_CODE);
            }
        });


        //bepaal de layoutmanager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mCardObject.setLayoutManager(layoutManager);
        mCardObject.setHasFixedSize(true);

         /*
         Een touchhelper is nodig om te kunnen swipen. Het vraagt om een viewholder waar op geswiped wordt
         en een richting waarin geswiped wordt. Callbacks worden gebruikt om aan te geven dat een gebruiker
         aan het swipen is.
        */
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            // wordt niet in app gebruikt
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
                    target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                //position is de index van de viewholder die geswiped wordt
                int position = (viewHolder.getAdapterPosition());
                gameOverzicht.remove(position);
                adapter.notifyItemRemoved(position);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mCardObject);


    }

    /**
     * Wanneer er een activity wordt gestart, kan dit dmv de fab of dmv een onclick. Bij een fab, wordt de data die is ingevuld en
     * meegestuurd(zie GameInput.java) ontvangen en toegevoegd aan een nieuwe gemaakte Game.
     * Wanneer op een cardview wordt geklikt, dan wordt bij desbetreffende game de nieuwe waardes geset.
     * @param requestCode is of NEW_GAME_ACTIVITY_REQUEST_CODE of EDIT_GAME_ACTIVITY_REQUEST_CODE + index van viewholder
     * @param resultCode wordt gegenereerd door de app
     * @param data instantie van Intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_GAME_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                String returnNaam = data.getStringExtra("naam");
                String returnPlatform = data.getStringExtra("platform");
                String returnNotes = data.getStringExtra("notes");
                int returnStatus = data.getIntExtra("status", 0);
                gameOverzicht.add(new Game(returnNaam, returnPlatform, returnNotes, returnStatus));
                adapter.notifyDataSetChanged();
            }
        }else {
            if (resultCode == RESULT_OK) {

                // get String data from Intent
                String returnNaam = data.getStringExtra("naam");
                String returnPlatform = data.getStringExtra("platform");
                String returnNotes = data.getStringExtra("notes");
                int returnStatus = data.getIntExtra("status", 0);

                gameOverzicht.get(requestCode - EDIT_GAME_ACTIVITY_REQUEST_CODE).setNaam(returnNaam);
                gameOverzicht.get(requestCode - EDIT_GAME_ACTIVITY_REQUEST_CODE).setPlatform(returnPlatform);
                gameOverzicht.get(requestCode - EDIT_GAME_ACTIVITY_REQUEST_CODE).setNotes(returnNotes);
                gameOverzicht.get(requestCode - EDIT_GAME_ACTIVITY_REQUEST_CODE).setStatus(returnStatus);
                adapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * bij een klik wordt er een intent aangemaakt en het scherm van GameInput getoond. De waarden van
     * de gekozen game (op index i in de de arraylist van gameOverzicht) worden meegegeven.
     * @param i index van viewholder
     */
    @Override
    public void gameOnClick(int i) {
        Intent intent = new Intent(MainActivity.this, GameInput.class);
        Game gekozenGame = gameOverzicht.get(i);
        intent.putExtra("naam", gekozenGame.naam);
        intent.putExtra("platform", gekozenGame.platform);
        intent.putExtra("notes", gekozenGame.notes);
        intent.putExtra("status", gekozenGame.status);
        startActivityForResult(intent, EDIT_GAME_ACTIVITY_REQUEST_CODE + i);
    }
}
