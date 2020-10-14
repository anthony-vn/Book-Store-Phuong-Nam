package com.example.bookstorephuongnam;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class InfAccountActivity extends AppCompatActivity {

    private TextView tvUsername, tvPassword, tvEmail;
    FirebaseAuth fbAuth;
    FirebaseFirestore ffs;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inf_account);

        tvUsername = findViewById(R.id.info_username);
        tvPassword = findViewById(R.id.info_password);
        tvEmail = findViewById(R.id.info_email);
        fbAuth = FirebaseAuth.getInstance();
        ffs = FirebaseFirestore.getInstance();
        userID = fbAuth.getCurrentUser().getUid();

        final DocumentReference documentReference = ffs.collection("User").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                tvUsername.setText(value.getString("USERNAME"));
                tvPassword.setText(value.getString("PASSWORD"));
                tvEmail.setText(value.getString("EMAIL"));
            }
        });
    }

    public void returnInfAccount(View view) {
        finish();
    }

    public void startActivityChangePassword(View view) {

    }

    public void startActivitySignUp(View view) {

    }

    public void SignOut(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}