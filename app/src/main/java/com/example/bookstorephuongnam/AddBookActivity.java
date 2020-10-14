package com.example.bookstorephuongnam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookstorephuongnam.Adapter.Adapter_Books;
import com.example.bookstorephuongnam.DAO.SachDAO;
import com.example.bookstorephuongnam.DAO.TheLoaiDAO;
import com.example.bookstorephuongnam.Modal.Sach;
import com.example.bookstorephuongnam.Modal.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class AddBookActivity extends AppCompatActivity {
    EditText edtMaSach, edtTenSach, edtTacGia, edtNHaXuatBan, edtGiaBia, edtSoLuong;
    Spinner sp_theloai;

    SachDAO sachDAO;
    TheLoaiDAO theLoaiDAO;
    Adapter_Books adapter_books;
    String maTheLoai = null;
    List<TheLoai> listTheLoai = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        anhxa();
        getTheLoai();

        //Show the loai to spiner
        sp_theloai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maTheLoai = listTheLoai.get(sp_theloai.getSelectedItemPosition()).getMaTheLoai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //load data into form
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edtMaSach.setText(b.getString("MASACH"));
            String maTheLoai = b.getString("MATHELOAI");
            edtTenSach.setText(b.getString("TENSACH"));
            edtNHaXuatBan.setText(b.getString("NXB"));
            edtTacGia.setText(b.getString("TACGIA"));
            edtGiaBia.setText(b.getString("GIABIA"));
            edtSoLuong.setText(b.getString("SOLUONG"));
            sp_theloai.setSelection(checkPositionTheLoai(maTheLoai));
        }
    }

    public void getTheLoai() {
        theLoaiDAO = new TheLoaiDAO(AddBookActivity.this);

        listTheLoai = theLoaiDAO.getAllTheLoai();
        ArrayAdapter<TheLoai> dataAdapter = new ArrayAdapter<TheLoai>(this,
                android.R.layout.simple_spinner_item, listTheLoai);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_theloai.setAdapter(dataAdapter);
    }

    public void returnAddBook(View view) {
        finish();
    }

    public void AddBooks(View view) {
        sachDAO = new SachDAO(AddBookActivity.this);
        Sach sach = new Sach(edtMaSach.getText().toString(), maTheLoai, edtTenSach.getText().toString(),
                edtTacGia.getText().toString(), edtNHaXuatBan.getText().toString(),
                Double.parseDouble(edtGiaBia.getText().toString()), Integer.parseInt(edtSoLuong.getText().toString()));
        try {
            if (sachDAO.inserSach(sach) > 0) {
                Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                finish();
                adapter_books.notifyDataSetChanged();
            } else {
                Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public int checkPositionTheLoai(String strTheLoai) {
        for (int i = 0; i < listTheLoai.size(); i++) {
            if (strTheLoai.equals(listTheLoai.get(i).getMaTheLoai())) {
                return i;
            }
        }
        return 0;
    }

    private void anhxa() {
        edtMaSach = findViewById(R.id.edt_masach);
        edtTenSach = findViewById(R.id.edt_tensach);
        edtTacGia = findViewById(R.id.edt_tacgia);
        edtNHaXuatBan = findViewById(R.id.edt_nhaxuatban);
        edtGiaBia = findViewById(R.id.edt_giabia);
        edtSoLuong = findViewById(R.id.edt_soluong);
        sp_theloai = findViewById(R.id.spnTheLoai);
    }
}