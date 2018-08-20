package com.usha.mvplistapp.presenter.contract;

import com.google.gson.annotations.SerializedName;
import com.usha.mvplistapp.presenter.base.BaseView;

import java.util.HashMap;
import java.util.List;

public interface NewsContract {

    interface View<M> extends BaseView {
        void onSuccess(List<M> responseModel);
        void onFailed();
    }

    interface Presenter {
        void unSubscribe();
        void callNewsDetailsInfo(String url, String date);

    }

}
