package com.utils.stringmethod;

public class substring {
    public static void main(String[] args) {
//        String str="https://m.sephora.cn/campaign/shu20200102/?utm_source=weiboXJQ&utm_medium=cooperation&utm_campaign=shu&utm_content=shu202001";
//        int mobile=str.indexOf("m.sephora.cn");
//        System.out.println(mobile);
        String str="https://www.sephora.cn/weeklyspecials/?utm_source=youlan&utm_medium=dsp&utm_campaign=weeklyspecials&utm_content=xinwen&utm_term=D";
        String str2="https://www.sephora.cn/login";
        if(str.indexOf("?")>0){
            System.out.println(str.substring(0,str.indexOf("?")));
        }else {
            System.out.println(str);
        }
        if(str2.indexOf("?")>0){
            System.out.println(str2.substring(0,str2.indexOf("?")));
        }else{
            System.out.println(str2);
        }

    }
}
