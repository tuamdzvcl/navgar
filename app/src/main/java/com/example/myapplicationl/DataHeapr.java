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
        String sqlCreate = "CREATE TABLE IF NOT EXISTS QLBH(TenSP text primary key, " + "SoLuong text," + "GiaSP text," + "Hinh int" + ");";
        db.execSQL(sqlCreate);

//         String insert2 = "insert into QLBH(TenSP,SoLuong,GiaSP,Hinh) values('Mì5','20','15',1)";
//         String insert3 = "insert into QLBH(TenSP,SoLuong,GiaSP,Hinh) values('Mì56','20','15',2)";
//         String insert4 = "insert into QLBH(TenSP,SoLuong,GiaSP,Hinh) values('Mì567','20','15',1)";
//         String insert5 = "insert into QLBH(TenSP,SoLuong,GiaSP,Hinh) values('Mì568','20','15',2)";
//         String insert6 = "insert into QLBH(TenSP,SoLuong,GiaSP,Hinh) values('Mì569','20','15',0)";
//        db.execSQL(insert2);
//        db.execSQL(insert4);
//        db.execSQL(insert5);
//        db.execSQL(insert6);
//        db.execSQL(insert3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}