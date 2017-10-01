package com.example.goateeddev.swipedetector;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

public class CountdownActivity extends AppCompatActivity {

    int time, count = 0;
    float initX = 0, initY = 0, movX = 0, movY = 0, finX = 0, finY = 0;
    final double x = 15, cosx = Math.cos(Math.toRadians(x));
    boolean gameRunning = false, dc = false;

    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);

        final NumberPicker np = (NumberPicker) findViewById(R.id.np);
        np.setMinValue(5);
        np.setMaxValue(30);
        np.setWrapSelectorWheel(true);
        np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time = np.getValue();
                setContentView(R.layout.activity_countdown_started);

                try{
                    findViewById(R.id.relative_layout).setBackgroundResource(getIntent().getExtras().getInt("resource"));
                }catch (Exception e){
                    Log.d("Countdown Exception", "::::: " + e.toString());
                }

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
                                        count++;
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
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                TextView tv_total = (TextView) findViewById(R.id.tv_total);
                tv_total.setText( "GAME OVER!" + "\n" + "Total Swipes = " + count);
                stopHandler();
                gameRunning = false;
            }
        };
        startHandler();
    }

    public void resetGame(){
        stopHandler();
        count = 0;
    }

    public void startHandler() {
        final TextView countdown = (TextView) findViewById(R.id.tv_countdown_time);

        new CountDownTimer((time * 1000) + 2000, 1000){
            int cd = time;
            public void onTick(long millisUntilFinished){
                if((cd + "").length() < 2){
                    countdown.setText("00:0" + cd--);
                } else {
                    countdown.setText("00:" + cd--);
                }
            }
            public void onFinish(){
            }
        }.start();

        handler.postDelayed(runnable, time * 1000);
    }

    public void stopHandler() {
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        //stopHandler();
        //startHandler();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}