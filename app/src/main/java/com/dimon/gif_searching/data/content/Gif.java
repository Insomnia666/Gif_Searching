package com.dimon.gif_searching.data.content;

/**
 * Created by dimon on 19.09.2017.
 */

public class Gif {
    private String url;
    private String bigSizeGifUrl;

    public Gif (String url, String bigSizeGifUrl) {
        this.url = url;
        this.bigSizeGifUrl = bigSizeGifUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getBigSizeGifUrl() {
        return bigSizeGifUrl;
    }
}
