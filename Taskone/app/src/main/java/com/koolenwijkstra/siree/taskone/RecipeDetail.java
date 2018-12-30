package com.koolenwijkstra.siree.taskone;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link RecipeDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeDetail extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "image_url";
    private static final String ARG_PARAM2 = "title";

    private String mParam1;
    private String mParam2;

    private ImageView image;
    private TextView title;


    public RecipeDetail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RecipeDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static RecipeDetail newInstance(RecipeItem recipeItem) {
        RecipeDetail fragment = new RecipeDetail();
        Bundle args = new Bundle();
        Log.v("Image",recipeItem.getImage());
        Log.v("title",recipeItem.getNameRecipe());
        args.putString(ARG_PARAM1, recipeItem.getImage());
        args.putString(ARG_PARAM2, recipeItem.getNameRecipe());
        fragment.setArguments(args);
        return fragment;
    }


    // Inflate the layout for this fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        image = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);

        title.setText(getArguments().getString(ARG_PARAM2));
        Glide.with(this).load(getArguments().getString(ARG_PARAM1)).into(image);

        return view;
    }





}
