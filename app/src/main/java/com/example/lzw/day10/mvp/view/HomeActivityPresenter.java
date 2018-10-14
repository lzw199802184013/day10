package com.example.lzw.day10.mvp.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.lzw.day10.R;
import com.example.lzw.day10.mvp.IView.ADeleGate;

public class HomeActivityPresenter extends ADeleGate implements View.OnClickListener {


    private TextView user_name;
    private TextView user_pass;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initData() {
        super.initData();
            user_name=(TextView)get(R.id.user_name);
        user_pass=(TextView)get(R.id.user_pass);
        SharedPreferences sp=context.getSharedPreferences("user",context.MODE_PRIVATE);
        String username= sp.getString("username","");
        String password=sp.getString("password","");
        user_name.setText(username);
        user_pass.setText(password);
        setClick(this,R.id.tuichu);
    }

    private  Context context;
    @Override
    public void getContext(Context context) {
        this.context =context;
    }

    @Override
    public void onClick(View view) {
        SharedPreferences sp = context.getSharedPreferences("user", context.MODE_PRIVATE);
//        sp.edit().putString("username","")
//                .putString("password","")
//                .commit();
        sp.edit().clear();
        context.startActivity(new Intent(context,MainActivity.class));
    }
}
