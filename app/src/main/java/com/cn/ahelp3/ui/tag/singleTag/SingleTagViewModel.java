package com.cn.ahelp3.ui.tag.singleTag;

import androidx.databinding.ObservableField;

import com.cn.ahelp3.common.App;
import com.cn.ahelp3.core.base.BaseViewModel;
import com.cn.ahelp3.core.util.Lg;
import com.cn.ahelp3.data.model.Tag;

import java.util.ArrayList;
import java.util.List;

public class SingleTagViewModel extends BaseViewModel<SingleTagNavigation> {

    public ObservableField<String> content = new ObservableField<>("");

    public void getPostByTag(Tag tag) {
        content.set("\"" + tag.getDetail() + "\"");
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        App.dataManager.getPostByTag(0, tags)
                .whenSuccess(data -> {
                    nav.getPostByTagSuccess(data);
                })
                .whenFailure(error -> Lg.ar(error.getStatusCode(), error.getMessage()))
                .call();
    }
}
