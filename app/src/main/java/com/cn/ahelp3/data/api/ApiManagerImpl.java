package com.cn.ahelp3.data.api;

import com.cn.ahelp3.common.App;
import com.cn.ahelp3.core.ApiObject;
import com.cn.ahelp3.core.model.PagedObject;
import com.cn.ahelp3.data.api.inter.ApiInterface;
import com.cn.ahelp3.data.api.inter.PostInterface;
import com.cn.ahelp3.data.api.inter.TagInterface;
import com.cn.ahelp3.data.model.Post;
import com.cn.ahelp3.data.model.PostDetail;
import com.cn.ahelp3.data.model.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ApiManagerImpl implements ApiManager {

    private ApiInterface api = App.retrofit.create(ApiInterface.class);
    private PostInterface post = App.retrofit.create(PostInterface.class);
    private TagInterface tag = App.retrofit.create(TagInterface.class);

    @Override
    public ApiObject<String> wakeUp(String message) {
        return new ApiObject<>(api.wakeUp(message));
    }

    @Override
    public ApiObject<PagedObject<Post>> lastUpdatedPost() {
        return new ApiObject<>(post.lastUpdatePost());
    }

    @Override
    public ApiObject<PagedObject<Post>> allPost(int page) {
        return new ApiObject<>(post.allPost(page));
    }

    @Override
    public ApiObject<PagedObject<Post>> getPostByTag(int page, List<Tag> tags) {
        List<Long> tagsId = new ArrayList<>();
        for (Tag tag : tags) {
            tagsId.add(tag.getId());
        }
        return new ApiObject<>(post.postByTag(page, tagsId));
    }

    @Override
    public ApiObject<PostDetail> getPost(Long id) {
        return new ApiObject<>(post.getPost(id));
    }

    @Override
    public ApiObject<List<Tag>> getAllTag() {
        return new ApiObject<>(tag.getAllTag());
    }
}
