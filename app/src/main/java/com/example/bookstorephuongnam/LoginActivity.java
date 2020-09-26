package com.example.bookstorephuongnam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookstorephuongnam.DAO.NguoiDungDAO;

public class LoginActivity extends AppCompatActivity {
    CheckBox chk_Rem;
    TextView tv_signUp, tv_forgotPass;
    EditText username, password;
    Button btnLogin;

    private long backPressedTime;
    NguoiDungDAO nguoiDungDAO;
    String strUser, strPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        anhXa();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin(view);
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(getBaseContext(), "Nhấn lần nữa để thoát!", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    private void anhXa() {
        tv_forgotPass = findViewById(R.id.tv_forgotPassword);
        tv_signUp = findViewById(R.id.tv_signUp);
        chk_Rem = findViewById(R.id.chkRememberMe);
        username = findViewById(R.id.edt_username);
        password = findViewById(R.id.edt_password);
    }

    public void checkLogin(View view) {
        strUser = username.getText().toString();
        strPass = password.getText().toString();
        if (strUser.isEmpty() || strPass.isEmpty()) {
            username.setError("Username không được để trống!");
            password.setError("Password không được để trống!");
        } else {
            if (strUser.equalsIgnoreCase("admin") && strPass.equalsIgnoreCase("admin")) {
                rememberUser(strUser, strPass, chk_Rem.isChecked());
                Intent intent = new Intent(LoginActivity.this, TrangChinhActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Tên đăng nhập và mật khẩu không đúng",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void rememberUser(String u, String p, boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status){
            //xoa tinh trang luu tru truoc do
            edit.clear();
        }else {
            //luu du lieu
            edit.putString("USERNAME",u);
            edit.putString("PASSWORD",p);
            edit.putBoolean("REMEMBER",status);
        }
        //luu lai toan bo
        edit.commit();
    }
}