package com.utils.datatype;

public class dataType {
    public static void main(String[] args) {
        //test(11);
        //test2();
    }
    public static void test(Object obj){
        String cla=obj.getClass().toString();
        cla=cla.substring(cla.lastIndexOf(".")+1);
        switch (cla){
            case "Integer":System.out.println("整数");break;
        }
    }
    public static void test2(){
        int i=11;
        Object o=i;
        int j=Integer.parseInt(o.toString());
        System.out.println(j);
        System.out.println(o instanceof Integer);
    }
}
