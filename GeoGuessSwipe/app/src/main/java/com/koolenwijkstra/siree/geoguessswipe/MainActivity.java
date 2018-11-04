package com.koolenwijkstra.siree.geoguessswipe;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //data source
        final ArrayList<Plaatje> fotoOverzicht = new ArrayList<Plaatje>();

        fotoOverzicht.add(new Plaatje(R.drawable.img1_yes_denmark, true));
        fotoOverzicht.add(new Plaatje(R.drawable.img2_no_canada, false));
        fotoOverzicht.add(new Plaatje(R.drawable.img3_no_bangladesh, false));
        fotoOverzicht.add(new Plaatje(R.drawable.img4_yes_kazachstan, false));
        fotoOverzicht.add(new Plaatje(R.drawable.img5_no_colombia, false));
        fotoOverzicht.add(new Plaatje(R.drawable.img6_yes_poland, true));
        fotoOverzicht.add(new Plaatje(R.drawable.img7_yes_malta, true));
        fotoOverzicht.add(new Plaatje(R.drawable.img8_no_thailand, false));


        //bind de xml met het RecyclerView object in de code
        final RecyclerView mImageObject = (RecyclerView) findViewById(R.id.recyclerView);

        //bepaal de layoutmanager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mImageObject.setLayoutManager(layoutManager);
        mImageObject.setHasFixedSize(true);

        final ImageAdapter adapter = new ImageAdapter(fotoOverzicht);
        mImageObject.setAdapter(adapter);

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
                Log.v("Mainactivity", " richting " + swipeDir);
                //Get the index corresponding to the selected position
                int position = (viewHolder.getAdapterPosition());
                //swipeDir = 4 is naar links, swipeDir 8 is naar rechts.
                if ((swipeDir == 8 && fotoOverzicht.get(position).isInEuropa()) || (swipeDir == 4 && !fotoOverzicht.get(position).isInEuropa())) {
                    Context context = getApplicationContext();
                    CharSequence text = "Dat is niet correct!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {Context context = getApplicationContext();
                    CharSequence text = "Dat is correct!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                fotoOverzicht.remove(position);
                adapter.notifyItemRemoved(position);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mImageObject);

    }
}
