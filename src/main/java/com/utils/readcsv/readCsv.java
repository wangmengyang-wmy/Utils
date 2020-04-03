package com.utils.readcsv;

import org.jumpmind.symmetric.csv.CsvReader;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class readCsv {

    public static List<String[]> readcsv(String filepath){
        List<String[]> csvList = new ArrayList<>();
        try {
            CsvReader reader = new CsvReader(filepath,',', Charset.forName("utf-8"));
            reader.readHeaders(); //跳过表头,不跳可以注释掉
            while(reader.readRecord()){
                csvList.add(reader.getValues()); //按行读取，每行会返回一个String数组对象，并把每一行的数据添加到list集合
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
     return csvList;
    }

}
