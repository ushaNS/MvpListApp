package com.usha.mvplistapp.utills;

import com.usha.mvplistapp.view.NewsListActivity;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {

    void inject(NewsListActivity newsListActivity);
}

