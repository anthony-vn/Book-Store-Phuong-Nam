package com.example.bookstorephuongnam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookstorephuongnam.DAO.NguoiDungDAO;

public class LoginActivity extends AppCompatActivity {
    EditText edUserName, edPassword;
    CheckBox chk_Rem;
    String strUser, strPass;
    NguoiDungDAO nguoiDungDAO;
    TextView tv_forgotPass, tv_signUp;

    ProgressBar simpleProgressBar;
    private long backPressedTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        anhXa();

    }

    public void checkLogin(View v) {
        strUser = edUserName.getText().toString();
        strPass = edPassword.getText().toString();
        if (strUser.isEmpty() || strPass.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Tên đăng nhập và mật khẩu không được bỏ trống",
                    Toast.LENGTH_SHORT).show();
        } else {
            if (strUser.equalsIgnoreCase("admin") && strPass.equalsIgnoreCase("admin")) {
                rememberUser(strUser, strPass, chk_Rem.isChecked());
                finish();
                try {
                    Thread.sleep(2000);

//                    simpleProgressBar.setBackgroundColor(getColor(R.color.colorBlack));
//                    simpleProgressBar.setVisibility(View.VISIBLE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Tên đăng nhập và mật khẩu không đúng",
                        Toast.LENGTH_SHORT).show();
            }
        }
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

    public void rememberUser(String u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status) {
            //xoa tinh trang luu tru truoc do
            edit.clear();
        } else {
            //luu du lieu
            edit.putString("USERNAME", u);
            edit.putString("PASSWORD", p);
            edit.putBoolean("REMEMBER", status);
        }
        //luu lai toan bo
        edit.commit();
    }

    private void anhXa() {
        tv_forgotPass = findViewById(R.id.tv_forgotPassword);
        tv_signUp = findViewById(R.id.tv_signUp);
        chk_Rem = findViewById(R.id.chkRememberMe);
        edUserName = findViewById(R.id.edt_username);
        edPassword = findViewById(R.id.edt_password);
//        simpleProgressBar = findViewById(R.id.progress_bar);
    }
}