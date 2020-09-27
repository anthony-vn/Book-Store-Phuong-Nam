package com.example.bookstorephuongnam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TheLoaiActivity extends AppCompatActivity {

    FloatingActionButton fab_them;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theloai);
        anhxa();

    }

    private void anhxa(){
        fab_them = findViewById(R.id.fab_theloai);
    }
    public void callThemTheLoaiActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), AddTheLoaiActivity.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.fab_theloai), "transition_login");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(TheLoaiActivity.this);
        startActivity(intent, options.toBundle());
    }
}