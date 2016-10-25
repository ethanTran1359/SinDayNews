package com.example.rubit1359.sindaynews.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rubit1359 on 10/24/2016.
 */

public class SearchResult {

    // General class to store the list of Model
    @SerializedName("docs")
    private List<Article> articles;
    public List<Article> getArticles() {return articles;}
}
