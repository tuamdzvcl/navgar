package com.example.myapplicationl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class fragment_item2 extends Fragment {
    private  View item_2 ;
    private TextView txttieude,txtthanhtoan,txthuydon,txttong;
    private  DataBaseManager baseManager;
    private ArrayList<SanPham> sanPhamArrayList;
    private ListView lv;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return item_2=inflater.inflate(R.layout.fragment_item_2, container, false) ;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       Anhxa();
       loaddanhsach();
       lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               SanPham sp = sanPhamArrayList.get(position);
               String tenSP = sp.getTenSp();
               float soLuong = getSoLuong(tenSP);
               float gia = getGia(tenSP);
               float tong = soLuong * gia;
               txttong.setText(String.valueOf(tong));
           }

       });
    }
    private float getSoLuong(String TenSP) {
        ArrayList<SanPham> sanPhamList = baseManager.loadds();
        for (SanPham sp : sanPhamList) {
            if (sp.getTenSp().equals(TenSP)) {
                return Integer.parseInt(sp.getSoLuong()); // Trả về số lượng cũ
            }
        }
        return 0;
        
    }private float getGia(String TenSP) {
        ArrayList<SanPham> sanPhamList = baseManager.loadds();
        for (SanPham sp : sanPhamList) {
            if (sp.getTenSp().equals(TenSP)) {
                return Float.parseFloat(sp.getGiaSP()); // Trả về  giá sp
            }
        }
        return 0;
    }
    private void loaddanhsach() {
        try {
            baseManager = new DataBaseManager(getContext());
            sanPhamArrayList = baseManager.loadds();
            AdapterSanPham adapter = new AdapterSanPham(getContext(),sanPhamArrayList);
            lv.setAdapter(adapter);

        } catch (Exception ex) {
            txttieude.setText("Lỗi: " + ex.getMessage());
        }

    }

    private void Anhxa() {
        lv = (item_2.findViewById(R.id.lv));
        txttieude = item_2.findViewById(R.id.txttieude);
        txtthanhtoan = item_2.findViewById(R.id.txtthanhtoan);
        txthuydon = item_2.findViewById(R.id.txthuydon);
        txttong = item_2.findViewById(R.id.txttong);

    }

}
