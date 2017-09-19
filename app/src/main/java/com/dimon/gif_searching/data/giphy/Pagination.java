package com.dimon.gif_searching.data.giphy;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dimon on 18.09.2017.
 */

public class Pagination {

    @SerializedName("count")
    private Integer count;

    public Integer getCount() {
        return count;
    }
}
