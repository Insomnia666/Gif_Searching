package com.dimon.gif_searching.data.api.giphy;

import com.dimon.gif_searching.data.giphy.Data;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dimon on 18.09.2017.
 */

public interface GiphyApi {
    @GET("trending?api_key=792a9563d39242d5a1acbb60e59548d8&limit=25&rating=G")
    Observable<Data> getTrending();
    @GET("search?api_key=792a9563d39242d5a1acbb60e59548d8")
    Observable<Data> getFoundResult(@Query("q") String query, @Query("lang") String lang);
}
