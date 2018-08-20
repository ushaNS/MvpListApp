package com.usha.mvplistapp.view;

import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.usha.mvplistapp.R;
import com.usha.mvplistapp.presenter.NewsPresenter;
import com.usha.mvplistapp.presenter.contract.NewsContract;
import com.usha.mvplistapp.utills.App;
import com.usha.mvplistapp.view.adapter.NewsListAdapter;
import com.usha.mvplistapp.view.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.usha.mvplistapp.constatnts.Const.NEWS_DETAILS_URL;


public class NewsListActivity extends BaseActivity implements NewsContract.View{

    NewsListAdapter newsListAdapter;
    @BindView(R.id.newsList)
    RecyclerView newsRecyclerView;
    @Inject
    NewsPresenter presenter;

    @Override
    protected void initializeDagger() {
        App app = (App)getApplicationContext();
        app.getSessionPreference().inject(NewsListActivity.this);
        showProgressDialog(getString(R.string.str_please_wait));
    }

    @Override
    protected void savedInstance(Bundle savedInstanceState) {

    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
        if(presenter != null)

            presenter.callNewsDetailsInfo(NEWS_DETAILS_URL,"2018-08-15");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_news_list;
    }

    @Override
    public void onSuccess(List responseModel) {
        hideProgressDialog();
        if (responseModel != null && responseModel.size() > 0){

            newsListAdapter = new NewsListAdapter(responseModel,this);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            newsRecyclerView.setLayoutManager(mLayoutManager);
            newsRecyclerView.setItemAnimator(new DefaultItemAnimator());
            newsRecyclerView.setAdapter(newsListAdapter);
        }

    }

    @Override
    public void onFailed() {

    }
}
