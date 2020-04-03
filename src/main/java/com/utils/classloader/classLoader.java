package com.utils.classloader;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class classLoader {
    public static void main(String[] args) {

        System.out.println(classLoader.class.getClassLoader().getClass().getName());
        ClassLoader cl=ClassLoader.getSystemClassLoader();
        ClassLoader cl2=classLoader.class.getClassLoader();
        Class<?> clz=classLoader.class;
        System.out.println(cl.getClass().getName());//类加载器的全局限定名
        System.out.println(cl2.getClass().getName());
        System.out.println(clz.getName());



        InputStream io= null;
        try {
            io = new FileInputStream("src/main/rs/rule2.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(io);



        InputStream is=classLoader.class.getClassLoader().getResourceAsStream("rule1.json");
        String input=null;
        try {
            input= IOUtils.toString(is, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(input);

        String path=classLoader.class.getClassLoader().getResource("rule1.json").getPath();
        System.out.println(path);
        InputStream io2= null;
        try {
            io2 = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("io2:"+io2);

        System.out.println(classLoader.class.getClassLoader().getResource("rules.json").getPath());

    }
}
