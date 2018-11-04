package com.koolenwijkstra.siree.studentportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class GateToPortal extends AppCompatActivity {
    WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigatetoportal);

        myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl(getIntent().getStringExtra("url"));
    }
}
