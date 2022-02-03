package com.cn.ahelp3.util.binding.adapter;

import android.os.Build;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

public class TextViewBindingAdapter {

    @BindingAdapter("ahelp:html")
    public static void setHtml(TextView tv, String str) {
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv.setText(Html.fromHtml(str, Html.FROM_HTML_MODE_COMPACT));
        } else {
            tv.setText(Html.fromHtml(str));
        }
    }
}
