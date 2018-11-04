package com.koolenwijkstra.siree.dice;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final int SOORTDOBBEL = 6;
    //local variables
    private ImageView mImageView;
    private Button mButtonLager;
    private Button mButtonHoger;
    private ListView mListview;
    private ArrayAdapter mAdapter;
    private List<String> mOverzichtGooi;
    //start van eigen variabelen, beschrijven state van app
    private int mHuidigeDie = 1;
    private int mHighScore;
    private int mScore;

    private int[] mHuidigeGooi = new int[]{R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4,
            R.drawable.d5, R.drawable.d6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = findViewById(R.id.dice);
        mListview = findViewById(R.id.opsommingScores);

        mOverzichtGooi = new ArrayList<String>();


        FloatingActionButton fabDown = findViewById(R.id.pijlDown);
        fabDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                klikOpPijl(true);
            }
        });

        FloatingActionButton fabUp = findViewById(R.id.pijlUp);
        fabUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                klikOpPijl(false);
            }
        });
    }

    /*
        vergelijk de nieuwe score met de oude, geef een toaster als het fout is, geef een
        jouwScore + 1 als het goed is. Toon in de Listview de nieuwe score.
        */
    private void klikOpPijl(boolean pijlDown) {

        int mOudeWorp = mHuidigeDie;
        mHuidigeDie = rolDice();

        if ((pijlDown && mOudeWorp > mHuidigeDie) || (!pijlDown && mOudeWorp < mHuidigeDie)) {
            mScore++;
            toonScore();
            if (mScore > mHighScore) {
                mHighScore = mScore;
                toonHighScore();
            }
        } else {
            toastme();
            mScore = 0;
            toonScore();
        }
        updateUI();
    }


    private void toastme() {
        Context context = getApplicationContext();
        CharSequence text = "You Lose!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

    private int rolDice() {
        return (int) ((Math.random() * SOORTDOBBEL) + 1);
    }


    private void updateUI() {
        updateListview();

    }


    private void updateListview() {

        int mResultaatGooi = mHuidigeGooi[mHuidigeDie - 1];

        mOverzichtGooi.add("Throw is " + mHuidigeDie);
        //Laat nieuwe diceplaatje zien
        mImageView.setImageResource(mResultaatGooi);

        //niet meer dan 5 resultaten tonen anders wordt de Listview te groot
        if (mOverzichtGooi.size() > 5) {
            mOverzichtGooi.remove(0);

        }
        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter. De Arrayadapter toont de ge-update view op het scherm.
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mOverzichtGooi);

        mListview.setAdapter(mAdapter);
    }

    private void toonScore() {
        TextView spelScore = findViewById(R.id.jouwScore);
        spelScore.setText("Score: " + mScore);
    }

    private void toonHighScore() {
        TextView highScore = findViewById(R.id.highScore);
        highScore.setText("High Score: " + mHighScore);
    }
}
