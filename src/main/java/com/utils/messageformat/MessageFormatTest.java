package com.utils.messageformat;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.*;

public class MessageFormatTest {

    public static final Properties p = new Properties();

    public static final String path = "message.properties";

    public static void main(String[] args) {

//        System.out.println(new Date());

//        String message = "oh, {0} not is a person";
//        String format="特朗普";
//        String value = MessageFormat.format(message, format);
//        System.out.println(value);


//        String message = "oh, {0,number,#.#} is a number";
//        Object[] array = new Object[]{new Double(3.1415)};
//        double format=3.1415;
//        String value = MessageFormat.format(message, format);
//        System.out.println(value); // oh, 3.1 is a number

//        String str = "| {1} | {0} | {1}";
//        Object[] array = new Object[] { "A", "B" };
//        String value = MessageFormat.format(str, array);
//        System.out.println(value); // A | B | A | B

//        read_properties();



//        String format="oh, {0} not is a person, his jiji just {1,number,#.#} cm, I will fuck his daughter from {2,date} to {3} everytime";
//        Object []array=new Object[]{"特朗普",new Double("3.1415"),new Date(),"forever"};
//        String value=MessageFormat.format(format,array);
//        System.out.println(value);

        //初始化Properties对象
        MessageFormatTest.init();


        String key="formats";
        Object []array=new Object[]{"Donald Trump",new Double("1.10101"),new Date(),"forever"};
        System.out.println(update(key,array));

        list();
    }

    public static void read_properties(){
        Properties prop = new Properties();
        InputStream in = MessageFormatTest.class.getClassLoader().getResourceAsStream("message.properties");
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String source=prop.getProperty("formats");
        String format="特朗普";
        String value = MessageFormat.format(source, format);
        System.out.println(value);
    }

    /**
     * 通过类装载器 初始化Properties
     */
    public static void init() {
        //转换成流
        InputStream inputStream = MessageFormatTest.class.getClassLoader().getResourceAsStream(path);
        try {
            //从输入流中读取属性列表（键和元素对）
            p.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * 通过key获取value
     * @param key
     * @return
     */
    public static String get(String key) {
        return p.getProperty(key);
    }

    /**
     * 按照配置文件中的key修改整条数据或者新增一条数据
     * @param key
     * @param value
     */
    public static void update(String key, String value) {
        p.setProperty(key, value);
        FileOutputStream oFile = null;
        try {
            oFile = new FileOutputStream(path);
            //将Properties中的属性列表（键和元素对）写入输出流
            p.store(oFile, "");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 替换配置文件莫一条数据中的占位符（部分）数据
     * @param key
     * @param array
     */
    public static boolean update(String key, Object []array) {
        boolean flag=false;
        String format=(String) p.get(key);

        String value=MessageFormat.format(format,array);

        p.setProperty(key, value);

        FileOutputStream oFile = null;
        try {
            oFile = new FileOutputStream("./src/main/resources/"+path);
            //将Properties中的属性列表（键和元素对）写入输出流
            p.store(oFile, "");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            flag=true;
        }
        return flag;
    }

    /**
     * 通过key删除value
     * @param key
     */
    public static void delete(String key) {
        p.remove(key);
        FileOutputStream oFile = null;
        try {
            oFile = new FileOutputStream(path);
            p.store(oFile, "");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 循环所有key value
     */
    public static void list() {
        Enumeration en = p.propertyNames(); //得到配置文件的名字
        while(en.hasMoreElements()) {
            String strKey = (String) en.nextElement();
            String strValue = p.getProperty(strKey);
            System.out.println(strKey + "=" + strValue);
        }
    }

}
