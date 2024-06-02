package com.example.myapplicationl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private TextView txttieude;
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
        lv = (item_2.findViewById(R.id.lv));
        txttieude = item_2.findViewById(R.id.txttieude);
        try {
            baseManager = new DataBaseManager(getContext());
            sanPhamArrayList = baseManager.loadds();
            AdapterSanPham adapter = new AdapterSanPham(getContext(),sanPhamArrayList);
            lv.setAdapter(adapter);


        } catch (Exception ex) {
            txttieude.setText("Lỗi: " + ex.getMessage());
        }

    }

}
