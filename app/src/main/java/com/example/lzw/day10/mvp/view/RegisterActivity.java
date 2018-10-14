package com.example.lzw.day10.mvp.view;

import com.example.lzw.day10.mvp.presenter.ActivityPresenter;

public class RegisterActivity  extends ActivityPresenter<RegisyterActivityPresenter>{
    @Override
    public Class<RegisyterActivityPresenter> getClassDeleGate() {
        return RegisyterActivityPresenter.class;
    }
}
