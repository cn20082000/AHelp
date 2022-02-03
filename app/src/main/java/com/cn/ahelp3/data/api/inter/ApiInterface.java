package com.cn.ahelp3.data.api.inter;

import com.cn.ahelp3.core.model.ResponseObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/api")
    Call<ResponseObject<String>> wakeUp(@Query("message") String message);
}
