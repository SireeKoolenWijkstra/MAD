package com.koolenwijkstra.siree.retrofit;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class UserFragment extends Fragment {

    static final int REQUEST_IMAGE_CAPTURE = 1;


    private DagUserViewModel mViewModel;

    private ImageView foto;

    private EditText mEditage;
    private EditText mEditweight;
    private EditText mEditlength;

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user_fragment, container, false);

        Button takePicture = rootView.findViewById(R.id.camera);
        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }

            }
        });

        foto = rootView.findViewById(R.id.foto);

        mEditage = rootView.findViewById(R.id.editage);
        mEditweight = rootView.findViewById(R.id.editweight);
        mEditlength = rootView.findViewById(R.id.editlength);


        mViewModel = ViewModelProviders.of(this.getActivity()).get(DagUserViewModel.class);

        mViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if (user != null) {
                    mEditage.setText(Integer.toString(user.getAge()));
                    mEditweight.setText(Integer.toString(user.getWeight()));
                    mEditlength.setText(Integer.toString(user.getLength()));
                }
            }
        });

        final Button button = rootView.findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mViewModel.setUser(new User(Integer.parseInt(mEditage.getText().toString())
                        , Integer.parseInt(mEditweight.getText().toString())
                        , Integer.parseInt(mEditlength.getText().toString())));

                Context context = getActivity();
                CharSequence text = "Your user data has been sent to the local storage";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 500);
                toast.show();
            }
        });
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == AppCompatActivity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            foto.setImageBitmap(imageBitmap);
        }
    }


}
