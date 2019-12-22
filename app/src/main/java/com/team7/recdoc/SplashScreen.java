package com.team7.recdoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.team7.recdoc.tools.Delay;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Delay.delay(2, new Delay.DelayCallback() {
            @Override
            public void afterDelay() {
                next();
            }
        });
    }

    void next() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
