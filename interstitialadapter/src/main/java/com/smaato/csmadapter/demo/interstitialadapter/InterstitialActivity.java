package com.smaato.csmadapter.demo.interstitialadapter;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.smaato.csmadapter.demo.interstitialadapter.databinding.ActivityInterstitialBinding;

public class InterstitialActivity extends AppCompatActivity {

    public static Runnable onClick;
    public static Runnable onDestroy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_interstitial);

        findViewById(R.id.text).setOnClickListener(ignore -> onClick.run());

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