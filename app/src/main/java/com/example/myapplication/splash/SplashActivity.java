package com.example.myapplication.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.core.UserListActivity;

public class SplashActivity extends AppCompatActivity {
    int secondsDelayed = 1;
    private TextView wel_txtvw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        wel_txtvw = (TextView) findViewById(R.id.spalsh_txtvw);
        startAnimation();
        callHandler();
    }

    private void startAnimation() {
        Animation expandIn = AnimationUtils.loadAnimation(this, R.anim.expand_in);
        wel_txtvw.startAnimation(expandIn);

    }


    private void callHandler() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashActivity.this, UserListActivity.class));
                finish();
            }
        }, secondsDelayed * 2000);
    }

}
