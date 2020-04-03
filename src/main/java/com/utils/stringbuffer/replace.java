package com.utils.stringbuffer;

public class replace {
    public static void main(String[] args) {
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("1");
        stringBuffer.append(",");
        stringBuffer.append("1");
        stringBuffer.append(",");
        stringBuffer.append("1");
        stringBuffer.append(",");
        stringBuffer.replace(stringBuffer.length()-1,stringBuffer.length(),"");
        System.out.println(stringBuffer.toString());
    }
}
