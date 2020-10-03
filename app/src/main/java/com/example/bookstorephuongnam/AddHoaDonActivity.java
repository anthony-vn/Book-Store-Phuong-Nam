package com.example.bookstorephuongnam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

public class AddHoaDonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hoa_don);
    }

    public void datePicker(View view) {
    }

    public void ADDHoaDon(View view) {
    }

    public void returnAddHoaDon(View view) {
        finish();
    }

    public void nextToHoaDonChiTiet(View view) {
        Intent intent = new Intent(getApplicationContext(), HoaDonChiTietActivity.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.fab_book), "transition_book");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(AddHoaDonActivity.this);
        startActivity(intent, options.toBundle());
    }
}