package com.cn.ahelp3.data.model;

import com.cn.ahelp3.R;
import com.cn.ahelp3.common.App;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

public class Post implements Serializable {

    @SerializedName("id")
    private Long id;

    @SerializedName("createdAt")
    private Calendar createdAt;

    @SerializedName("updatedAt")
    private Calendar updatedAt;

    @SerializedName("title")
    private String title;

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

    public static String getTypeStr(String type) {
        String[] arr = App.res.getStringArray(R.array.post_type);
        switch (type) {
            case "New": return arr[0];
            case "Extend": return arr[1];
            case "Rewrite": return arr[2];
            case "Copy": return arr[3];
            default: return arr[4];
        }
    }
}
