package com.utils.getjsonresource;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * 利用classloader读取json资源文件，并将每个json初始化赋予json对象或者将全部json赋予json数组
 */
public class getJson {

    public static void main(String[] args) {
        getJson gj=new getJson();
        JSONObject jsonObject=gj.getJsonObject("rule1.json");
        System.out.println(jsonObject.get("pattern"));
        System.out.println("----------------------------------");
        JSONArray jsonArray=gj.getJsonArray("rules.json");
        for (int i = 0; i <jsonArray.size() ; i++) {
            JSONObject object=jsonArray.getJSONObject(i);
            System.out.println(object.get("pattern"));
        }
    }

    /**
     * 获取单个json对象由{}花括号包裹的部分
     * @param fileName
     * @return
     */
    public JSONObject getJsonObject(String fileName) {
        InputStream io =getJson.class.getClassLoader().getResourceAsStream(fileName);
        String input=null;
        JSONObject jsonObject = null;
        try {
            input= IOUtils.toString(io,"utf-8");
            jsonObject = JSONObject.parseObject(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //因为FileUtils.readFileToString方法本身就是利用流的方式获取文件的内容,所以不如直接使用流的方式
        return jsonObject;
    }

    /**
     * 获取json配置文件数组
     * @param fileName
     * @return
     */
    public JSONArray getJsonArray(String fileName) {
        InputStream io =getJson.class.getClassLoader().getResourceAsStream(fileName);
        String input=null;
        JSONArray jsonArray=null;
        try {
            input= IOUtils.toString(io,"utf-8");
            jsonArray=JSONArray.parseArray(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
}
