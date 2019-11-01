package com.grove.newsfeed.service.repository;


import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient
{
    private static Retrofit retrofit = null;
    private static ApiInterface REST_CLIENT;

    static {
        setupRestClient();
    }

    public static ApiInterface get() {
        return REST_CLIENT;
    }

    private static void setupRestClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlConstants.URL_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        REST_CLIENT=retrofit.create(ApiInterface.class);
    }


}
