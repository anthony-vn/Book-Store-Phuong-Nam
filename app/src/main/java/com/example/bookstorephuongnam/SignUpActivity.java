package com.example.bookstorephuongnam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookstorephuongnam.DAO.NguoiDungDAO;
import com.example.bookstorephuongnam.Modal.NguoiDung;

public class SignUpActivity extends AppCompatActivity {

    EditText edtUsername, edtEmail, edtPassword, edTConfirmPass;
    NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //View
        edtUsername = findViewById(R.id.edUsername);
        edtEmail = findViewById(R.id.edEmail);
        edtPassword = findViewById(R.id.edPassword);
        edTConfirmPass = findViewById(R.id.edConFirmPassword);

    }

    @Override
    public void onBackPressed() {
        onStop();
        super.onBackPressed();
    }

    public void retrunSignUp(View view) {
        finish();
    }

    public void btn_signup(View view) {
        String username_ = edtUsername.getText().toString();
        String email_ = edtEmail.getText().toString();
        String password_ = edtUsername.getText().toString();
        String confirmPass_ = edTConfirmPass.getText().toString();

        NguoiDung nd = new NguoiDung(username_, password_, email_);
        if (username_.isEmpty() || password_.isEmpty() || email_.isEmpty() || confirmPass_.isEmpty()) {
            edtUsername.setError("Username is empty!");
            edtPassword.setError("Password is empty!");
            edtEmail.setError("Email is empty!");
            edTConfirmPass.setError("Confirm password is empty!");
        } else {
            if (nguoiDungDAO.checkUser(username_)) {
                Toast.makeText(this, "Username already exist!", Toast.LENGTH_SHORT).show();
            } else {
                if (password_.equals(confirmPass_)) {
                    nguoiDungDAO.inserNguoiDung(nd);
                    Toast.makeText(getApplicationContext(), "Sign up successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this, TrangChinhActivity.class));
                    finish();
                } else
                    Toast.makeText(this, "Sign up failed!", Toast.LENGTH_SHORT).show();
            }
        }

    }

}