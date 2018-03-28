package com.nelson.sign.utils;

public class StringUtil {

    public static Boolean isNotBlank(String str){
        if(str==null || "".equals(str)){
            return false;
        }
        return true;
    }

}
