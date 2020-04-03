package com.utils.objectoriented.test;

import com.utils.objectoriented.impl.interfaceClass;
import com.utils.objectoriented.interfaceInterface;

public class interfaceTest {
    public static void main(String[] args) {
        interfaceInterface i=new interfaceClass("123");
        System.out.println(i.getOriginalData());
    }
}
