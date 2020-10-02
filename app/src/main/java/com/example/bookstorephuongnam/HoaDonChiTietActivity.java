package com.example.bookstorephuongnam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class HoaDonChiTietActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);
    }

    public void AddHoaDonChiTiet(View view) {
    }

    public void ClickThanhToan(View view) {
    }

    public void returnThemHoaDon(View view) {
        finish();
    }
}