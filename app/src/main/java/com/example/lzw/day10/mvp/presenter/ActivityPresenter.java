package com.example.lzw.day10.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.lzw.day10.mvp.IView.ADeleGate;

public abstract class ActivityPresenter<T extends ADeleGate> extends AppCompatActivity {

    private  T deleGate;

    public abstract  Class<T> getClassDeleGate();
    public  ActivityPresenter(){
        try {
            deleGate=getClassDeleGate().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deleGate.create(getLayoutInflater(),null,savedInstanceState);
        setContentView(deleGate.rootView());
        deleGate.getContext(this);
        deleGate.initData();

    }
    public void  initData(){


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        deleGate.destoryView();
        deleGate=null;
    }
}
