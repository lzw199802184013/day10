package com.example.lzw.day10.mvp.view;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.lzw.day10.R;
import com.example.lzw.day10.bean.JsonToBean;
import com.example.lzw.day10.model.IModel;
import com.example.lzw.day10.model.RegisterBean;
import com.example.lzw.day10.mvp.IView.ADeleGate;
import com.example.lzw.day10.net.Helper;
import com.example.lzw.day10.net.Http;

public class RegisyterActivityPresenter extends ADeleGate implements View.OnClickListener {
    private TextView user_name;
    private TextView user_pass;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initData() {
        super.initData();
        user_name = (TextView) get(R.id.user_name);
        user_pass = (TextView) get(R.id.user_pass);
        setClick(this, R.id.login);
    }

    private Context context;

    @Override
    public void getContext(Context context) {
        this.context = context;

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.login:
                doLogin();
                break;
        }
    }

    private void doLogin() {
        String name = user_name.getText().toString().trim();
        String pass = user_pass.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            toast(context, "请输入账号");
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            toast(context, "请输入密码");
            return;
        }
        new Helper().get("https://www.zhaoapi.cn/user/reg?mobile=" + name + "&password=" + pass).result(new Helper.HttpListener() {
            @Override
            public void success(String data) {
                RegisterBean bean = JsonToBean.jsonToBean(data, RegisterBean.class);
                if ("0".equals(bean.getCode())) {
                    toast(context, data);
                    context.startActivity(new Intent(context, MainActivity.class));
                    ((RegisterActivity) context).finish();
                }
            }

            @Override
            public void fail() {

            }
        });

    }


}
