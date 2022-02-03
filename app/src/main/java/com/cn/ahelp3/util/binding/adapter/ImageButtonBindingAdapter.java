package com.cn.ahelp3.util.binding.adapter;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.databinding.BindingAdapter;

public class ImageButtonBindingAdapter {

    @BindingAdapter("app:srcCompat")
    public static void setImageResource(AppCompatImageButton button, int resourceId) {
        button.setImageResource(resourceId);
    }
}
