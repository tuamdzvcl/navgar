package com.example.myapplicationl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterSanPham extends BaseAdapter {

    private Context context;
    private int layout ;
    private List<SanPham> arraylist;

    public AdapterSanPham(Context context, int layout, List<SanPham> arraylist) {
        this.context = context;
        this.layout = layout;
        this.arraylist = arraylist;
    }

    @Override
    public int getCount() {
        return arraylist.size();
    }

    @Override
    public Object getItem(int position) {
        return arraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(layout, parent, false);
        }

        SanPham sanPham = arraylist.get(position);
        TextView textView = convertView.findViewById(R.id.name);
        TextView textView1 = convertView.findViewById(R.id.gia);
        TextView textView2 = convertView.findViewById(R.id.soluong);
        ImageView imageView = convertView.findViewById(R.id.image4);

        textView.setText(sanPham.getTenSp());
        textView1.setText(String.valueOf(sanPham.getGiaSP()));
        textView2.setText(String.valueOf(sanPham.getSoLuong()));
        imageView.setImageResource(sanPham.getHinh());

        return convertView;
    }
}
