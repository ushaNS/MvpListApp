package com.usha.mvplistapp.presenter.base;

import java.util.List;

public interface BaseCallback <M>{

    void onFail();
    void onSuccess(List<M> model);

}
