package com.smaato.csmadapter.demo.rewardedadapter;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class RewardedActivity extends AppCompatActivity {

    public static Runnable onClick;
    public static Runnable onReward;
    public static Runnable onDestroy;

    private CountDownTimer countDownTimer;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rewarded);

        findViewById(R.id.text).setOnClickListener(ignore -> onClick.run());

        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        findViewById(R.id.close).setOnClickListener(ignore -> finish());

        startCountDown();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
        onDestroy.run();
    }

    private void startCountDown(){
        textView = findViewById(R.id.text);
        countDownTimer = new CountDownTimer(10500, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(String.format(Locale.ENGLISH, "Countdown\n\n%d", millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                textView.setText("Rewarded");
                onReward.run();
            }
        };

        countDownTimer.start();
    }
}