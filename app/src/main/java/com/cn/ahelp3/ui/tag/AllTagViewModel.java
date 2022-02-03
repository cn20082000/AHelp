package com.cn.ahelp3.ui.tag;

import com.cn.ahelp3.common.App;
import com.cn.ahelp3.core.base.BaseViewModel;
import com.cn.ahelp3.core.util.Lg;

public class AllTagViewModel extends BaseViewModel<AllTagNavigation> {

    public void getAllTag() {
        App.dataManager.getAllTag()
                .whenSuccess(data -> nav.getAllTagSuccess(data))
                .whenFailure(error -> Lg.ar(error.getStatusCode(), error.getMessage()))
                .call();
    }
}
