package com.utils.aes.details;

public class mixColumns {
    static String [][]colM={
            {"2","3","1","1"},
            {"1","2","3","1"},
            {"1","1","2","3"},
            {"3","1","1","2"}
        };
    static int table[]=new int[256];
    static int arc_table[]=new int[256];
    static {
        table[0] = 1;//g^0
        for(int i = 1; i < 255; ++i)//生成元为x + 1
        {
            //下面是m_table[i] = m_table[i-1] * (x + 1)的简写形式
            table[i] = (table[i-1] << 1 ) ^ table[i-1];
            //最高指数已经到了8，需要模上m(x)
            if( (table[i] & 0x100)==0x100)
            {
                table[i] ^= 0x11B;//用到了前面说到的乘法技巧
            }
        }
        for(int i = 0; i < 255; ++i)
            arc_table[ table[i] ] = i;
    }
    public static void mixcolumns(String pwd){
        int round=0;
        String pwdArr[][]=new String[4][4];
        for (int i = 0; i <4 ; i++) {
            for (int j = 0; j <4 ; j++) {
                pwdArr[i][j]=pwd.substring(8*round,8*round+8);
                round++;
            }
        }
        String temp[]=new String[64];
        int temp_index=0;
        String resultArr[][]=new String[4][4];
        for (int i = 0; i <4 ; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k <4 ; k++) {
                    temp[temp_index]=cheng(pwdArr[i][k],colM[k][j]);
                    temp_index++;
                }
            }
        }
    }
    public static String cheng(String str1,String str2){
        return null;
    }
    public static int mul(int x, int y)
    {
        if( x==0 || y==0 )
            return 0;
        return table[ (arc_table[x] + arc_table[y]) % 255];
    }
    public static String yh(){
        return null;
    }
}
