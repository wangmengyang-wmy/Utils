package com.utils.random;

import java.security.SecureRandom;
import java.util.Base64;

public class Random {
    /**
     * 根据种子产生伪随机数，种子相同生成的随机数相同
     */
    public static void randomTest(){
        java.util.Random random=new java.util.Random(1);
        for (int i = 0; i < 10; i++) {
            int ran1 = random.nextInt(10);
            System.out.println(ran1);
        }
    }

    /**
     * Math.random()生成[0.0,1.0）的double型数值，
     */
    public static void math_randomTest(){
        for (int i = 0; i < 5; i++) {
            double d=Math.random();
            System.out.println(d);
        }
        int max=100,min=1;
//        int ran=(int) (Math.random()*(max)); //生成[0,100)的整数随机数
        System.out.println(0.9963637403296901*max);
        System.out.println((int)(0.9973637403296901*max));
        int ran2 = (int) (Math.random()*(max)+min);//生成[1,101)==[1,100]的整数随机数
        System.out.println(ran2);
    }

    public static void secureRandomTest(){
        byte[] values = new byte[128];
        SecureRandom secureRandom=new SecureRandom();
        //secureRandom.setSeed(System.currentTimeMillis());
        secureRandom.nextBytes(values);
        String encoded = Base64.getEncoder().encodeToString(values);
        System.out.println(encoded);
        System.out.println(encoded.length());
    }
}
