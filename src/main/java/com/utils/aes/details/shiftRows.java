package com.utils.aes.details;

/**
 * 加密时的行移位
 */
public class shiftRows {
    public static String shiftrows(String pwd){
        String row1=pwd.substring(0,32);
        String row2=pwd.substring(32,64);
        String row3=pwd.substring(64,96);
        String row4=pwd.substring(96);
        row2=row2.substring(8)+row2.substring(0,8);
        row3=row3.substring(16)+row3.substring(0,16);
        row4=row4.substring(32)+row4.substring(0,32);
        return row1+row2+row3+row4;
    }
}
