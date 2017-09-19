package com.dimon.gif_searching.data.giphy;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dimon on 18.09.2017.
 */

public class Datum {

    @SerializedName("rating")
    private String rating;
    @SerializedName("images")
    private Images images;

    public String getRating() {
        return rating;
    }

    public Images getImages() {
        return images;
    }
}
