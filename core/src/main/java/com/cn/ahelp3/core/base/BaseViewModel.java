package com.cn.ahelp3.core.base;

import androidx.lifecycle.ViewModel;

public class BaseViewModel<NA extends BaseNavigation> extends ViewModel {

    protected NA nav;

    public BaseViewModel() {
        setupModule();
    }

    public void setNav(NA nav) {
        this.nav = nav;
    }

    protected void setupModule() {}
}