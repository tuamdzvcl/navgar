package com.example.myapplicationl;

public class SanPham {
    private String TenSp;
    private String SoLuong;
    private String giaSP;
    private int hinh;

    public SanPham(String tenSp, String soLuong, String giaSP, int hinh) {
        TenSp = tenSp;
        SoLuong = soLuong;
        this.giaSP = giaSP;
        this.hinh = hinh;
    }

    public String getTenSp() {
        return TenSp;
    }

    public void setTenSp(String tenSp) {
        TenSp = tenSp;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String soLuong) {
        SoLuong = soLuong;
    }

    public String getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(String giaSP) {
        this.giaSP = giaSP;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }
}