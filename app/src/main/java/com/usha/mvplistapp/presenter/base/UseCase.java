package com.usha.mvplistapp.presenter.base;


public class UseCase {

    public interface NewsUseCase {

        void getNewsDetailsData(BaseCallback callback, String url, String date);
    }

}
