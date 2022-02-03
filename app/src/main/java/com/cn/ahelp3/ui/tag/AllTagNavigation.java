package com.cn.ahelp3.ui.tag;

import com.cn.ahelp3.core.base.BaseNavigation;
import com.cn.ahelp3.data.model.Tag;

import java.util.List;

public interface AllTagNavigation extends BaseNavigation {

    void getAllTagSuccess(List<Tag> list);
}
