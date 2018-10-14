package com.example.lzw.day10.mvp.view;

import com.example.lzw.day10.mvp.presenter.ActivityPresenter;

public class MainActivity extends ActivityPresenter<MainActivityPresenter> {
    @Override
    public Class<MainActivityPresenter> getClassDeleGate() {
        return MainActivityPresenter.class;
    }
}
