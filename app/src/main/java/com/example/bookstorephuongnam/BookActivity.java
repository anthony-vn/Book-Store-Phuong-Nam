package com.example.bookstorephuongnam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

public class BookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
    }

    public void callThemBookActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), AddBookActivity.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.fab_theloai), "transition_book");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(BookActivity.this);
        startActivity(intent, options.toBundle());
    }

    public void returnListBook(View view) {
        finish();
    }
}