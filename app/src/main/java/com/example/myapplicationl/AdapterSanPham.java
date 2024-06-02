package com.example.myapplicationl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterSanPham extends BaseAdapter {

    private Context context;

    private ArrayList<SanPham> sanPhamArrayList;

    public AdapterSanPham(Context context, ArrayList<SanPham> data) {
        this.context = context;
        this.sanPhamArrayList =data;

    }

    @Override
    public int getCount() {
        return sanPhamArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoler viewHoler;
        if(convertView == null){

            viewHoler = new ViewHoler();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.sanpham,null);
            viewHoler.ivhinh=convertView.findViewById(R.id.image4);
            viewHoler.tvtensp=convertView.findViewById(R.id.TenSP);
            viewHoler.tvsl=convertView.findViewById(R.id.soluong);
            viewHoler.tvgia=convertView.findViewById(R.id.gia);
            convertView.setTag(viewHoler);



        }else
        {
            viewHoler =(ViewHoler) convertView.getTag();
        }
        SanPham sp = sanPhamArrayList.get(position);
        viewHoler.tvtensp.setText("Tên SP: " + sp.getTenSp());
        viewHoler.tvsl.setText("Số Lượng: " + sp.getSoLuong());
        viewHoler.tvgia.setText("Giá SP: " + sp.getGiaSP());

        if(sp.getHinh()==1)
        {
            viewHoler.ivhinh.setImageResource(R.drawable.bun);
        }
        else if (sp.getHinh()==2)
        {
            viewHoler.ivhinh.setImageResource(R.drawable.bia);
        }
        else
        {
            viewHoler.ivhinh.setImageResource(R.drawable.coca);
        }
        return convertView;
    }
    class ViewHoler {
        public ImageView ivhinh;
        public  TextView tvtensp,tvsl,tvgia;
    }
}
