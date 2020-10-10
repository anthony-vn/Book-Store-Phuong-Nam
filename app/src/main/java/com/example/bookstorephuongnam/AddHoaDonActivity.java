package com.example.bookstorephuongnam;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookstorephuongnam.DAO.HoaDonDAO;
import com.example.bookstorephuongnam.Modal.HoaDon;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddHoaDonActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText edtMaHoaDon, edtNgayMua;
    ImageView imgPicdate;

    HoaDonDAO hoaDonDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hoa_don);
        edtMaHoaDon = findViewById(R.id.edMaHoaDon);
        edtNgayMua = findViewById(R.id.edNgayMua);
        imgPicdate = findViewById(R.id.picDate);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }

    private void setDate(final Calendar calendar) {

        edtNgayMua.setText(sdf.format(calendar.getTime()));
    }

    public void datePicker(View view) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getFragmentManager(), "date");
    }

    public void returnAddHoaDon(View view) {
        finish();
    }

    public void NextToHoaDonChiTietActivity(View view) {
        hoaDonDAO = new HoaDonDAO(AddHoaDonActivity.this);

        try {
            if (validation() < 0) {
                Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                HoaDon hoaDon = new HoaDon(edtMaHoaDon.getText().toString(), sdf.parse(edtNgayMua.getText().toString()));
                if (hoaDonDAO.inserHoaDon(hoaDon) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddHoaDonActivity.this, HoaDonChiTietActivity.class);
                    Bundle b = new Bundle();
                    b.putString("MAHOADON", edtMaHoaDon.getText().toString());
                    intent.putExtras(b);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }

        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public int validation() {
        if (edtMaHoaDon.getText().toString().isEmpty() || edtNgayMua.getText().toString().isEmpty()) {
            return -1;
        }
        return 1;
    }

    public static class DatePickerFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(),
                    (DatePickerDialog.OnDateSetListener)
                            getActivity(), year, month, day);
        }
    }
}