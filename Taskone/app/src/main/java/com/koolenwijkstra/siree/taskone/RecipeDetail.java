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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
    private static final String ARG_PARAM3 = "id";

    private String mParam1;
    private String mParam2;

    private ImageView image;
    private TextView title;
    private TextView ingredient;

    private RecipeItem recipeItem;
    private RecipePageAdapter mOneItemPageAdapter;

    //private ViewPager mViewPager;


    public RecipeDetail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RecipeDetail.
     */

    public static RecipeDetail newInstance(RecipeItem recipeItem) {
        RecipeDetail fragment = new RecipeDetail();
        Bundle args = new Bundle();
        Log.v("Image",recipeItem.getImage());
        Log.v("title",recipeItem.getNameRecipe());
        args.putString(ARG_PARAM1, recipeItem.getImage());
        args.putString(ARG_PARAM2, recipeItem.getNameRecipe());
        args.putString(ARG_PARAM3, recipeItem.getId());
        fragment.setArguments(args);
        return fragment;
    }


    // Inflate the layout for this fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        ImageView image = view.findViewById(R.id.image);
        TextView title = view.findViewById(R.id.title);
        TextView ingredient = view.findViewById(R.id.ingredients);

        title.setText(getArguments().getString(ARG_PARAM2));

        Glide.with(this).load(getArguments().getString(ARG_PARAM1)).into(image);
        requestIngredient(ingredient);

        return view;
    }

    private void requestIngredient(final TextView ingredient)
    {
        RecipeApiService service = RecipeApiService.retrofit.create(RecipeApiService.class);
        /**
         * Make an a-synchronous call by enqueing and definition of callbacks.
         * Call<RecipeItem> getRecipeItem();
         */

        Call<RecipeWrapper> call = service.getRecipeWrapper(getArguments().getString(ARG_PARAM3));
        call.enqueue(new Callback<RecipeWrapper>() {

            @Override
            public void onResponse(Call<RecipeWrapper> call, Response<RecipeWrapper> response) {
                RecipeWrapper recipeWrapper = response.body();
                ingredient.setText(recipeWrapper.recipeItem.getIngredient().toString());
            }


            @Override
            public void onFailure(Call<RecipeWrapper> call, Throwable t) {
                Log.d("error", t.toString());

            }
        });
    }

}
