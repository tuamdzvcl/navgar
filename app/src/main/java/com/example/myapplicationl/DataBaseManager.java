package com.example.myapplicationl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DataBaseManager {
    private DataHeapr csdl;
    private Context context;
    private SQLiteDatabase database;

    public DataBaseManager(Context ctx) {

        csdl = new DataHeapr(ctx);
    }
    public  ArrayList<SanPham> loadds(){
        String sql = "SELECT * FROM QLBH";
        ArrayList<SanPham> arrayList = new ArrayList<>();
        SQLiteDatabase db = csdl.getReadableDatabase(); // Get readable database
        Cursor cursor = db.rawQuery(sql, null); // Execute query
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            SanPham SP = new SanPham();
            SP.setTenSp(cursor.getString(0));
            SP.setSoLuong(cursor.getString(1));
            SP.setGiaSP(cursor.getString(2));
            arrayList.add(SP);
            cursor.moveToNext();
        }
        return arrayList;

    }
public void insert(SanPham SP){

    SQLiteDatabase db = csdl.getWritableDatabase(); // Get readable database
    ContentValues values = new ContentValues();
    values.put("tenSP",SP.getTenSp() );
    values.put("SoLuong", SP.getSoLuong());
    values.put("Giá", SP.getGiaSP());
    values.put("Hinh", SP.getHinh());
    db.insert("QLBH", null, values);
    db.close();



}


    public void close(){
        csdl.close();
    }
}


