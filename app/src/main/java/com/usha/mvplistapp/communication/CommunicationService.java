package com.usha.mvplistapp.communication;


import com.usha.mvplistapp.model.NewsResponse;


import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface CommunicationService {

    @GET
    Call<List<NewsResponse>> getNewsOnDateSelected(@Url String url, @Query("newsdate") String newsDate);
}