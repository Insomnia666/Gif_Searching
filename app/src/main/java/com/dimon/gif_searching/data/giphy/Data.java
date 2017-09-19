package com.dimon.gif_searching.data.giphy;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dimon on 18.09.2017.
 */

public class Data {

    @SerializedName("data")
    private List<Datum> data = null;
    @SerializedName("pagination")
    private Pagination pagination;

    public List<Datum> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }
}
