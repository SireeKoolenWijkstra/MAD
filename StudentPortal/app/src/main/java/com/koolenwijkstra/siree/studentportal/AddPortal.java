package com.koolenwijkstra.siree.studentportal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPortal extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addportal);

        Button mButton = findViewById(R.id.addPortal);

        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText url = (EditText) findViewById(R.id.invoerUrl);
                EditText title = (EditText) findViewById(R.id.invoerTitle);

                if ((url.getText().toString().matches("https://"))||(url.getText().toString().matches(""))){
                    Context context = getApplicationContext();
                    CharSequence text = "Voer een geldige URL in!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }else{
                    // put the String to pass back into an Intent and close this activity
                    Intent intent = new Intent();
                    intent.putExtra("url", url.getText().toString());
                    intent.putExtra("title", title.getText().toString());
                    setResult(RESULT_OK, intent);
                    //Go back to the previous activity
                    finish();
                }

            }
        });
    }
};
