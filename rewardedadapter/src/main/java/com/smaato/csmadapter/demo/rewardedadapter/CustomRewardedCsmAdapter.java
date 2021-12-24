package com.smaato.csmadapter.demo.rewardedadapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.google.auto.service.AutoService;
import com.smaato.sdk.rewarded.csm.SMARewardedNetworkEvent;
import com.smaato.sdk.rewarded.csm.SMARewardedNetworkEventListener;

import java.util.Map;

import androidx.annotation.NonNull;

@AutoService(SMARewardedNetworkEvent.class)
public class CustomRewardedCsmAdapter implements SMARewardedNetworkEvent{

    private static final String NETWORK_NAME = "SMACustomCSMRewardedAdapter"; //This name needs to be configured in SPX as Classname


    private SMARewardedNetworkEventListener smaRewardedNetworkEventListener;

    private Activity launchActivity;

    @Override
    public void requestRewardedInterstitial(@NonNull Context context,
                                            @NonNull SMARewardedNetworkEventListener smaRewardedNetworkEventListener,
                                            @NonNull Map<String, String> params,
                                            @NonNull Map<String, Object> objectExtras) {

        this.smaRewardedNetworkEventListener = smaRewardedNetworkEventListener;

        //Replace this piece of code with the code for requesting an ad from your network
        if (context instanceof Activity){
            launchActivity = (Activity) context;
            smaRewardedNetworkEventListener.onAdLoaded();
        }
        else {
            smaRewardedNetworkEventListener.onAdFailedToLoad();
        }
    }

    @Override
    public void showAd() {
        Intent intent = new Intent(launchActivity, RewardedActivity.class);
        RewardedActivity.onClick = () -> smaRewardedNetworkEventListener.onAdClicked();
        RewardedActivity.onDestroy = () -> smaRewardedNetworkEventListener.onAdClosed();
        RewardedActivity.onReward = () -> smaRewardedNetworkEventListener.onAdReward();

        launchActivity.startActivity(intent);
        smaRewardedNetworkEventListener.onAdStarted();
    }

    @Override
    public boolean isValid() {
        return true; //Should generally return true if and only if the ad has been successfully loaded
    }

    @Override
    public void onDestroy() {

    }

    @NonNull
    @Override
    public String getNetworkName() {
        return NETWORK_NAME;
    }
}

