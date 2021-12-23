package com.smaato.csmadapter.demo;


import android.annotation.SuppressLint;
import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.smaato.sdk.core.Config;
import com.smaato.sdk.core.Gender;
import com.smaato.sdk.core.SmaatoSdk;
import com.smaato.sdk.core.log.LogLevel;

public class SmaatoCustomAdapterApplication extends Application {

        @SuppressLint("ApplySharedPref")
        @Override
        public void onCreate() {
            super.onCreate();

            Config config = Config.builder()
                    .setLogLevel(LogLevel.ERROR)
                    .setHttpsOnly(true)
                    .build();

            SmaatoSdk.init(this, config, "1100042525");

            SmaatoSdk.setSearchQuery("bitcoin, lamborghini, san-francisco");
            SmaatoSdk.setGender(Gender.MALE);
            SmaatoSdk.setAge(40);

            SmaatoSdk.setGPSEnabled(true);

            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("IABTCF_gdprApplies", 0);
            editor.commit();
        }
    }