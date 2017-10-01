package com.example.goateeddev.swipedetector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class SettingsActivity extends AppCompatActivity {

    int images[] = {R.drawable.wall1, R.drawable.wall2, R.drawable.wall3};
    int current = 0;
    Intent home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        home = new Intent(SettingsActivity.this, HomeActivity.class);

        final ImageSwitcher is = (ImageSwitcher) findViewById(R.id.is_switcher);

        Animation in = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right);
        is.setInAnimation(in);
        is.setOutAnimation(out);

        is.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView iv = new ImageView(getApplicationContext());
                iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
                iv.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                return iv;
            }
        });

        is.setImageResource(images[current]);

        findViewById(R.id.btn_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(current == 0){
                    current = images.length - 1;
                } else current--;
                is.setImageResource(images[current]);
            }
        });

        findViewById(R.id.btn_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (current == images.length - 1){
                    current = 0;
                } else current++;
                is.setImageResource(images[current]);
            }
        });

        findViewById(R.id.btn_select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home.putExtra("resource", images[current]);
                Toast.makeText(SettingsActivity.this, "Swipe background has been changed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(home);
        finish();
    }
}
