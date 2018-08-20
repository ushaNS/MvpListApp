package com.usha.mvplistapp.utills;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;


@Module
public class MainModule {
    public MainModule() {

    }

    @Provides
    @Singleton
    public Gson provideGson() {
        Gson gson = new GsonBuilder().setLenient().create();

        return gson;
    }

    @Provides
    public CompositeDisposable provideCompositeSubscription() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        return compositeDisposable;
    }

    private Context context;


    public MainModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideApplication() {
        return context;
    }

}
