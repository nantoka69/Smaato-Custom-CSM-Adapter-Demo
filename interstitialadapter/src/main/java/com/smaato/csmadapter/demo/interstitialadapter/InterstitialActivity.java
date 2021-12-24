package com.smaato.csmadapter.demo.interstitialadapter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class InterstitialActivity extends AppCompatActivity {

    public static Runnable onClick;
    public static Runnable onDestroy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_interstitial);

        findViewById(R.id.text).setOnClickListener(ignore -> onClick.run());
        findViewById(R.id.close).setOnClickListener(ignore -> finish());

        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onDestroy.run();
    }
}