package com.example.rubit1359.sindaynews.model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Created by rubit1359 on 10/24/2016.
 */

public class ApiResponse {
    @SerializedName("response")
    private static JSONObject response;
    @SerializedName("status")
    private String status;

    public ApiResponse() {
    }

    public static JSONObject getResponse() {
        if (response == null) {
            return new JSONObject();
        }

        return response;
    }

    public String getStatus() {
        return status;
    }
}
