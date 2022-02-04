package com.cn.ahelp3.data.api.inter;

import com.cn.ahelp3.core.model.PagedObject;
import com.cn.ahelp3.core.model.ResponseObject;
import com.cn.ahelp3.data.model.Post;
import com.cn.ahelp3.data.model.PostDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PostInterface {

    @GET("/api/blog/post/search?key=&page=0&size=5&isDesc=true&property=updatedAt")
    Call<ResponseObject<PagedObject<Post>>> lastUpdatePost();

    @GET("/api/blog/post/search?key=&size=20&isDesc=true&property=updatedAt")
    Call<ResponseObject<PagedObject<Post>>> allPost(@Query("page") int page);

    @GET("/api/blog/post/search?key=&size=20&isDesc=true&property=updatedAt")
    Call<ResponseObject<PagedObject<Post>>> postByTag(@Query("page") int page, @Query("tags") List<Long> tagsId);

    @GET("/api/blog/post")
    Call<ResponseObject<PostDetail>> getPost(@Query("id") Long id);
}
