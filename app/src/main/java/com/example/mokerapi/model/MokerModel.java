package com.example.mokerapi.model;

import com.google.gson.annotations.SerializedName;

public
class MokerModel {

    @SerializedName("id")
    private Integer id;

    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    @SerializedName("user")
    private Integer user;

    @SerializedName("group")
    private Integer group;

    public MokerModel(String title, String content, Integer user, Integer group) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.group = group;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getUser() {
        return user;
    }

    public Integer getGroup() {
        return group;
    }
}
