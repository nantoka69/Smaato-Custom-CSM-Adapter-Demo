package com.smaato.csmadapter.demo.banneradapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.auto.service.AutoService;
import com.smaato.sdk.banner.csm.SMABannerNetworkEvent;
import com.smaato.sdk.banner.csm.SMABannerNetworkEventListener;

import java.util.Map;

import androidx.annotation.NonNull;

@AutoService(SMABannerNetworkEvent.class)
public class CustomBannerCsmAdapter implements SMABannerNetworkEvent {

    private static final String NETWORK_NAME = "SMACustomCSMBannerAdapter"; //This is the name that has been configured in SPX as Classname

    SMABannerNetworkEventListener smaBannerNetworkEventListener;

    @Override
    public void requestBanner(@NonNull Context context,
                              @NonNull SMABannerNetworkEventListener smaBannerNetworkEventListener,
                              @NonNull Map<String, String> params,
                              @NonNull Map<String, Object> objectExtras) {

        this.smaBannerNetworkEventListener = smaBannerNetworkEventListener;

        String width = params.get("width");
        String height = params.get("height");

        //Replace this piece of code with the code for requesting an ad from your network
        try {
            View view = createTextView(context, width, height);
            smaBannerNetworkEventListener.onAdLoaded(view);
        }
        catch (Exception ex){
            smaBannerNetworkEventListener.onAdFailedToLoad();
        }
    }

    @Override
    public void onDestroy() {

    }

    @NonNull
    @Override
    public String getNetworkName() {
        return NETWORK_NAME;
    }

    @NonNull
    private TextView createTextView(Context context, String widthString, String heightString) {
        int width = dpToPx(context, Integer.parseInt(widthString));
        int height = dpToPx(context, Integer.parseInt(heightString));

        TextView textView = new TextView(context);
        textView.setWidth(width);
        textView.setHeight(height);
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundColor(0xff4bbbeb);
        textView.setText(String.format("Width = %s Height = %s", widthString, heightString));

        textView.setClickable(true);
        textView.setOnClickListener(ignore -> smaBannerNetworkEventListener.onAdClicked());

        return textView;
    }

    private int dpToPx(@NonNull Context context, int dpValue) {
        return (int) (dpValue * context.getResources().getDisplayMetrics().density);
    }
}
