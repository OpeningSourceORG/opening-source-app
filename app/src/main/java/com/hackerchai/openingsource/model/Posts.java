package com.hackerchai.openingsource.model;

import java.io.Serializable;

public class Posts implements Serializable
{
    private String postUrl;
    private String title;
    private String desc;
    private String photoUrl;

    /**
     * Constructs a new instance of {@code Object}.
     */
    public Posts(String v_post_url, String v_title, String v_desc, String v_photo_url) {
        this.postUrl = v_post_url;
        this.title = v_title;
        this.desc = v_desc;
        this.photoUrl = v_photo_url ;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public String getDesc() {
        return desc;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getPostUrl()
    {
        return postUrl;
    }

}

