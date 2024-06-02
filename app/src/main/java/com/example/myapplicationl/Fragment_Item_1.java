package com.example.myapplicationl;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import java.util.List;

public class Fragment_Item_1 extends Fragment {
    private View viewItem1;

    TextView txtttieude;
    EditText edittensp, editsl, editgia,editimg;
    Button btninsert, btnupdate, btndelete;


    private  DataBaseManager baseManager;
    private ArrayList<SanPham> sanPhamArrayList;
    private ListView lv;
    private List<SanPham> SPList;
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
        editimg = viewItem1.findViewById(R.id.editimg);
        baseManager = new DataBaseManager(getContext());
        loaddanhsach();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SanPham sp = sanPhamArrayList.get(position);
                edittensp.setText(sp.getTenSp());
                editsl.setText(sp.getSoLuong());
                editgia.setText(sp.getGiaSP());
                edittensp.setText(sp.getTenSp());
                editimg.setText(sp.getHinh()+" ") ;

            }
        });
        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TenSP = edittensp.getText().toString();
                String Sl = editsl.getText().toString();
                String Gia = editgia.getText().toString();
                String hinh = editimg.getText().toString();
                int hinh1 = Integer.parseInt(hinh);
                SanPham sp = new SanPham(TenSP,Sl,Gia,hinh1);
               baseManager.insert(sp);
               loaddanhsach();
               Toast.makeText(getContext(),"Thêm Thành Công ",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loaddanhsach() {

        sanPhamArrayList = baseManager.loadds();
        AdapterSanPham adapter = new AdapterSanPham(getContext(),sanPhamArrayList);
        lv.setAdapter(adapter);
        }

}
