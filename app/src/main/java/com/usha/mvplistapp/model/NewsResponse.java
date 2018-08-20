package com.usha.mvplistapp.model;

import com.google.gson.annotations.SerializedName;

public class NewsResponse {

    @SerializedName("headline")
    private String headline;
    @SerializedName("content")
    private String content;
    @SerializedName("didyouknow")
    private String didyouknow;
    @SerializedName("imgurl")
    private String imgurl;

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDidyouknow() {
        return didyouknow;
    }

    public void setDidyouknow(String didyouknow) {
        this.didyouknow = didyouknow;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

}
