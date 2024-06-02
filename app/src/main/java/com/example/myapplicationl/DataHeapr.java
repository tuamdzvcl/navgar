package com.example.myapplicationl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataHeapr extends SQLiteOpenHelper {
    private Context context;
    public DataHeapr(@Nullable Context con) {
        super(con, "qlbh.sqlite", null, 1);
        this.context = con;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS QLBH(TenSP text primary key, " +
                "SoLuong text," +
                "GiaSP text," +
                "Hinh int" +
                ");";
        db.execSQL(sqlCreate);
        String insert = "insert into QLBH(TenSP,SoLuong,GiaSP,Hinh) values('Mì','20','15',1)";
        String insert2 = "insert into QLBH(TenSP,SoLuong,GiaSP,Hinh) values('Mì5','20','15',1)";
        System.out.println("đã thêm " );// Closing parenthesis added
        db.execSQL(insert2);
        db.execSQL(insert);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}