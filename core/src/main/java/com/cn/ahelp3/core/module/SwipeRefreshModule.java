package com.cn.ahelp3.core.module;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public abstract class SwipeRefreshModule {

    public SwipeRefreshModule() {
        refreshLayout().setOnRefreshListener(this::update);
        trigger();
    }

    public void done() {
        refreshLayout().post(() -> {
            refreshLayout().setRefreshing(false);
        });
    }

    public void trigger() {
        refreshLayout().post(() -> {
            refreshLayout().setRefreshing(true);
            update();
        });
    }

    protected abstract SwipeRefreshLayout refreshLayout();

    protected abstract void update();
}
