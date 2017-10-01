package com.example.goateeddev.swipedetector;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {

    int target, seconds, swipes = 0;
    float initX = 0, initY = 0, movX = 0, movY = 0, finX = 0, finY = 0;
    final double x = 15, cosx = Math.cos(Math.toRadians(x));
    boolean gameRunning = false, dc = false;

    TextView tv_message, tv_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        final EditText et_target = (EditText) findViewById(R.id.et_target);

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                target = Integer.parseInt(et_target.getText().toString());
                setContentView(R.layout.activity_timer_started);

                tv_message = (TextView) findViewById(R.id.tv_message);
                tv_count = (TextView) findViewById(R.id.tv_count);

                initialiseGame();

                findViewById(R.id.relative_layout).setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent e) {
                        if (gameRunning) {
                            if (e.getAction() == MotionEvent.ACTION_DOWN) {
                                initX = e.getX();
                                initY = e.getY();
                            }
                            if (e.getAction() == MotionEvent.ACTION_MOVE && !dc) {
                                movX = e.getX();
                                movY = e.getY();
                                dc = directionChanged(initX, initY, movX, movY);
                            }
                            if (e.getAction() == MotionEvent.ACTION_UP) {
                                if (!dc) {
                                    finX = e.getX();
                                    finY = e.getY();
                                    float opp = Math.abs(initX - finX);
                                    float adj = Math.abs(initY - finY);
                                    double hyp = adj / cosx;
                                    double oppe = Math.abs(Math.sqrt((hyp * hyp) - (adj * adj)));
                                    if (opp < oppe) {
                                        swipes++;
                                        if(swipes <= target) tv_count.setText(swipes + "");
                                    }
                                } else {
                                    dc = false;
                                }
                            }
                        }
                        return true;
                    }
                });
            }
        });
    }

    public boolean directionChanged(float ix, float iy, float mx, float my){
        float opp = Math.abs(ix - mx);
        float adj = Math.abs(iy - my);
        double hyp = adj/cosx;
        double oppexp = Math.abs(Math.sqrt((hyp*hyp) - (adj*adj)));
        return opp > oppexp;
    }

    public void initialiseGame(){
        gameRunning = true;
        seconds = 0;
        startTimer();
    }

    public void startTimer() {
        new CountDownTimer(61000, 1000){
            public void onTick(long millisUntilFinished){
                seconds++;
                if (swipes >= target){
                    gameRunning = false;
                    tv_message.setText("GAME OVER!" + "\n" + "You took " + seconds + " seconds to get " + target + " swipes");
                    seconds--;
                }
            }
            public void onFinish(){
                if(gameRunning){
                    tv_message.setText("GAME OVER!" + "\n" + "You took too long to get " + target + " swipes");
                    gameRunning = false;
                }
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}