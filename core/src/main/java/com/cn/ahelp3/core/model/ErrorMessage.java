package com.cn.ahelp3.core.model;

import com.google.gson.annotations.SerializedName;

public class ErrorMessage {

    @SerializedName("statusCode")
    private int statusCode;

    @SerializedName("message")
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
