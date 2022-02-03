package com.cn.ahelp3.core.model;

import com.google.gson.annotations.SerializedName;

public class ResponseObject<T> {

    @SerializedName("data")
    private T data;

    @SerializedName("errorMessage")
    private ErrorMessage errorMessage;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}
