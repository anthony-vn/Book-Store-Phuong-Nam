package com.example.bookstorephuongnam;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookstorephuongnam.Adapter.Adapter_Books;
import com.example.bookstorephuongnam.DAO.SachDAO;
import com.example.bookstorephuongnam.Modal.Sach;

import java.util.ArrayList;
import java.util.List;

public class ListBookActivity extends AppCompatActivity {
    public static List<Sach> dsSach = new ArrayList<>();
    ListView lvBook;
    Adapter_Books adapter = null;
    SachDAO sachDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_book);
        lvBook = (ListView) findViewById(R.id.lv_books);

        sachDAO = new SachDAO(ListBookActivity.this);
        dsSach = sachDAO.getAllSach();

        adapter = new Adapter_Books(this, dsSach);
        lvBook.setAdapter(adapter);
        lvBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Sach sach = (Sach) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListBookActivity.this, AddBookActivity.class);
                Bundle b = new Bundle();
                b.putString("MASACH", sach.getMaSach());
                b.putString("MATHELOAI", sach.getMaTheLoai());
                b.putString("TENSACH", sach.getTenSach());
                b.putString("TACGIA", sach.getTacGia());
                b.putString("NXB", sach.getNXB());
                b.putString("GIABIA", String.valueOf(sach.getGiaBia()));
                b.putString("SOLUONG", String.valueOf(sach.getSoLuong()));
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        // TextFilter
        lvBook.setTextFilterEnabled(true);
        EditText edSeach = (EditText) findViewById(R.id.edSearchBook);
        edSeach.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("Text [" + s + "] - Start [" + start + "] - Before [" + before + "] - Count [" + count + "]");
                if (count < before) {
                    adapter.resetData();
                }
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dsSach.clear();
        dsSach = sachDAO.getAllSach();
        adapter.changeDataset(dsSach);
    }

    public void callThemBookActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), AddBookActivity.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.fab_book), "transition_book");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ListBookActivity.this);
        startActivity(intent, options.toBundle());
    }

    public void returnListBook(View view) {
        finish();
    }
}