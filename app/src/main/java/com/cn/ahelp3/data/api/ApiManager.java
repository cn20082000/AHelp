package com.cn.ahelp3.data.api;

import com.cn.ahelp3.core.ApiObject;
import com.cn.ahelp3.core.model.PagedObject;
import com.cn.ahelp3.data.model.Post;
import com.cn.ahelp3.data.model.PostDetail;
import com.cn.ahelp3.data.model.Tag;

import java.util.List;

public interface ApiManager {

    ApiObject<String> wakeUp(String message);
    ApiObject<PagedObject<Post>> lastUpdatedPost();
    ApiObject<PagedObject<Post>> allPost(int page);
    ApiObject<PagedObject<Post>> getPostByTag(int page, List<Tag> tags);
    ApiObject<PostDetail> getPost(Long id);
    ApiObject<List<Tag>> getAllTag();
}
