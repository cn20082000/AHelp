package com.cn.ahelp3.ui.post;

import android.view.View;

import androidx.databinding.ObservableField;

import com.cn.ahelp3.R;
import com.cn.ahelp3.common.App;
import com.cn.ahelp3.core.base.BaseViewModel;
import com.cn.ahelp3.core.util.Lg;
import com.cn.ahelp3.util.binding.obj.VisibilityWAnimBindingObject;

public class AllPostViewModel extends BaseViewModel<AllPostNavigation> {

    private int page = 0;
    private boolean isShowTop = false;

    public ObservableField<VisibilityWAnimBindingObject> topVisi
            = new ObservableField<>(new VisibilityWAnimBindingObject.Builder()
            .setVisibility(View.GONE)
            .build());

    public void getAllPost() {
        App.dataManager.allPost(page)
                .whenSuccess(data -> {
                    nav.getAllPostSuccess(data);
                })
                .whenFailure(error -> Lg.ar(error.getStatusCode(), error.getMessage()))
                .call();
    }

    public void showTop() {
        if (!isShowTop) {
            isShowTop = true;
            topVisi.set(new VisibilityWAnimBindingObject.Builder()
                    .setVisibility(View.VISIBLE)
                    .setAnim(R.anim.med_float_in_up)
                    .build());
        }
    }

    public void hideTop() {
        if (isShowTop) {
            isShowTop = false;
            topVisi.set(new VisibilityWAnimBindingObject.Builder()
                    .setVisibility(View.GONE)
                    .setAnim(R.anim.med_dive_out_down)
                    .build());
        }
    }

    public void scrollTopClick() {
        nav.scrollTop();
    }
}
