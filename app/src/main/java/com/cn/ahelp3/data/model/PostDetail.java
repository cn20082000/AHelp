package com.cn.ahelp3.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.List;

public class PostDetail {

    @SerializedName("id")
    private Long id;

    @SerializedName("createdAt")
    private Calendar createdAt;

    @SerializedName("updatedAt")
    private Calendar updatedAt;

    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    @SerializedName("info")
    private String info;

    @SerializedName("type")
    private String type;

    @SerializedName("tags")
    private List<Tag> tags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public Calendar getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Calendar updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
