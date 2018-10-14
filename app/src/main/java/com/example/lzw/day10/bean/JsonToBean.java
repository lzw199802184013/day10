package com.example.lzw.day10.bean;

import com.example.lzw.day10.model.IModel;
import com.google.gson.Gson;

public class JsonToBean {
    public  static  <D extends IModel> D jsonToBean(String json, Class cls){
            return (D) new Gson().fromJson(json,cls);

    }
}
