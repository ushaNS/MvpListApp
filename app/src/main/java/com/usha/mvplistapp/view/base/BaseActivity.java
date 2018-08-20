package com.usha.mvplistapp.view.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;


import com.usha.mvplistapp.presenter.base.Presenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity {
    protected abstract void initializeDagger();
    protected abstract void savedInstance(Bundle savedInstanceState);
    protected abstract void initializePresenter();
    protected Presenter presenter;
    private Unbinder unbinder;
    protected ProgressDialog progressDialog;

    public abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        savedInstance(savedInstanceState);
        initializeDagger();
        initializePresenter();

        if (presenter != null) {
            presenter.initialize(getIntent().getExtras());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (presenter != null) {
            presenter.start();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.finalizeView();
        }
    }


    @Override
    public void setTitle(CharSequence resId) {
        if (getSupportActionBar() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                getSupportActionBar().setTitle(Html.fromHtml(resId.toString(), Html.FROM_HTML_MODE_LEGACY));

            } else {
                getSupportActionBar().setTitle(Html.fromHtml(resId.toString()));
            }
            getSupportActionBar().setDisplayShowTitleEnabled(true);

        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }





    protected void showProgressDialog(String message) {


        if (progressDialog == null || !progressDialog.isShowing()) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(message);
            progressDialog.setCanceledOnTouchOutside(false);
            if (!isFinishing()) {
                progressDialog.show();
            }
        }
    }

    protected void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            if (!isFinishing()) {
                progressDialog.dismiss();
            }
        }
    }
}
