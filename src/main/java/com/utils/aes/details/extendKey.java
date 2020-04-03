package com.utils.aes.details;

public class extendKey {

    //常量数组
    static String Rcon[] = {
            "01000000",  "02000000",
            "04000000",  "08000000",
            "10000000",  "20000000",
            "40000000",  "80000000",
            "1B000000",  "36000000"
    };
    //S盒
    static String SBox[][]= {
            {"63","7C","77","7B","F2","6B","6F","C5","30","01","67","2B","FE","D7","AB","76"},
            {"CA","82","C9","7D","FA","59","47","F0","AD","D4","A2","AF","9C","A4","72","C0"},
            {"B7","FD","93","26","36","3F","F7","CC","34","A5","E5","F1","71","D8","31","15"},
            {"04","C7","23","C3","18","96","05","9A","07","12","80","E2","EB","27","B2","75"},
            {"09","83","2C","1A","1B","6E","5A","A0","52","3B","D6","B3","29","E3","2F","84"},
            {"53","D1","00","ED","20","FC","B1","5B","6A","CB","BE","39","4A","4C","58","CF"},
            {"D0","EF","AA","FB","43","4D","33","85","45","F9","02","7F","50","3C","9F","A8"},
            {"51","A3","40","8F","92","9D","38","F5","BC","B6","DA","21","10","FF","F3","D2"},
            {"CD","0C","13","EC","5F","97","44","17","C4","A7","7E","3D","64","5D","19","73"},
            {"60","81","4F","DC","22","2A","90","88","46","EE","B8","14","DE","5E","0B","DB"},
            {"E0","32","3A","0A","49","06","24","5C","C2","D3","AC","62","91","95","E4","79"},
            {"E7","C8","37","6D","8D","D5","4E","A9","6C","56","F4","EA","65","7A","AE","08"},
            {"BA","78","25","2E","1C","A6","B4","C6","E8","DD","74","1F","4B","BD","8B","8A"},
            {"70","3E","B5","66","48","03","F6","0E","61","35","57","B9","86","C1","1D","9E"},
            {"E1","F8","98","11","69","D9","8E","94","9B","1E","87","E9","CE","55","28","DF"},
            {"8C","A1","89","0D","BF","E6","42","68","41","99","2D","0F","B0","54","BB","16"}
    };

    /**
     * 密钥拓展
     * @param original_key  原始密钥
     * @return  拓展过的密钥数组，用于下一步的与待加密内容异或
     */
    public static String[] extendKey(String original_key){
        //密钥数组
        String[] key=new String[44];
        int length=original_key.length();
        if(length==16||length==24||length==32){
            char[] original_keychars=original_key.toCharArray();
            for(int i = 0; i < 4; i++){
                //密钥原始二进制字符串
                key[i] = getWordFromStrArr(original_keychars,i);
            }
            for(int i = 4, j = 0; i < 44; i++) {
                if( i % 4 == 0) {//是4的倍数
                    key[i] = yh(key[i - 4] , T(key[i - 1], j));
                    j++;//标识与常量的加密轮数
                }else {//不是4的倍数
                    key[i] = yh(key[i - 4] , key[i - 1]);
                }
            }
        }else {
            System.out.println("原始密钥输入错误！长度必须是16、24、32的字符串！");
        }
        return key;
    }

    /**
     * 将原来的密钥字符串按照每4位为一组的顺序先转变为二进制然后存入拓展密钥数组的前四位
     * @param original_keychars  源密钥数组
     * @param j  标记每次存入拓展密钥数组的位置
     * @return  返回转换过后的二进制32位字符串
     */
    public static String getWordFromStrArr(char[] original_keychars, int j){
        String result="";
        for(int i=4*j;i<4*j+4;i++){
            String var1=Integer.toBinaryString(original_keychars[i]);
            int len=var1.length();
            while (len<8){
                var1="0"+var1;
                len++;
            }
            result +=var1;
            //java中char是16位，取值范围是[0, 65535]；int的取值范围[-2147483648,2147483647]
            //所以char类型的变量可以随便复制给int类型的变量:测试方法transform()
            //然后通过Integer.toBinaryString()方法将一个int型转变为String类型的二进制字符串
        }
        if (result.length()!=32){
            return null;
        }
        return result;
    }

    /**
     * 进行异或运算
     * @param str1  参数1，二进制字符串
     * @param str2  参数2，二进制字符串
     * @return  异或过后的二进制字符串
     */
    public static String yh(String str1,String str2){
        String result="";
        char chars1[]=str1.toCharArray();
        char chars2[]=str2.toCharArray();
        if (chars1.length>chars2.length){
            int c=chars1.length-chars2.length;
            for (int i = 0; i < c; i++) {
                if (chars1[i]=='1'){
                    result+="0";
                }else {
                    result+="1";
                }
            }
            for (int i = chars1.length-c; i <chars1.length ; i++) {
                if (chars1[i]==chars2[i]){
                    result+="0";
                }else {
                    result+="1";
                }
            }
        }else if (chars1.length<chars2.length){
            int c=chars2.length-chars1.length;
            for (int i = 0; i < c; i++) {
                if (chars2[i]=='1'){
                    result+="0";
                }else {
                    result+="1";
                }
            }
            for (int i = chars2.length-c; i <chars2.length ; i++) {
                if (chars1[i]==chars2[i]){
                    result+="0";
                }else {
                    result+="1";
                }
            }
        }else {
            for (int i = 0; i <chars1.length; i++) {
                if (chars1[i]==chars2[i]){
                    result+="0";
                }else {
                    result+="1";
                }
            }
        }
        return result;
    }
    /**
     * 密钥扩展中的T函数
     */
    public static String T(String var1,int j) {
        //字循环
        var1=var1.substring(8)+var1.substring(0,8);

        //字节代换
        getNumFromSBox(var1);

        //与轮常量Rcon异或
        Rconyh(var1,j);
        return var1;
    }
    public static void Rconyh(String var1,int j){
        String temp=Rcon[j];
        String temp3="";
        for (int i = 0; i <temp.length() ; i++) {
            String temp2=Integer.toBinaryString(Integer.parseInt(temp.substring(i,i+1),16));
            int len=temp2.length();
            while (len<4){
                temp2="0"+temp2;
                len++;
            }
            temp3+=temp2;
        }
        String temp4= yh(var1,temp3);
        var1=temp4;
    }
    public static void getNumFromSBox(String var1){
        int len=var1.length()/4;
        int numArr[]=new int[len];
        //先将二进制字符串安装每四位为一组得出在S盒中的坐标值
        for (int i = 0; i < len; i++) {
            String var2=var1.substring(4*i,i*4+4);
            numArr[i]=Integer.parseInt(var2,2);
        }
        String result[]=new String[4];//存储从S盒中取出的16进制的字符串
        for (int i = 0; i <len ; i+=2) {
            int row=numArr[i];
            int clu=numArr[i+1];
            result[i/2]=SBox[row][clu];
        }
        //再将取出的16进制字符串转换为二进制字符串，以供后续运算
        for (int i = 0; i <result.length ; i++) {
            String str=result[i];
            String temp2="";
            for (int j = 0; j <str.length() ; j++) {
                int temp=Integer.parseInt(str.substring(j,j+1),16);
                temp2=Integer.toBinaryString(temp);
                int len2=temp2.length();
                while (len2<4){
                    temp2="0"+temp2;
                    len2++;
                }
            }
            result[i]=temp2;//转换为对应的二进制字符串
        }
        String temp3="";
        for (int i = 0; i <result.length ; i++) {
            temp3+=result[i];
        }
        var1=temp3;
    }
}
