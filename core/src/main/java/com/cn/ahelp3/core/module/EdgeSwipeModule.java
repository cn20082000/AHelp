package com.cn.ahelp3.core.module;

import android.view.View;

import com.cn.ahelp3.core.listener.OnEdgeSwipeListener;

public abstract class EdgeSwipeModule {

    public EdgeSwipeModule() {
        setup();
    }

    private void setup() {
        view().setOnTouchListener(onEdgeSwipeListener());
    }

    protected abstract View view();

    protected abstract OnEdgeSwipeListener onEdgeSwipeListener();
}
