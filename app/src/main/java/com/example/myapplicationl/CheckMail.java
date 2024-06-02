package com.example.myapplicationl;

public class CheckMail {

    public static boolean checkMail(String  email) {

        if(email.endsWith("@sv.vnua.edu.vn") || email.endsWith("@gv.vnua.edu.vn"))
            return true;

        return false;
    }
}
