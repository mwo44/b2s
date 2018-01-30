package com.example.iala.ialatest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebChromeClient;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import java.util.Locale;

public class IALAHome extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ialahome);

        WebView webView;
        webView = (WebView) findViewById(R.id.activity_ialahome_webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);

        webView.loadUrl("file:///android_asset/html/index.html");

        webView.setWebChromeClient(new WebChromeClient() {
            public void onConsoleMessage(String message, int lineNumber, String sourceID) {
                Log.d("MyApplication", message + " -- From line "
                        + lineNumber + " of "
                        + sourceID);
            }
        });

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String title = "Exit";
        String prompt = "Do you want to exit?";
        String confirm = "Yes";
        String deny = "No";

        if (Locale.getDefault().getLanguage().equals("pl")) {
            title = "Wyjdź";
            prompt = "Czy chcesz wyjść?";
            confirm = "Tak";
            deny = "Nie";
        }

        builder.setTitle(title).setMessage(prompt).setCancelable(false).setPositiveButton(confirm, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                moveTaskToBack(true);
                IALAHome.this.finish();
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
                getParent().finish();
            }
        }).setNegativeButton(deny, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            }
        });

        AlertDialog alert = builder.create();
        alert.setCancelable(false);
        alert.show();

    }
}