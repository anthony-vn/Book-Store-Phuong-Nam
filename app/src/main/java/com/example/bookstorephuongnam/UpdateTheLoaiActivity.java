package com.example.bookstorephuongnam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookstorephuongnam.Adapter.Adapter_TheLoai;
import com.example.bookstorephuongnam.DAO.TheLoaiDAO;
import com.example.bookstorephuongnam.Modal.TheLoai;

import static com.example.bookstorephuongnam.TheLoaiActivity.dsTheLoai;

public class UpdateTheLoaiActivity extends AppCompatActivity {
    EditText edtMaTheLoai, edtTenTheLoai, edtViTri, edtMoTa;
    TheLoaiDAO theLoaiDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_theloai);

        edtMaTheLoai = findViewById(R.id.edtUpdate_matheloai);
        edtTenTheLoai = findViewById(R.id.edtUpdate_tentheloai);
        edtViTri = findViewById(R.id.edtUpdate_vitri);
        edtMoTa = findViewById(R.id.edtUpdate_mota);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edtMaTheLoai.setText(b.getString("MATHELOAI"));
            edtTenTheLoai.setText(b.getString("TENTHELOAI"));
            edtMoTa.setText(b.getString("MOTA"));
            edtViTri.setText(b.getString("VITRI"));
        }
    }

    public void UpdateTheLoai(View view) {
        TheLoai theLoai = new TheLoai(edtMaTheLoai.getText().toString(), edtTenTheLoai.getText().toString(),
                edtMoTa.getText().toString(), Integer.parseInt(edtViTri.getText().toString()));
        theLoaiDAO = new TheLoaiDAO(getApplicationContext());
        theLoaiDAO.updateTheLoai(theLoai);
        Toast.makeText(getApplicationContext(), "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
        finish();
    }
    public void returnThemTheLoai(View view) {
        finish();
    }

}