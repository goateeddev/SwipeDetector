package com.example.goateeddev.swipedetector;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Intent home = new Intent(this, HomeActivity.class);
        new CountDownTimer(3000, 1000){
            public void onTick(long millisUntilFinished){
            }
            public void onFinish(){
                startActivity(home);
                finish();
            }
        }.start();
    }
}
