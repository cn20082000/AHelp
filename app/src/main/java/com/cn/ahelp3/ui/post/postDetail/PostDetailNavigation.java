package com.cn.ahelp3.ui.post.postDetail;

import com.cn.ahelp3.core.base.BaseNavigation;
import com.cn.ahelp3.data.model.PostDetail;

public interface PostDetailNavigation extends BaseNavigation {

    void getPostSuccess(PostDetail postDetail);
    void scrollTop();
}
