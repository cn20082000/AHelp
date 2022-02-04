package com.cn.ahelp3.data;

import com.cn.ahelp3.core.ApiObject;
import com.cn.ahelp3.core.model.PagedObject;
import com.cn.ahelp3.data.api.ApiManager;
import com.cn.ahelp3.data.api.ApiManagerImpl;
import com.cn.ahelp3.data.model.Post;
import com.cn.ahelp3.data.model.PostDetail;
import com.cn.ahelp3.data.model.Tag;

import java.util.List;

public class DataManagerImpl implements DataManager {

    private final ApiManager api = new ApiManagerImpl();

    @Override
    public ApiObject<String> wakeUp(String message) {
        return api.wakeUp(message);
    }

    @Override
    public ApiObject<PagedObject<Post>> lastUpdatedPost() {
        return api.lastUpdatedPost();
    }

    @Override
    public ApiObject<PagedObject<Post>> allPost(int page) {
        return api.allPost(page);
    }

    @Override
    public ApiObject<PagedObject<Post>> getPostByTag(int page, List<Tag> tags) {
        return api.getPostByTag(page, tags);
    }

    @Override
    public ApiObject<PostDetail> getPost(Long id) {
        return api.getPost(id);
    }

    @Override
    public ApiObject<List<Tag>> getAllTag() {
        return api.getAllTag();
    }
}
