package com.example.bookstorephuongnam.AminationOnOpenScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookstorephuongnam.LoginActivity;
import com.example.bookstorephuongnam.TrangChinhActivity;
import com.example.bookstorephuongnam.R;

public class AnimationSplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_animation_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), TrangChinhActivity.class));
                finish();
            }
        }, 3000);
    }
}