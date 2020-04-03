package com.utils.nanotime;

public class nanoTime {
    public static void main(String[] args) {
        long start=System.nanoTime();
        System.out.println("开始："+start);
        //System.out.println(System.currentTimeMillis());//获取距格林威治时间的时间戳，单位毫秒
        for (int i = 1; i <=9; i++) {
            for (int j = 1; j <=i; j++) {
                String result=String.valueOf(i*j);
                System.out.print(j+"*"+i+"="+result);
                if (result.length()==1){
                    System.out.print("     ");
                }else {
                    System.out.print("    ");
                }
            }
            System.out.println();
        }
        long end=System.nanoTime();
        System.out.println("结束："+end);
        System.out.println("用时："+(end-start)+"纳秒");
    }
}
