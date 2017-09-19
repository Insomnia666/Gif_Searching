package com.dimon.gif_searching.data.giphy;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dimon on 18.09.2017.
 */

public class Images {

    @SerializedName("fixed_height")
    private FixedHeight fixedHeight;
    @SerializedName("fixed_height_small")
    private FixedHeightSmall fixedHeightSmall;

    public FixedHeight getFixedHeight() {
        return fixedHeight;
    }

    public FixedHeightSmall getFixedHeightSmall() {
        return fixedHeightSmall;
    }
}
