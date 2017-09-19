package com.dimon.gif_searching.data.api;

import com.dimon.gif_searching.data.giphy.Data;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by dimon on 18.09.2017.
 */

public interface GiphyApi {
    @GET("trending?api_key=792a9563d39242d5a1acbb60e59548d8&limit=25")
    Call<Data> getTrending();
}
