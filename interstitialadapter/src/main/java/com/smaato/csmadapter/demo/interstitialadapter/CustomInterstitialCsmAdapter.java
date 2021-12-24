package com.smaato.csmadapter.demo.interstitialadapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.google.auto.service.AutoService;
import com.smaato.sdk.interstitial.csm.SMAInterstitialNetworkEvent;
import com.smaato.sdk.interstitial.csm.SMAInterstitialNetworkEventListener;

import java.util.Map;

import androidx.annotation.NonNull;

@AutoService(SMAInterstitialNetworkEvent.class)
public class CustomInterstitialCsmAdapter implements SMAInterstitialNetworkEvent {

    private static final String NETWORK_NAME = "SMACustomCSMInterstitialAdapter"; //This is the name that has been configured in SPX as Classname

    private SMAInterstitialNetworkEventListener smaInterstitialNetworkEventListener;
    private Activity launchActivity;

    @Override
    public void requestInterstitial(@NonNull Context context,
                                    @NonNull SMAInterstitialNetworkEventListener smaInterstitialNetworkEventListener,
                                    @NonNull Map<String, String> params,
                                    @NonNull Map<String, Object> objectExtras) {

        this.smaInterstitialNetworkEventListener = smaInterstitialNetworkEventListener;

        //Replace this piece of code with the code for requesting an ad from your network
        if (context instanceof Activity){
            launchActivity = (Activity) context;
            smaInterstitialNetworkEventListener.onAdLoaded();
        }
        else {
            smaInterstitialNetworkEventListener.onAdFailedToLoad();
        }
    }

    @Override
    public boolean isValid() {
        return true; //Should generally return true if and only if the ad has been successfully loaded
    }

    @Override
    public void showAd() {
        Intent intent = new Intent(launchActivity, InterstitialActivity.class);
        InterstitialActivity.onClick = () -> smaInterstitialNetworkEventListener.onAdClicked();
        InterstitialActivity.onDestroy = () -> smaInterstitialNetworkEventListener.onAdClosed();

        launchActivity.startActivity(intent);
        smaInterstitialNetworkEventListener.onAdOpened();
    }

    @Override
    public void onDestroy() {
        launchActivity = null;
    }

    @NonNull
    @Override
    public String getNetworkName() {
        return NETWORK_NAME;
    }
}
