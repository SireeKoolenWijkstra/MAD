package com.koolenwijkstra.siree.numbertrivia;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TriviaItemAdapter extends RecyclerView.Adapter<TriviaItemAdapter.ViewHolder> {

    private final Context context;
    ArrayList<TriviaItem> mTriviaList;

    boolean switchscreen = false;

    final private TriviaItemClicklistener mTriviaItemClicklistener;

    public TriviaItemAdapter(ArrayList<TriviaItem> mTriviaList, Context context, TriviaItemClicklistener mTriviaItemClickListener) {
        this.mTriviaList = mTriviaList;
        this.context = context;
        this.mTriviaItemClicklistener = mTriviaItemClickListener;
    }

    @NonNull
    @Override
    public TriviaItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;

        //Inflate maakt van je XML een javaobject, je moet dus een verwijzing hebben naar waar je XML staat
        if (switchscreen == true) {
            view = inflater.inflate(R.layout.item_trivia_last, null);
            switchscreen = false;

            // Return a new holder instance
        }else {view = inflater.inflate(R.layout.item_trivia_first, null);
                switchscreen = true;

            // Return a new holder instance
        }
        TriviaItemAdapter.ViewHolder viewHolder = new TriviaItemAdapter.ViewHolder(view);
        return viewHolder;
    }

    /**
     * @param i index van de verzameling data
     *
     * */
    @Override
    public void onBindViewHolder(@NonNull TriviaItemAdapter.ViewHolder viewHolder, int i) {
        viewHolder.text.setText(mTriviaList.get(i).getText());
        viewHolder.number.setText((mTriviaList.get(i).getNumber().toString()));
    }

    @Override
    public int getItemCount() {
        return mTriviaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView number;
        TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            number = (TextView) itemView.findViewById(R.id.number);
            text = (TextView) itemView.findViewById(R.id.trivia_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mTriviaItemClicklistener.triviaItemOnClick(clickedPosition);
        }
    }

    public interface TriviaItemClicklistener {
        void triviaItemOnClick(int i);
    }
}




