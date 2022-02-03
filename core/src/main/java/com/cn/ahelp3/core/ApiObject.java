package com.cn.ahelp3.core;

import com.cn.ahelp3.core.action.FailureAction;
import com.cn.ahelp3.core.action.SuccessAction;
import com.cn.ahelp3.core.model.ErrorMessage;
import com.cn.ahelp3.core.model.ResponseObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiObject<T> {

    private final Call<ResponseObject<T>> caller;
    private SuccessAction<T> onSuccess;
    private FailureAction onFailure;

    public ApiObject(Call<ResponseObject<T>> caller) {
        this.caller = caller;
        onSuccess = data -> {};
        onFailure = t -> {};
    }

    public ApiObject<T> whenSuccess(SuccessAction<T> onSuccess) {
        this.onSuccess = onSuccess;
        return this;
    }

    public ApiObject<T> whenFailure(FailureAction onFailure) {
        this.onFailure = onFailure;
        return this;
    }

    public void call() {
        caller.enqueue(new Callback<ResponseObject<T>>() {
            @Override
            public void onResponse(Call<ResponseObject<T>> call, Response<ResponseObject<T>> response) {
                if (response.isSuccessful()) {
                    onSuccess.execute(response.body().getData());
                } else {
                    try {
                        JsonObject obj = new JsonParser().parse(response.errorBody().string())
                                .getAsJsonObject()
                                .getAsJsonObject("error");
                        ErrorMessage error = new Gson().fromJson(obj, ErrorMessage.class);
                        onFailure.execute(error);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseObject<T>> call, Throwable t) {
                ErrorMessage error = new ErrorMessage();
                error.setStatusCode(-1);
                error.setMessage(t.getLocalizedMessage());
                onFailure.execute(error);
            }
        });
    }
}
