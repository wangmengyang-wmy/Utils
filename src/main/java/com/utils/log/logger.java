package com.utils.log;

import java.io.IOException;
import java.util.logging.*;

public class logger {
    public static void main(String[] args) {
        //根据字符串参数的哈希值算出存储位置，并用logger对象指向这个地址（或者就是初始化的logger对象的地址）
        Logger logger=Logger.getLogger("1");
        logger.setLevel(Level.INFO);// 所以同样的字符串会有同样的哈希值，即指向的是同一片存储位置，亦即同一个对象
//        Logger logger1=Logger.getLogger("1");
//        logger1.setLevel(Level.WARNING);
//        Logger logger2=Logger.getLogger("2");
//        logger2.setLevel(Level.WARNING);

        ConsoleHandler consoleHandler=new ConsoleHandler();//控制台操作者
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);

        FileHandler fileHandler= null;
        try {
            fileHandler = new FileHandler("./today.log");//会覆盖文件内容
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileHandler.setLevel(Level.INFO);
        fileHandler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return record.getLevel() + ":" + record.getMessage() + "\n";
            }
        });
        logger.addHandler(fileHandler);

        logger.info("info2");
        logger.warning("warning2");
        //System.out.println(logger==logger2);
    }
}
