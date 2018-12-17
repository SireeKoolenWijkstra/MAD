package com.koolenwijkstra.siree.numbertrivia;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.koolenwijkstra.siree.numbertrivia.TriviaItemAdapter.TriviaItemClicklistener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements TriviaItemClicklistener {

    private TextView mQuoteTextView;
    private TextView mNumberTextView;

    private RecyclerView mTrivia;
    final ArrayList<TriviaItem> mTriviaList = new ArrayList<>();
    TriviaItemAdapter adapter;
    //   private TextView mQuoteLastTextView;
    //   private TextView mNumberLastTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuoteTextView = findViewById(R.id.trivia_text);
        mNumberTextView = findViewById(R.id.number);

        mTrivia = findViewById(R.id.trivia);

        //bepaal de layoutmanager
        mTrivia.setLayoutManager(new LinearLayoutManager(this));
        mTrivia.setHasFixedSize(true);

        adapter = new TriviaItemAdapter(mTriviaList, getApplicationContext(), this);
        mTrivia.setAdapter(adapter);

//        mQuoteLastTextView = findViewById(R.id.trivia_text_last);
//        mNumberLastTextView = findViewById(R.id.number_last);

        FloatingActionButton fabAdd = (FloatingActionButton) findViewById(R.id.fab);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestData();

            }
        });
    }

    public void setTriviaItem(String quoteMessage, Integer quoteNumber) {
        mTriviaList.add(new TriviaItem(quoteMessage, quoteNumber));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void triviaItemOnClick(int i) {
    }

    private void requestData()
    {
        NumbersApiService service = NumbersApiService.retrofit.create(NumbersApiService.class);
        /**
         * Make an a-synchronous call by enqueing and definition of callbacks.
         * Call<TriviaItem> getTrivia();
         */

        Call<TriviaItem> call = service.getTrivia();
        call.enqueue(new Callback<TriviaItem>() {

            @Override
            public void onResponse(Call<TriviaItem> call, Response<TriviaItem> response) {
                TriviaItem triviaOneItem = response.body();
                Log.v("triviaOneItem", triviaOneItem.toString());
                setTriviaItem(triviaOneItem.getText(), triviaOneItem.getNumber());
            }


            @Override
            public void onFailure(Call<TriviaItem> call, Throwable t) {
                Log.d("error", t.toString());

            }
        });
    }
}
