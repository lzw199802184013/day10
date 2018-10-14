package com.example.lzw.day10.mvp.view;

import com.example.lzw.day10.mvp.presenter.ActivityPresenter;

public class HomeActivity extends ActivityPresenter<HomeActivityPresenter> {
    @Override
    public Class<HomeActivityPresenter> getClassDeleGate() {
        return HomeActivityPresenter.class;
    }
}
