package com.usha.mvplistapp.communication;
import com.google.gson.Gson;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


@Singleton
public class ServiceGenerator {

    //Network constants
    private final int TIMEOUT_CONNECT = 60;   //In seconds
    private final int TIMEOUT_READ = 60;   //In seconds


    private OkHttpClient.Builder okHttpBuilder;
    private Retrofit retrofit;
    private Gson gson;

    @Inject
    public ServiceGenerator(Gson gson) {
        this.okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.addNetworkInterceptor(new UserAgentInterceptor());
        okHttpBuilder.addInterceptor(getLogger());
        okHttpBuilder.connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(TIMEOUT_READ, TimeUnit.SECONDS);
        this.gson = gson;

    }

    public <S> S createService(Class<S> serviceClass, String baseUrl) {

        OkHttpClient client = okHttpBuilder.build();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl).client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))

                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        return retrofit.create(serviceClass);
    }
    private HttpLoggingInterceptor getLogger() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

        return loggingInterceptor;
    }

}