package com.usha.mvplistapp.utills;

import android.app.Application;
import android.content.Context;


public class App extends Application {

    private static Context context;
    private MainComponent mainComponent;
    private MainComponent sessionPrefrenceComponent;

    public MainComponent getMainComponent() {
        return mainComponent;
    }

    public MainComponent getSessionPreference() {
        if (sessionPrefrenceComponent == null) {
            sessionPrefrenceComponent = DaggerMainComponent.builder().mainModule(new MainModule(getApplicationContext())).build();
        }
        return sessionPrefrenceComponent;
    }


    public static Context getContext() {
        return context;
    }



}