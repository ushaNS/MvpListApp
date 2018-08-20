package com.usha.mvplistapp.presenter.data;

import com.usha.mvplistapp.model.NewsResponse;

import java.util.List;
import io.reactivex.Single;


interface DataSource {

    Single<List<NewsResponse>> requestNewsDetailsData(String url, String date);

}

