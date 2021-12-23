package com.nantoka.smaatocustomcsmadapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.smaato.sdk.banner.ad.BannerAdSize;
import com.smaato.sdk.banner.widget.BannerError;
import com.smaato.sdk.banner.widget.BannerView;
import com.smaato.sdk.interstitial.Interstitial;
import com.smaato.sdk.interstitial.InterstitialAd;
import com.smaato.sdk.interstitial.InterstitialError;
import com.smaato.sdk.interstitial.InterstitialRequestError;
import com.smaato.sdk.rewarded.RewardedError;
import com.smaato.sdk.rewarded.RewardedInterstitial;
import com.smaato.sdk.rewarded.RewardedInterstitialAd;
import com.smaato.sdk.rewarded.RewardedRequestError;

public class MainActivity extends AppCompatActivity {
    BannerView bannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bannerView = findViewById(R.id.bannerView);
        setBannerViewListener();

        findViewById(R.id.button_banner).setOnClickListener(ignore ->
                bannerView.loadAd("133149963", BannerAdSize.XX_LARGE_320x50));

        findViewById(R.id.button_interstitial).setOnClickListener(ignore ->
                Interstitial.loadAd("133149969", interstitialEventListener));

        findViewById(R.id.button_rewarded).setOnClickListener(ignore ->
                RewardedInterstitial.loadAd("133149969", rewardedEventListener));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bannerView.destroy();
    }

    private void setBannerViewListener(){
        bannerView.setEventListener(new BannerView.EventListener() {
            @Override

            public void onAdLoaded(@NonNull BannerView bannerView) {
                Toast.makeText(MainActivity.this, "BannerView: onAdLoaded", Toast.LENGTH_SHORT).show();
            }

            @Override

            public void onAdFailedToLoad(@NonNull BannerView bannerView, @NonNull BannerError bannerError) {
                Toast.makeText(MainActivity.this, "BannerView: onAdFailedToLoad", Toast.LENGTH_SHORT).show();
            }

            @Override

            public void onAdImpression(@NonNull BannerView bannerView) {
                Toast.makeText(MainActivity.this, "BannerView: onAdImpression", Toast.LENGTH_SHORT).show();
            }

            @Override

            public void onAdClicked(@NonNull BannerView bannerView) {
                Toast.makeText(MainActivity.this, "BannerView: onAdClicked", Toast.LENGTH_SHORT).show();
            }

            @Override

            public void onAdTTLExpired(@NonNull BannerView bannerView) {
                Toast.makeText(MainActivity.this, "BannerView: onAdTTLExpired", Toast.LENGTH_SHORT).show();
            }
        });
    }

    com.smaato.sdk.interstitial.EventListener interstitialEventListener = new com.smaato.sdk.interstitial.EventListener() {
        @Override
        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
            Toast.makeText(MainActivity.this, "Interstitial: onAdLoaded", Toast.LENGTH_SHORT).show();
            interstitialAd.setBackgroundColor(0xff123456);
            interstitialAd.showAd(MainActivity.this);
        }

        @Override
        public void onAdFailedToLoad(@NonNull InterstitialRequestError interstitialRequestError) {
            Toast.makeText(MainActivity.this, "Interstitial: onAdFailedToLoad", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAdError(@NonNull InterstitialAd interstitialAd, @NonNull InterstitialError interstitialError) {
            Toast.makeText(MainActivity.this, "Interstitial: onAdError", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAdOpened(@NonNull InterstitialAd interstitialAd) {
            Toast.makeText(MainActivity.this, "Interstitial: onAdOpened", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAdClosed(@NonNull InterstitialAd interstitialAd) {
            Toast.makeText(MainActivity.this, "Interstitial: onAdClosed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAdClicked(@NonNull InterstitialAd interstitialAd) {
            Toast.makeText(MainActivity.this, "Interstitial: onAdClicked", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAdImpression(@NonNull InterstitialAd interstitialAd) {
            Toast.makeText(MainActivity.this, "Interstitial: onAdImpression", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAdTTLExpired(@NonNull InterstitialAd interstitialAd) {
            Toast.makeText(MainActivity.this, "Interstitial: onAdTTLExpired", Toast.LENGTH_SHORT).show();
        }
    };

    com.smaato.sdk.rewarded.EventListener rewardedEventListener = new com.smaato.sdk.rewarded.EventListener() {
        @Override
        public void onAdLoaded(@NonNull RewardedInterstitialAd rewardedInterstitialAd) {
            Toast.makeText(MainActivity.this, "Rewarded: onAdLoaded", Toast.LENGTH_SHORT).show();
            rewardedInterstitialAd.showAd();
        }

        @Override
        public void onAdFailedToLoad(@NonNull RewardedRequestError rewardedRequestError) {
            Toast.makeText(MainActivity.this, "Rewarded: onAdFailedToLoad", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAdError(@NonNull RewardedInterstitialAd rewardedInterstitialAd, @NonNull RewardedError rewardedError) {
            Toast.makeText(MainActivity.this, "Rewarded: onAdError", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAdClosed(@NonNull RewardedInterstitialAd rewardedInterstitialAd) {
            Toast.makeText(MainActivity.this, "Rewarded: onAdClosed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAdClicked(@NonNull RewardedInterstitialAd rewardedInterstitialAd) {
            Toast.makeText(MainActivity.this, "Rewarded: onAdClicked", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAdStarted(@NonNull RewardedInterstitialAd rewardedInterstitialAd) {
            Toast.makeText(MainActivity.this, "Rewarded: onAdStarted", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAdReward(@NonNull RewardedInterstitialAd rewardedInterstitialAd) {
            Toast.makeText(MainActivity.this, "Rewarded: onAdReward", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAdTTLExpired(@NonNull RewardedInterstitialAd rewardedInterstitialAd) {
            Toast.makeText(MainActivity.this, "Rewarded: onAdTTLExpired", Toast.LENGTH_SHORT).show();
        }
    };
}