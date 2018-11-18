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
        Add a touch helper to the RecyclerView to recognize when a user swipes to delete a list entry.
        An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
        and uses callbacks to signal when a user is performing these actions.
        */
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
                    target) {
                return false;
            }

            //Called when a user swipes left or right on a ViewHolder
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                //Get the index corresponding to the selected position
                int position = (viewHolder.getAdapterPosition());
                gameOverzicht.remove(position);
                adapter.notifyItemRemoved(position);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mCardObject);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == NEW_GAME_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                // get String data from Intent
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
     * @param i
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
