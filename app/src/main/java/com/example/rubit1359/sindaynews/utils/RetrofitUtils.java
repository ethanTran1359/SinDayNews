package com.example.rubit1359.sindaynews.utils;

import com.example.rubit1359.sindaynews.BuildConfig;
import com.example.rubit1359.sindaynews.model.ApiResponse;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.HTTP;

/**
 * Created by rubit1359 on 10/23/2016.
 */

public class RetrofitUtils {
    public static final MediaType JSON = MediaType.parse("aplication/json; charset=utf-8");
    public static final Gson GSON = new Gson();

    public static Retrofit get() {
        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(client())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static OkHttpClient client() {
        return new OkHttpClient.Builder()
                .addInterceptor(apiKeyInterceptor())
                .addInterceptor(responseInterceptor())
                .build();
    }

    private static Interceptor responseInterceptor() {
        return chain -> {
            Request request = chain.request();
            Response response = chain.proceed(request);
            ResponseBody body = response.body();
            ApiResponse apiResponse = GSON.fromJson(body.string(), ApiResponse.class);
            body.close();
            return response.newBuilder()
                    .body(ResponseBody.create(JSON, ApiResponse.getResponse().toString()))
                    .build();
        };
    }

    private static Interceptor apiKeyInterceptor() {
        return chain -> {
            Request request = chain.request();
            HttpUrl url = request.url()
                    .newBuilder()
                    .addQueryParameter("api_key", BuildConfig.API_KEY)
                    .build();
            request = request.newBuilder()
                    .url(url)
                    .build();
            return chain.proceed(request);

//            Response response = chain.proceed(request);
//            ResponseBody body = response.body();
//            ApiResponse apiResponse = GSON.fromJson(body.string(), ApiResponse.class);
//            body.close();
//            response = response.newBuilder()
//                    .body(ResponseBody.create(JSON, ApiResponse.getResponse().toString()))
//                    .build();
//            return response;
        };
    }

    private static Interceptor apiKeyInterceptor(final String apiKey) {
        return (Interceptor.Chain chain) -> {
            Request request = chain.request();
            HttpUrl url = request.url()
                    .newBuilder()
                    .addQueryParameter("api_key", BuildConfig.API_KEY)
                    .build();
            request =  request.newBuilder()
                    .url(url)
                    .build();
            return chain.proceed(request);
        };
    }
}
