package com.example.saravana.studentscorner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by saravana on 19/2/18.
 */

public class Webpage extends AppCompatActivity {
    WebView myWebView;

    public void onBackPressed()
    {
        if (this.myWebView.canGoBack())
        {
            this.myWebView.goBack();
            return;
        }
        startActivity(new Intent(this, Homepage.class));
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        Toast.makeText(Webpage.this,"Loading...",Toast.LENGTH_LONG).show();
        String url=getIntent().getExtras().getString("url");
        this.myWebView = (WebView) findViewById(R.id.webpage1);
        this.myWebView.setWebViewClient(new WebViewClient());
        WebSettings ws=myWebView.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setDomStorageEnabled(true);
        this.myWebView.loadUrl(url);
    }
}
