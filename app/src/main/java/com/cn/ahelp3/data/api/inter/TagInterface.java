package com.cn.ahelp3.data.api.inter;

import com.cn.ahelp3.core.model.ResponseObject;
import com.cn.ahelp3.data.model.Tag;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TagInterface {

    @GET("/api/blog/tag")
    Call<ResponseObject<List<Tag>>> getAllTag();
}
