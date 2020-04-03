package com.utils.file;

import java.io.File;
import java.io.IOException;

public class newDirectory {
    public static void main(String[] args) {
        String insert="result/insert.sql";
        String update="result/update.sql";
        String select="result/select.sql";
        String delete="result/delete.sql";
        make(insert);
        make(update);
        make(select);
        make(delete);
    }
    public static void make(String fileName){
        String prefix=fileName.substring(0,fileName.lastIndexOf("/"));
        File dir=new File(prefix);
        File file=new File(fileName);
        if (dir.exists()) {
            System.out.println(dir+":目录存在");
            if (file.exists()&&file.isFile()){
                System.out.println(fileName+":文件存在");
            }else {
                try {
                    System.out.println(fileName+":文件不存在");
                    if (file.createNewFile()) {
                        System.out.println(fileName+":文件创建");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            System.out.println(dir+":目录不存在");
            if (dir.mkdirs()){
                System.out.println(dir+":目录创建");
                try {
                    System.out.println(fileName+":文件创建");
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
