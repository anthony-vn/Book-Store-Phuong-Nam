package com.example.bookstorephuongnam;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookstorephuongnam.DAO.NguoiDungDAO;
import com.example.bookstorephuongnam.Modal.NguoiDung;

public class SignUpActivity extends AppCompatActivity {

    EditText edtUsername, edtPhone, edtPassword, edTConfirmPass;
    NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //View
        edtUsername = findViewById(R.id.edUsername);
        edtPhone = findViewById(R.id.edPhoneNumber);
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

        NguoiDung nd = new NguoiDung(edtUsername.getText().toString(), edtPassword.getText().toString(), edtPhone.getText().toString());
        if (edtUsername.getText().toString().isEmpty() || edtPhone.getText().toString().isEmpty() || edtPassword.getText().toString().isEmpty() || edTConfirmPass.getText().toString().isEmpty()) {
            edtUsername.setError("Username is empty!");
            edtPhone.setError("Phone numer is empty!");
            edtPassword.setError("Password is empty!");
            edTConfirmPass.setError("Confirm password is empty!");
        } else {
            if (nguoiDungDAO.checkUser(nd.getUserName())) {
                Toast.makeText(this, "Username already exist!", Toast.LENGTH_SHORT).show();
            } else {
                if (edtPassword.getText().toString().equals(edTConfirmPass.getText().toString())) {
                    nguoiDungDAO.inserNguoiDung(nd);
                    Toast.makeText(getApplicationContext(), "Sign up successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                } else
                    Toast.makeText(this, "Sign up failed!", Toast.LENGTH_SHORT).show();
            }
        }

    }

}