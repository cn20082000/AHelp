package com.cn.ahelp3.core.module;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.cn.ahelp3.core.action.ContentFunction;

public abstract class ContentWebViewModule {

    public ContentWebViewModule() {
        setupWV();
    }

    private void setupWV() {
        webView().getSettings().setJavaScriptEnabled(true);
        webView().setWebChromeClient(new WebChromeClient());
    }

    public void setContent(ContentFunction content) {
        webView().loadDataWithBaseURL(null, content.func(),
                "text/html; charset=utf-8", "UTF-8", null);
    }

    protected abstract WebView webView();
}
