package com.cn.ahelp3.ui.wakeup;

import androidx.databinding.ObservableField;

import com.cn.ahelp3.R;
import com.cn.ahelp3.common.App;
import com.cn.ahelp3.core.base.BaseViewModel;
import com.cn.ahelp3.core.util.Lg;

public class WakeUpViewModel extends BaseViewModel<WakeUpNavigation> {

    public ObservableField<String> loadingMessage = new ObservableField<>("");

    public void wakeServerUp() {
        loadingMessage.set(App.res.getString(R.string.wait_while_loading));
        App.dataManager.wakeUp("hello2")
                .whenSuccess(data -> {
                    nav.wakeServerUpSuccess();
                })
                .whenFailure(error -> {
                    Lg.ar(error.getStatusCode(), error.getMessage());
                    loadingMessage.set(App.res.getString(R.string.loading_fail));
                })
                .call();
    }
}
