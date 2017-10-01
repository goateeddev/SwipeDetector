package com.example.goateeddev.swipedetector;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    int bkcount = 0;
    Rect bounds;
    boolean outofbounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final TextView tv_countdown = (TextView) findViewById(R.id.tv_countdown);
        tv_countdown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent m) {
                if (m.getAction() == MotionEvent.ACTION_DOWN){
                    tv_countdown.setBackgroundColor(Color.WHITE);
                    tv_countdown.setTextColor(Color.BLACK);
                    bounds = new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
                    outofbounds = false;
                }
                if(m.getAction() == MotionEvent.ACTION_MOVE){
                    if (!bounds.contains(view.getLeft() + (int) m.getX(), view.getTop() + (int) m.getY())){
                        outofbounds = true;
                    }
                }
                if (m.getAction() == MotionEvent.ACTION_UP){
                    tv_countdown.setBackgroundColor(Color.BLACK);
                    tv_countdown.setTextColor(Color.WHITE);
                    if(!outofbounds){
                        try{
                            Bundle extras = getIntent().getExtras();
                            startActivity(new Intent(getApplicationContext(), CountdownActivity.class).putExtra("resource", extras.getInt("resource")));
                        }catch (Exception e){
                            startActivity(new Intent(getApplicationContext(), CountdownActivity.class));
                        }
                    }
                }
                return true;
            }
        });

        final TextView tv_timer = (TextView) findViewById(R.id.tv_timer);
        tv_timer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent m) {
                if (m.getAction() == MotionEvent.ACTION_DOWN){
                    tv_timer.setBackgroundColor(Color.BLACK);
                    tv_timer.setTextColor(Color.WHITE);
                    bounds = new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
                    outofbounds = false;
                }
                if(m.getAction() == MotionEvent.ACTION_MOVE){
                    if (!bounds.contains(view.getLeft() + (int) m.getX(), view.getTop() + (int) m.getY())){
                        outofbounds = true;
                    }
                }
                if (m.getAction() == MotionEvent.ACTION_UP){
                    tv_timer.setBackgroundColor(Color.WHITE);
                    tv_timer.setTextColor(Color.BLACK);
                    if(!outofbounds){
                        try{
                            Bundle extras = getIntent().getExtras();
                            startActivity(new Intent(getApplicationContext(), TimerActivity.class).putExtra("resource", extras.getInt("resource")));
                        }catch (Exception e){
                            startActivity(new Intent(getApplicationContext(), TimerActivity.class));
                        }
                    }
                }
                return true;
            }
        });

        final TextView tv_levels = (TextView) findViewById(R.id.tv_levels);
        tv_levels.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent m) {
                if (m.getAction() == MotionEvent.ACTION_DOWN){
                    tv_levels.setBackgroundColor(Color.WHITE);
                    tv_levels.setTextColor(Color.BLACK);
                    bounds = new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
                    outofbounds = false;
                }
                if(m.getAction() == MotionEvent.ACTION_MOVE){
                    if (!bounds.contains(view.getLeft() + (int) m.getX(), view.getTop() + (int) m.getY())){
                        outofbounds = true;
                    }
                }
                if (m.getAction() == MotionEvent.ACTION_UP){
                    tv_levels.setBackgroundColor(Color.BLACK);
                    tv_levels.setTextColor(Color.WHITE);
                    if(!outofbounds){
                        startActivity(new Intent(getApplicationContext(), LevelsActivity.class));
                    }
                }
                return true;
            }
        });

        final TextView tv_highscores = (TextView) findViewById(R.id.tv_highscores);
        tv_highscores.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent m) {
                if (m.getAction() == MotionEvent.ACTION_DOWN){
                    tv_highscores.setBackgroundColor(Color.BLACK);
                    tv_highscores.setTextColor(Color.WHITE);
                    bounds = new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
                    outofbounds = false;
                }
                if(m.getAction() == MotionEvent.ACTION_MOVE){
                    if (!bounds.contains(view.getLeft() + (int) m.getX(), view.getTop() + (int) m.getY())){
                        outofbounds = true;
                    }
                }
                if (m.getAction() == MotionEvent.ACTION_UP){
                    tv_highscores.setBackgroundColor(Color.WHITE);
                    tv_highscores.setTextColor(Color.BLACK);
                    if(!outofbounds){
                        startActivity(new Intent(getApplicationContext(), HighscoresActivity.class));
                    }
                }
                return true;
            }
        });

        final TextView tv_settings = (TextView) findViewById(R.id.tv_settings);
        tv_settings.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent m) {
                if (m.getAction() == MotionEvent.ACTION_DOWN){
                    tv_settings.setBackgroundColor(Color.WHITE);
                    tv_settings.setTextColor(Color.BLACK);
                    bounds = new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
                    outofbounds = false;
                }
                if(m.getAction() == MotionEvent.ACTION_MOVE){
                    if (!bounds.contains(view.getLeft() + (int) m.getX(), view.getTop() + (int) m.getY())){
                        outofbounds = true;
                    }
                }
                if (m.getAction() == MotionEvent.ACTION_UP){
                    tv_settings.setBackgroundColor(Color.BLACK);
                    tv_settings.setTextColor(Color.WHITE);
                    if(!outofbounds){
                        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                        finish();
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (bkcount > 0) {
            finish();
        } else {
            bkcount++;
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
            new CountDownTimer(2000, 1000){
                public void onTick(long millisUntilFinished){
                }
                public void onFinish(){
                    bkcount = 0;
                }
            }.start();
        }
    }
}
