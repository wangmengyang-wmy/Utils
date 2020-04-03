package com.utils.aes.details;

/**
 * 轮密钥加
 */
public class addRoundKey {
    /**
     * 轮密钥加
     * @param pwd  密码
     * @param key  密钥
     * @param round  轮数
     * @return  轮密钥加的结果
     */
    public static String addroundkey(String pwd,String []key,int round){
        char pwdchars[]=pwd.toCharArray();
        String newpwd="";
        for(int i=0;i<pwdchars.length;i++){
            String var1=Integer.toBinaryString(pwdchars[i]);
            int len=var1.length();
            while (len<8){
                var1="0"+var1;
                len++;
            }
            newpwd +=var1;
        }
        if (newpwd.length()!=128){
            return null;
        }
        String temp="";
        for (int i = 4*round; i <4*round+4; i++) {
            temp+=key[i];
        }
        return extendKey.yh(newpwd,temp);
    }
}
