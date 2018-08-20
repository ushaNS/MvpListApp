package com.usha.mvplistapp.communication;

import android.accounts.NetworkErrorException;
import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import com.usha.mvplistapp.constatnts.Const;
import com.usha.mvplistapp.model.NewsResponse;

import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Call;

import static com.usha.mvplistapp.communication.ServiceError.NETWORK_ERROR;
import static com.usha.mvplistapp.communication.ServiceError.SUCCESS_CODE;
import static java.util.Objects.isNull;


public class RemoteRepository implements RemoteSource {
    private ServiceGenerator serviceGenerator;
    private final String UNDELIVERABLE_EXCEPTION_TAG = "Unknown error occur";

    @Inject
    public RemoteRepository(ServiceGenerator serviceGenerator) {
        this.serviceGenerator = serviceGenerator;
    }


    @TargetApi(Build.VERSION_CODES.N)
    @NonNull
    private ServiceResponse processCall(Call call, boolean isVoid) {
        try {
            retrofit2.Response response = call.execute();
            if (isNull(response)) {
                return new ServiceResponse(new ServiceError(NETWORK_ERROR, Const.ERROR_UNDEFINED));
            }
            int responseCode = response.code();
            if (response.isSuccessful()) {
                return new ServiceResponse(responseCode, isVoid ? null : response.body());
            } else {
                ServiceError ServiceError;
                ServiceError = new ServiceError(response.message(), responseCode);
                return new ServiceResponse(ServiceError);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ServiceResponse(new ServiceError(NETWORK_ERROR, Const.ERROR_UNDEFINED));
        }
    }

    @Override
    public Single getNewsDetailsData(String url, String date) {
        RxJavaPlugins.setErrorHandler(throwable -> {
            Log.i(UNDELIVERABLE_EXCEPTION_TAG, throwable.getMessage());
            return;
        });
        Single<List<NewsResponse>> newsDetailsModelSingle = Single.create(singleOnSubscribe -> {

                        try {
                            CommunicationService newsService = serviceGenerator.createService(CommunicationService.class, Const.BASE_URL);

                            ServiceResponse serviceResponse = processCall(newsService.getNewsOnDateSelected(url,date), false);

                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                List<NewsResponse> newsdetailsModel = (List<NewsResponse>) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(newsdetailsModel);
                            } else {
                                Throwable throwable = new NetworkErrorException();
                                singleOnSubscribe.onError(throwable);
                                throwable.printStackTrace();
                            }
                        } catch (Exception e) {
                            singleOnSubscribe.onError(e);
                            e.printStackTrace();
                        }

                }
        );
        return newsDetailsModelSingle;
    }
}
