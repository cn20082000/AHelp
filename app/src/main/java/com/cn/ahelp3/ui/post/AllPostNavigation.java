package com.cn.ahelp3.ui.post;

import com.cn.ahelp3.core.base.BaseNavigation;
import com.cn.ahelp3.core.model.PagedObject;
import com.cn.ahelp3.data.model.Post;

public interface AllPostNavigation extends BaseNavigation {

    void getAllPostSuccess(PagedObject<Post> paged);
    void scrollTop();
}
