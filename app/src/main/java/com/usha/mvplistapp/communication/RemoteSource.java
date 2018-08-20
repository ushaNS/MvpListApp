package com.usha.mvplistapp.communication;

import io.reactivex.Single;

interface RemoteSource {

    Single getNewsDetailsData(String url, String date);
}
