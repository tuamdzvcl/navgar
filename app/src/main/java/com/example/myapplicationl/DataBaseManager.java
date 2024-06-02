package com.example.myapplicationl;

import android.app.DownloadManager;
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

    public ArrayList<SanPham> loadds() {
        String sql = "SELECT * FROM QLBH";
        ArrayList<SanPham> arrayList = new ArrayList<>();
        SQLiteDatabase db = csdl.getReadableDatabase(); // Get readable database
        Cursor cursor = db.rawQuery(sql, null); // Execute query
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            SanPham SP = new SanPham();
            SP.setTenSp(cursor.getString(0));
            SP.setSoLuong(cursor.getString(1));
            SP.setGiaSP(cursor.getString(2));
            SP.setHinh(cursor.getInt(3));
            arrayList.add(SP);
            cursor.moveToNext();
        }
        return arrayList;

    }

    public void deleteTest(SanPham sp) {


    }

    public int insert(SanPham SP) {

        SQLiteDatabase db = csdl.getWritableDatabase(); // Get readable database
        ContentValues values = new ContentValues();
        values.put("TenSP", SP.getTenSp());
        values.put("SoLuong", SP.getSoLuong());
        values.put("GiaSP", SP.getGiaSP());
        values.put("Hinh", SP.getHinh());
        try {
            long result = db.insert("QLBH", null, values);
            db.close();
            if (result > 0) return 1;
            return -1;
        } catch (Exception ex) {
            db.close();
            return -1;
        }
    }

    public void update(SanPham SP) {
        SQLiteDatabase db = csdl.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("SoLuong", SP.getSoLuong());
        values.put("GiaSP", SP.getGiaSP());
        values.put("Hinh", SP.getHinh());
        String whereClause = "TenSP=?";
        String[] whereArgs = {SP.getTenSp()};

        // Thực hiện cập nhật dữ liệu
        db.update("QLBH", values, whereClause, whereArgs);

        // Đóng kết nối đến cơ sở dữ liệu
        db.close();
    }
public void delete(SanPham SP)
{
    SQLiteDatabase db = csdl.getWritableDatabase(); // Lấy database có thể ghi

    // Điều kiện để xóa dữ liệu, trong trường hợp này, chúng ta xóa dữ liệu cho sản phẩm có tên sản phẩm giống với tên sản phẩm của sản phẩm được truyền vào.
    String whereClause = "TenSP=?";
    String[] whereArgs = {SP.getTenSp()};

    // Thực hiện xóa dữ liệu
    db.delete("QLBH", whereClause, whereArgs);

    // Đóng kết nối đến cơ sở dữ liệu
    db.close();
}
//public  void create(String sql){
//    String query = sql;
//    ArrayList<SanPham> arrayList = new ArrayList<>();
//    SQLiteDatabase db = csdl.getReadableDatabase(); // Get readable database
//    Cursor cursor = db.rawQuery(query, null); // Execute query
//    cursor.moveToFirst();
//    while (!cursor.isAfterLast()) {
//        SanPham SP = new SanPham();
//        SP.setTenSp(cursor.getString(0));
//        SP.setSoLuong(cursor.getString(1));
//        SP.setGiaSP(cursor.getString(2));
//        SP.setHinh(cursor.getInt(3));
//        arrayList.add(SP);
//        cursor.moveToNext();
//    }
//    return create("");

//}
    public void close(){
        csdl.close();
    }
}



