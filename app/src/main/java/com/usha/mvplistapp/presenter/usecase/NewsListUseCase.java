package com.usha.mvplistapp.presenter.usecase;

import com.usha.mvplistapp.model.NewsResponse;
import com.usha.mvplistapp.presenter.base.BaseCallback;
import com.usha.mvplistapp.presenter.base.UseCase;
import com.usha.mvplistapp.presenter.data.DataRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class NewsListUseCase implements UseCase.NewsUseCase {

    private Disposable baseDisposable;
    private final DataRepository mDataRepository;
    private final CompositeDisposable mCompositeDisposable;

    @Inject
    public NewsListUseCase(DataRepository mDataRepository, CompositeDisposable mCompositeDisposable) {
        this.mDataRepository = mDataRepository;
        this.mCompositeDisposable = mCompositeDisposable;
    }

    public void unSubscribe() {
        if (!mCompositeDisposable.isDisposed()) {
            if (baseDisposable != null) {
                mCompositeDisposable.remove(baseDisposable);
            }
        }
    }

    @Override
    public void getNewsDetailsData(BaseCallback callback, String url, String date) {

        DisposableSingleObserver<List<NewsResponse>> newsDetailsObserver =
                new DisposableSingleObserver<List<NewsResponse>>() {
                    @Override
                    public void onSuccess(List<NewsResponse> newsDataModels) {
                        if (callback != null) {
                            callback.onSuccess(newsDataModels);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (callback != null) {
                            callback.onFail();
                        }
                        e.printStackTrace();
                    }
                };

        if (!mCompositeDisposable.isDisposed()) {
            Single<List<NewsResponse>> newsDetailsModelSingle = mDataRepository.requestNewsDetailsData(url, date);
            baseDisposable = newsDetailsModelSingle.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io()).subscribeWith(newsDetailsObserver);
            mCompositeDisposable.add(baseDisposable);
        }
    }


}
