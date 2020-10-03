package com.example.bookstorephuongnam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

public class HoaDonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
    }

    public void callThemHoaDonActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), AddHoaDonActivity.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.fab_theloai), "transition_book");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(HoaDonActivity.this);
        startActivity(intent, options.toBundle());
    }

    public void returnHoaDonActivity(View view) {
        finish();
    }
}