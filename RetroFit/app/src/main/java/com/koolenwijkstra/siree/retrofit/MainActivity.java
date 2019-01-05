package com.koolenwijkstra.siree.retrofit;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    UserFragmentAdapter userFragmentAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        //userFragmentAdapter voor user_fragment
        userFragmentAdapter = new UserFragmentAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(userFragmentAdapter);

        //bind de xml met de ViewPager voor de fragment
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

    }


        /**
         * bij een klik wordt er een intent aangemaakt en het scherm van GameInput getoond. De waarden van
         * de gekozen game (op index i in de de arraylist van gameOverzicht) worden meegegeven.
         * @param i index van viewholder
         */
        /**
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
    }**/


}
