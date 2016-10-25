package com.example.rubit1359.sindaynews.api;

import com.example.rubit1359.sindaynews.model.SearchResult;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by rubit1359 on 10/24/2016.
 */

public interface ArticleApi {
    @GET("articlesearch.json")
    Call<SearchResult> search(@QueryMap(encoded = true) Map<String, String> options);
}
