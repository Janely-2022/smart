package com.ega.smartoutlet;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class SplashScreenActivity extends AppCompatActivity {

    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        handler  =  new Handler();
        ImageView logoImageView =  findViewById(R.id.logoImage);
//        TextView logoTextView  =  findViewById(R.id.logoText);

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.bilinking_effect);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.nordicBlueSlate));
        }

        logoImageView.startAnimation(fadeIn);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =  new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }
}