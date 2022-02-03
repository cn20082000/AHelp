package com.cn.ahelp3.ui.home;

import com.cn.ahelp3.core.base.BaseNavigation;
import com.cn.ahelp3.data.model.Post;

import java.util.List;

public interface HomeNavigation extends BaseNavigation {

    void getLastUpdatedPostSuccess(List<Post> list);
    void openAllPost();
}
