package com.utils.objectoriented.impl;

import com.utils.objectoriented.interfaceInterface;

public class interfaceClass implements interfaceInterface {
    String test;
    public interfaceClass (String str){
        this.test=str;
    }
    @Override
    public String getOriginalData() {
        return test;
    }
}
