package com.example.bookstorephuongnam;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText edtUsername, edtPassword, edtConfirmPass, edtEmail;
    Button btnSignUp;
    ProgressBar pbSignUp;
    FirebaseAuth fbAuth;
    FirebaseFirestore firebaseFirestore;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        //Hooks
        edtUsername = findViewById(R.id.edUsername);
        edtPassword = findViewById(R.id.edPassword);
        edtConfirmPass = findViewById(R.id.edConFirmPassword);
        edtEmail = findViewById(R.id.edEmail);
        btnSignUp = findViewById(R.id.btnSignUp);
        pbSignUp = findViewById(R.id.ProgressBarSignUp);
        fbAuth = FirebaseAuth.getInstance();

//        if (fbAuth.getCurrentUser() != null) {
//            startActivity(new Intent(getApplicationContext(), TrangChinhActivity.class));
//            finish();
//        }

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username_ = edtUsername.getText().toString().trim();
                final String password_ = edtPassword.getText().toString().trim();
                final String email_ = edtEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email_)) {
                    edtEmail.setError("Email is empty!");
                    return;
                }
                if (TextUtils.isEmpty(password_)) {
                    edtPassword.setError("Password is empty!");
                    return;
                }
                if (password_.length() < 6) {
                    edtPassword.setError("Password must be >= 6 characters.");
                    return;
                }

                pbSignUp.setVisibility(View.VISIBLE);

                //Register user in firebase
                fbAuth.createUserWithEmailAndPassword(email_, password_).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(SignUpActivity.this, "User created!", Toast.LENGTH_SHORT).show();
                            userID = fbAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = firebaseFirestore.collection("User").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("USERNAME", username_);
                            user.put("EMAIL", email_);
                            user.put("PASSWORD", password_);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user Profile is created for " + userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        } else {
                            Toast.makeText(SignUpActivity.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            pbSignUp.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });
    }

    public void retrunSignIn(View view) {
        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
        finish();
    }

}