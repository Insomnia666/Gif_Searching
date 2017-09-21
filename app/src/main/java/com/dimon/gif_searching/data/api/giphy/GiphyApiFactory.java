package com.dimon.gif_searching.data.api.giphy;

import com.dimon.gif_searching.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dimon on 18.09.2017.
 */

public class GiphyApiFactory {

    private static GiphyApi service;

    public static GiphyApi getInstance() {
        if (service == null) {
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(BuildConfig.API_GIPHY)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = client.create(GiphyApi.class);
            return service;
        } else {
            return service;
        }
    }
}
