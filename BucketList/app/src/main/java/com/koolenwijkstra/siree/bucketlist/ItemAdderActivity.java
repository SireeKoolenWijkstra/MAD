package com.koolenwijkstra.siree.bucketlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ItemAdderActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY =
            "com.example.android.roomwordssample.REPLY";

    private EditText mEditTitle;
    private EditText mEditDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_adder);
        mEditTitle = findViewById(R.id.edit_title);
        mEditDescription = findViewById(R.id.edit_description);

        final Button button = findViewById(R.id.button_save);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditTitle.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    Item item = new Item(mEditTitle.getText().toString()
                            , mEditDescription.getText().toString()
                            , false);
                    replyIntent.putExtra(EXTRA_REPLY, item);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });


    }
}
