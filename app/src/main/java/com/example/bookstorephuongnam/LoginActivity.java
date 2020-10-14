package com.example.bookstorephuongnam;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText edtEmail, edtPassword;
    Button btnLogin;
    TextView tvSignUp, tvForgotPass;
    ProgressBar pbLogin;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        pbLogin = findViewById(R.id.pbLogin);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btnSignIn);
        tvSignUp = findViewById(R.id.tv_signUp);
        tvForgotPass = findViewById(R.id.tv_forgotPassword);
        firebaseAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_ = edtEmail.getText().toString().trim();
                String password_ = edtPassword.getText().toString().trim();
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

                pbLogin.setVisibility(View.VISIBLE);

                //Login in with firebase
                firebaseAuth.signInWithEmailAndPassword(email_, password_).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), TrangChinhActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            pbLogin.setVisibility(View.INVISIBLE);
                        }
                    }
                });

            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
                finish();
            }
        });

        tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
                finish();
            }
        });
    }
}