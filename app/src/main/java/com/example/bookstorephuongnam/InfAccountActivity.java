package com.example.bookstorephuongnam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

public class InfAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inf_account);
    }

    public void returnInfAccount(View view) {
        finish();
    }

    public void startActivityChangePassword(View view) {
        Intent intent = new Intent(getApplicationContext(), PasswordUpdateActivity.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.btnStartChangePass), "transition_changePass");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(InfAccountActivity.this);
        startActivity(intent, options.toBundle());
    }

    public void startActivitySignUp(View view) {
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.btnStartSignUp), "transition_signUp");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(InfAccountActivity.this);
        startActivity(intent, options.toBundle());
    }

    public void SignOut(View view) {
    }
}