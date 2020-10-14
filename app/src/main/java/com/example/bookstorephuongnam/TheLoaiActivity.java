package com.example.bookstorephuongnam;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookstorephuongnam.Adapter.Adapter_TheLoai;
import com.example.bookstorephuongnam.DAO.TheLoaiDAO;
import com.example.bookstorephuongnam.Modal.TheLoai;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiActivity extends AppCompatActivity {
    FloatingActionButton fab_them;
    ListView lvTheLoai;

    public static List<TheLoai> dsTheLoai = new ArrayList<>();
    Adapter_TheLoai adapter_theLoai = null;
    TheLoaiDAO theLoaiDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theloai);
        setTitle("Thể Loại");
        anhxa();

        registerForContextMenu(lvTheLoai);
        theLoaiDAO = new TheLoaiDAO(TheLoaiActivity.this);
        dsTheLoai = theLoaiDAO.getAllTheLoai();

        adapter_theLoai = new Adapter_TheLoai(this, dsTheLoai);
        lvTheLoai.setAdapter(adapter_theLoai);

        lvTheLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TheLoaiActivity.this, UpdateTheLoaiActivity.class);
                Bundle b = new Bundle();
                b.putString("MATHELOAI", dsTheLoai.get(position).getMaTheLoai());
                b.putString("TENTHELOAI", dsTheLoai.get(position).getTenTheLoai());
                b.putString("MOTA", dsTheLoai.get(position).getMoTa());
                b.putString("VITRI", String.valueOf(dsTheLoai.get(position).getViTri()));
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dsTheLoai.clear();
        dsTheLoai = theLoaiDAO.getAllTheLoai();
        adapter_theLoai.changeDataset(dsTheLoai);
    }

    private void anhxa() {
        fab_them = findViewById(R.id.fab_theloai);
        lvTheLoai = findViewById(R.id.lv_theloai);
    }

    public void callThemTheLoaiActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), AddTheLoaiActivity.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.fab_theloai), "transition_login");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(TheLoaiActivity.this);
        startActivity(intent, options.toBundle());
    }

    public void returnListBook(View view) {
        finish();
    }
}