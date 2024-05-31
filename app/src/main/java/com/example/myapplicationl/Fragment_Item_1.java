package com.example.myapplicationl;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Fragment_Item_1 extends Fragment {
    private View viewItem1;

    TextView txtttieude;
    EditText edittensp, editsl, editgia;
    Button btninsert, btnupdate, btndelete;
    ListView lv;
    SQLiteDatabase db;
    ArrayList<String> SP = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return viewItem1 = inflater.inflate(R.layout.fragment_item_1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtttieude = viewItem1.findViewById(R.id.txttieude);
        edittensp = viewItem1.findViewById(R.id.edttensp);
        editsl = viewItem1.findViewById(R.id.editsl);
        editgia = viewItem1.findViewById(R.id.editgia);
        btninsert = viewItem1.findViewById(R.id.btninsert);
        btnupdate = viewItem1.findViewById(R.id.btnUpdate);
        btndelete = viewItem1.findViewById(R.id.btndelete);
        lv = viewItem1.findViewById(R.id.lv);

        loaddanhsach();

        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tensp = edittensp.getText().toString();
                String soluong = editsl.getText().toString();
                String gia = editgia.getText().toString();
                String msg = "";

                if (tensp.isEmpty() || soluong.isEmpty() || gia.isEmpty()) {
                    msg = "Hãy nhập đầy đủ thông tin";
                } else {
                    try {
                        db = SQLiteDatabase.openDatabase("/data/data/com.example.myapplicationl/QuanLyBanHang", null, SQLiteDatabase.CREATE_IF_NECESSARY);
                        db.execSQL("INSERT INTO tblQLBH (tenSP, SoLuong, Gia) VALUES ('" + tensp + "', '" + soluong + "', '" + gia + "');");
                        msg = "Thêm thành công: " + tensp + " - " + soluong + " - " + gia;
                        clear();
                        loaddanhsach();
                    } catch (SQLException ex) {
                        txtttieude.setText("Lỗi: " + ex.getMessage());
                    } finally {
                        if (db != null && db.isOpen()) {
                            db.close();
                        }
                    }
                }
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loaddanhsach() {
        SP.clear();
        try {
            db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.myapplicationl/QuanLyBanHang", null);
            db.execSQL("CREATE TABLE IF NOT EXISTS tblQLBH (tenSP TEXT PRIMARY KEY, SoLuong TEXT, Gia TEXT)");
            Cursor c1 = db.rawQuery("SELECT * FROM tblQLBH", null);
            if (c1.moveToFirst()) {
                do {
                    String tensp = c1.getString(0);
                    String soluong = c1.getString(1);
                    String gia = c1.getString(2);
                    SP.add(tensp + " - " + soluong + " - " + gia);
                } while (c1.moveToNext());
            }
            c1.close();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, SP);
            lv.setAdapter(adapter);
        } catch (SQLException e) {
            txtttieude.setText("Lỗi: " + e.getMessage());
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }

    private void clear() {
        edittensp.setText("");
        editsl.setText("");
        editgia.setText("");
    }
}
