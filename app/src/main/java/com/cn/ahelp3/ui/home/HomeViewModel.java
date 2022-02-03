package com.cn.ahelp3.ui.home;

import com.cn.ahelp3.common.App;
import com.cn.ahelp3.core.base.BaseViewModel;
import com.cn.ahelp3.core.util.Lg;

public class HomeViewModel extends BaseViewModel<HomeNavigation> {

    public void getLastUpdatedPost() {
        App.dataManager.lastUpdatedPost()
                .whenSuccess(data -> nav.getLastUpdatedPostSuccess(data.getContent()))
                .whenFailure(error -> Lg.ar(error.getStatusCode(), error.getMessage()))
                .call();
    }

    public void openAllPost() {
        nav.openAllPost();
    }
}
