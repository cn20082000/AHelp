package com.cn.ahelp3.ui.tag.singleTag;

import com.cn.ahelp3.core.base.BaseNavigation;
import com.cn.ahelp3.core.model.PagedObject;
import com.cn.ahelp3.data.model.Post;

public interface SingleTagNavigation extends BaseNavigation {
    void getPostByTagSuccess(PagedObject<Post> paged);
}
