package com.example.testproject;

import org.json.JSONObject;

import java.util.List;

public class ChannelItem {

    private String mImageUrl;
    private String mName;
    private String mDescription;
    private String mLink;
    private List<String> mCategory;

        public ChannelItem(String imageUrl, String name, String description, String link, List<String> category) {
        mImageUrl = imageUrl;
        mName = name;
        mDescription = description;
        mLink = link;
        mCategory = category;

    }

    public String getImageUrl(){
        return mImageUrl;
    }
    public String getChannelName(){
        return mName;
    }
    public String getDescription(){
        return mDescription;
    }
    public String getLink(){
        return mLink;
    }
    public String getCategory(){
        return mCategory.toString();
    }





}

