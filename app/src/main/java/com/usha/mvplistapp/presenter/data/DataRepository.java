package com.usha.mvplistapp.presenter.data;

import com.usha.mvplistapp.communication.RemoteRepository;
import com.usha.mvplistapp.model.NewsResponse;

import java.util.List;
import javax.inject.Inject;
import io.reactivex.Single;


public class DataRepository implements DataSource {
    private RemoteRepository remoteRepository;

    @Inject
    public DataRepository(RemoteRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    public Single<List<NewsResponse>> requestNewsDetailsData(String url, String date) {
        return remoteRepository.getNewsDetailsData(url,date);
    }
}
