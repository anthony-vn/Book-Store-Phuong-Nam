package com.example.bookstorephuongnam.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.bookstorephuongnam.Database.DatabaseHelper;
import com.example.bookstorephuongnam.LoginActivity;
import com.example.bookstorephuongnam.Modal.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class NguoiDungDAO {

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String TABLE_NAME = "NguoiDung";
    public static final String SQL_NGUOI_DUNG = "CREATE TABLE " +TABLE_NAME+
            "(username text primary key, password text, email text);";
    public static final String TAG = "NguoiDungDAO";

    public NguoiDungDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //insert
    public boolean inserNguoiDung(NguoiDung nd) {
        ContentValues values = new ContentValues();
        values.put("username", nd.getUsername());
        values.put("password", nd.getPassword());
        values.put("email", nd.getEmail());
        long result = db.insert(TABLE_NAME, null, values);
        if (result > 0){
            return true;
        }
        return false;
    }

    //getAll
    public List<NguoiDung> getAllNguoiDung() {
        List<NguoiDung> dsNguoiDung = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            NguoiDung ee = new NguoiDung();
            ee.setUsername(c.getString(0));
            ee.setPassword(c.getString(1));
            ee.setEmail(c.getString(2));
            dsNguoiDung.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsNguoiDung;

    }

    //update
    public int updateNguoiDung(NguoiDung nd) {
        ContentValues values = new ContentValues();
        values.put("username", nd.getUsername());
        values.put("password", nd.getPassword());
        values.put("email", nd.getEmail());
        int result = db.update(TABLE_NAME, values, "username=?", new String[]{nd.getUsername()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public int changePasswordNguoiDung(NguoiDung nd) {
        ContentValues values = new ContentValues();
        values.put("username", nd.getUsername());
        values.put("password", nd.getPassword());
        int result = db.update(TABLE_NAME, values, "username=?", new String[]{nd.getUsername()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public int updateInfoNguoiDung(String username, String email) {
        ContentValues values = new ContentValues();
        values.put("email", email);
        int result = db.update(TABLE_NAME, values, "username=?", new String[]{username});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    //delete
    public int deleteNguoiDungByID(String username) {
        int result = db.delete(TABLE_NAME, "username=?", new String[]{username});
        if (result == 0)
            return -1;
        return 1;
    }

    //check background_login
//    public int checkLogin(String username, String password) {
//        int result = db.delete(TABLE_NAME, "username=? AND password=?", new String[]{username, password});
//        if (result == 0)
//            return -1;
//        return 1;
//    }

    //check login
    public boolean checkUser(String strPrimaryKey) {
        //SELECT
        String[] columns = {"username"};
        //WHERE clause
        String selection = "username=?";
        //WHERE clause arguments
        String[] selectionArgs = {strPrimaryKey};
        Cursor c = null;
        try {
            c = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
            c.moveToFirst();
            int i = c.getCount();
            c.close();
            if (i <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
