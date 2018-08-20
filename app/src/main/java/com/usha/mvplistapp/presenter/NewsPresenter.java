package com.usha.mvplistapp.presenter;

import com.usha.mvplistapp.presenter.base.BaseCallback;
import com.usha.mvplistapp.presenter.base.Presenter;
import com.usha.mvplistapp.presenter.base.UseCase;
import com.usha.mvplistapp.presenter.contract.NewsContract;
import com.usha.mvplistapp.presenter.usecase.NewsListUseCase;

import java.util.List;

import javax.inject.Inject;

public class NewsPresenter extends Presenter<NewsContract.View> implements NewsContract.Presenter {

    private final NewsListUseCase newwsUseCase;

    @Inject
    public NewsPresenter(NewsListUseCase gkUseCase) {
        this.newwsUseCase = gkUseCase;
    }

    @Override
    public void unSubscribe() {
        if (newwsUseCase != null) {
            newwsUseCase.unSubscribe();
        }
    }
    @Override
    public void callNewsDetailsInfo(String url, String date) {
        if (newwsUseCase != null) {
            newwsUseCase.getNewsDetailsData(callback, url, date);
        }
    }


    @SuppressWarnings("unchecked")
    private final BaseCallback callback = new BaseCallback() {


        @Override
        public void onSuccess(List model) {
            if (getView() != null) {
                getView().onSuccess(model);
            }
        }

        @Override
        public void onFail() {
            if (getView() != null) {
                getView().onFailed();
            }
        }

    };

}
