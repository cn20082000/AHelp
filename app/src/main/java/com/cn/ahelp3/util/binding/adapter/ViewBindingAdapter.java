package com.cn.ahelp3.util.binding.adapter;

import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.databinding.BindingAdapter;

import com.cn.ahelp3.common.App;
import com.cn.ahelp3.util.binding.obj.VisibilityWAnimBindingObject;

public class ViewBindingAdapter {

    @BindingAdapter("ahelp:visibilityWithAnim")
    public static void setVisibilityWithAnim(View view, VisibilityWAnimBindingObject visibility) {
        switch (visibility.getVisibility()) {
            case View.VISIBLE: {
                view.setVisibility(View.VISIBLE);
                if (visibility.getAnim() != -1) {
                    view.startAnimation(AnimationUtils.loadAnimation(App.context, visibility.getAnim()));
                }
                break;
            }
            case View.INVISIBLE: {
                if (visibility.getAnim() != -1) {
                    view.startAnimation(AnimationUtils.loadAnimation(App.context, visibility.getAnim()));
                }
                view.setVisibility(View.INVISIBLE);
                break;
            }
            case View.GONE: {
                if (visibility.getAnim() != -1) {
                    view.startAnimation(AnimationUtils.loadAnimation(App.context, visibility.getAnim()));
                }
                view.setVisibility(View.GONE);
                break;
            }
            default: {
                break;
            }
        }
    }
}
