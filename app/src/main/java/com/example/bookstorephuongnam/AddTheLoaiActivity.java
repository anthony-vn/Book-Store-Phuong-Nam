package com.example.bookstorephuongnam;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookstorephuongnam.DAO.TheLoaiDAO;
import com.example.bookstorephuongnam.Modal.TheLoai;

public class AddTheLoaiActivity extends AppCompatActivity {
    EditText edtMaTheLoai, edtTenTheLoai, edtViTri, edtMoTa;
    TextView tvTitle;
    Button btnAdd;
    TheLoaiDAO theLoaiDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_theloai);
        anhxa();
        theLoaiDAO = new TheLoaiDAO(AddTheLoaiActivity.this);
    }

    public void AddTheLoai(View view) {
        try {
            if (validation() < 0) {
                Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                TheLoai theLoai = new TheLoai(edtMaTheLoai.getText().toString(), edtTenTheLoai.getText().toString(),
                        edtMoTa.getText().toString(), Integer.parseInt(edtViTri.getText().toString()));
                if (theLoaiDAO.inserTheLoai(theLoai) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại!", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            Log.e("Error : ", e.toString());
        }
    }

    private int validation() {
        int check = 1;
        if (edtMaTheLoai.getText().length() == 0 || edtTenTheLoai.getText().length() == 0
                || edtViTri.getText().length() == 0 || edtMoTa.getText().length() == 0) {
            check = -1;
        }
        return check;
    }

    private void anhxa() {
        edtMaTheLoai = findViewById(R.id.edt_matheloai);
        edtTenTheLoai = findViewById(R.id.edt_tentheloai);
        edtViTri = findViewById(R.id.edt_vitri);
        edtMoTa = findViewById(R.id.edt_mota);
        tvTitle = findViewById(R.id.tv_titleAddTheLoai);
        btnAdd = findViewById(R.id.btnAddTheLoai);
    }

    public void returnThemTheLoai(View view) {
        finish();
    }

}