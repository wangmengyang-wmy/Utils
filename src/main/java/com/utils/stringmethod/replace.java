package com.utils.stringmethod;

import org.apache.commons.lang3.StringUtils;

public class replace {
    public static void main(String[] args) {
        String str="sAMAccount=${name}${name}${name}";
        System.out.println(str.replace("${name}","123"));
        System.out.println(StringUtils.replace(str,"${name}","123"));
    }
}
