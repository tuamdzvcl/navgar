package com.example.myapplicationl;

public class CheckGiaTien {
    public static boolean checkGiaTien(String Gia){
        if(Gia.endsWith(".000")){
            return true;
        }
        return false;
    }
}
