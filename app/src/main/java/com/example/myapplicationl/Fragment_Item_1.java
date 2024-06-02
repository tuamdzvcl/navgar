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
    private AdapterSanPham adapter;
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
        AdapterSanPham adapter = new AdapterSanPham(getContext(), sanPhamArrayList);

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
                editimg.setText(sp.getHinh() + "");

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
                if (CheckGiaTien.checkGiaTien(Gia) == false) {
                    Toast.makeText(getContext(), "Sai định dạng tiền", Toast.LENGTH_SHORT).show();
                    return;
                    }
                    SanPham sp = new SanPham(TenSP, Sl, Gia, hinh1);
                    try {
                        int check = baseManager.insert(sp);
                        if (check > 0) {
                            loaddanhsach();
                            Toast.makeText(getContext(), "Thêm Thành Công ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Thêm Không Thành Công ", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        txtttieude.setText("lỗi" + e);
                    }

            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TenSP = edittensp.getText().toString();
                String Sl = editsl.getText().toString();
                String Gia = editgia.getText().toString();
                String hinh = editimg.getText().toString();
                int hinh1 = Integer.parseInt(hinh);
                SanPham sp = new SanPham(TenSP, Sl, Gia, hinh1);
                boolean isExist = checkTenSPExist(TenSP);
                if (!isExist) {
                    Toast.makeText(getContext(), "Sửa không thành công,Tên sản phẩm không tồn tại", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                    baseManager.delete(sp);
                    loaddanhsach();
                }

            }
            private boolean checkTenSPExist(String TenSP) {
                ArrayList<SanPham> sanPhamList = baseManager.loadds();
                for (SanPham sp : sanPhamList) {
                    if (sp.getTenSp().equals(TenSP)) {
                        return true;
                    }
                }
                return false;
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TenSP = edittensp.getText().toString();
                String Sl = editsl.getText().toString();
                String Gia = editgia.getText().toString();
                String hinh = editimg.getText().toString();
                int hinh1 = Integer.parseInt(hinh);
                boolean isExist = checkTenSPExist(TenSP);
                if (!isExist) {

                    Toast.makeText(getContext(), "Sửa không thành công: Tên sản phẩm không tồn tại", Toast.LENGTH_SHORT).show();
                } else {
                    int Slcu = getSoLuongCu(TenSP);
                    int SlMoi = Integer.parseInt(Sl) + Slcu;
                    SanPham sp = new SanPham(TenSP, String.valueOf(SlMoi), Gia, hinh1);
                    Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                    baseManager.update(sp);
                    loaddanhsach();
                }
            }
            private boolean checkTenSPExist(String TenSP) {
                ArrayList<SanPham> sanPhamList = baseManager.loadds();
                for (SanPham sp : sanPhamList) {
                    if (sp.getTenSp().equals(TenSP)) {
                        return true; // Tên sản phẩm đã tồn tại trong cơ sở dữ liệu
                    }
                }
                return false; // Tên sản phẩm không tồn tại trong cơ sở dữ liệu
            }
            private int getSoLuongCu(String TenSP) {
                ArrayList<SanPham> sanPhamList = baseManager.loadds();
                for (SanPham sp : sanPhamList) {
                    if (sp.getTenSp().equals(TenSP)) {
                        return Integer.parseInt(sp.getSoLuong()); // Trả về số lượng cũ
                    }
                }
                return 0;
            }

        });

    }
    public void loaddanhsach() {

        sanPhamArrayList = baseManager.loadds();
        adapter = new AdapterSanPham(getContext(),sanPhamArrayList);
        lv.setAdapter(adapter);
        }


}

