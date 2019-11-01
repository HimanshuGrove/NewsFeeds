package com.grove.newsfeed.service.repository;


import com.grove.newsfeed.service.model.GetNews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET(UrlConstants.URL_GET_HEADLINE)
    Call<GetNews> getHeadline(@Query(UrlConstants.country) String country,@Query(UrlConstants.apiKey) String ApiKey);

}
