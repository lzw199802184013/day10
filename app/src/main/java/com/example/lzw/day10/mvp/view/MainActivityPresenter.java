package com.example.lzw.day10.mvp.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lzw.day10.R;
import com.example.lzw.day10.bean.JsonToBean;
import com.example.lzw.day10.bean.Utils;
import com.example.lzw.day10.model.LoginBean;
import com.example.lzw.day10.mvp.IView.ADeleGate;
import com.example.lzw.day10.net.Helper;
import com.example.lzw.day10.net.Http;

public class MainActivityPresenter extends ADeleGate implements View.OnClickListener {
    private EditText user_name;
    private EditText user_pass;
    private SharedPreferences sp;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        super.initData();
        user_name = (EditText) get(R.id.user_name);
        user_pass = (EditText) get(R.id.user_pass);
        setClick(this, R.id.login, R.id
                .register);

    }

    @Override
    public void onClick(View view) {

        //pass=Utils.mD5(pass);//密码md5加密
        switch (view.getId()) {
            case R.id.login:
                doLogin();
                break;
            case R.id.register:
                doRegister();
                break;
        }
    }

    private void doRegister() {
        context.startActivity(new Intent(context, RegisterActivity.class));
    }

    //登录
    private void doLogin() {
        String name = user_name.getText().toString().trim();
        String pass = user_pass.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            toast(context, "账号不能为空");
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            toast(context, "密码不能为空");
            return;
        }
        new Helper().get(Http.LOGING_URL + "?mobile=" + name + "&password=" + pass).result(new Helper.HttpListener() {
            @Override
            public void success(String data) {
                LoginBean bean = JsonToBean.jsonToBean(data, LoginBean.class);
                if ("1".equals(bean.getCode())) {
                    toast(context, "账号密码错误");
                } else {
                    //toast(data);
                    //登录成功，之后，保存账号信息，然后跳转到成功1页面
                    saveUserData(bean);
                    context.startActivity(new Intent(context, HomeActivity.class));
                    ((MainActivity) context).finish();//销毁页面

                }

            }

            @Override
            public void fail() {

            }
        });

    }

    //保存用户数据
    private void saveUserData(LoginBean bean) {
        sp = context.getSharedPreferences("user", context.MODE_PRIVATE);
        sp.edit().putString("username", bean.getData().getUsername())
                .putString("password", bean.getData().getPassword())
                .putString("nickname", (String) bean.getData().getNickname())
                .commit();
    }

    private Context context;

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

}
