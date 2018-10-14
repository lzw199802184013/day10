package com.example.lzw.day10;

import com.example.lzw.day10.model.IModel;

public class TestBean implements IModel {
    private  String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
