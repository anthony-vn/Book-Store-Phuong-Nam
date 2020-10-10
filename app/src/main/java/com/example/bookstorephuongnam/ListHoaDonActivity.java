package com.example.bookstorephuongnam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bookstorephuongnam.Adapter.HoaDonAdapter;
import com.example.bookstorephuongnam.DAO.HoaDonDAO;
import com.example.bookstorephuongnam.Modal.HoaDon;

import java.util.ArrayList;
import java.util.List;

public class ListHoaDonActivity extends AppCompatActivity {
    public List<HoaDon> dsHoaDon = new ArrayList<>();
    ListView lvHoaDon;
    HoaDonAdapter adapter = null;
    HoaDonDAO hoaDonDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hoa_don);
        setTitle("HOÁ ĐƠN");
        lvHoaDon = (ListView) findViewById(R.id.lv_hoadon);
        hoaDonDAO = new HoaDonDAO(ListHoaDonActivity.this);
        try {
            dsHoaDon = hoaDonDAO.getAllHoaDon();
        } catch (Exception e) {
            Log.d("Error: ", e.toString());
        }

        adapter = new HoaDonAdapter(this, dsHoaDon);
        lvHoaDon.setAdapter(adapter);
        lvHoaDon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HoaDon hoaDon = (HoaDon) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListHoaDonActivity.this, ListHoaDonChiTietByIDActivity.class);
                Bundle b = new Bundle();
                b.putString("MAHOADON", hoaDon.getMaHoaDon());
                intent.putExtras(b);
                try {
                    startActivity(intent);
                }catch (Exception e){
                    Log.e("TAG", "Need fix error : " + e);
                    Toast.makeText(ListHoaDonActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // TextFilter
        lvHoaDon.setTextFilterEnabled(true);
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
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(ListHoaDonActivity.this, AddHoaDonActivity.class);
                startActivity(intent);
                return(true);
        }
        return super.onOptionsItemSelected(item);
    }

    public void callThemHoaDonActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), AddHoaDonActivity.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.fab_hoadon), "transition_CallAddHoaDon");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ListHoaDonActivity.this);
        startActivity(intent, options.toBundle());
    }
    public void returnHoaDonActivity(View view) {
        finish();
    }
}