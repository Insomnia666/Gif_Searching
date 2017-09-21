package com.dimon.gif_searching.domain;

import com.dimon.gif_searching.data.api.giphy.GiphyApiFactory;
import com.dimon.gif_searching.data.content.Gif;
import com.dimon.gif_searching.data.giphy.Data;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by dimon on 21.09.2017.
 */

public class GifListUseCase extends UseCase<Void, List<Gif>> {
    @Override
    protected Observable<List<Gif>> biuldUseCase(Void aVoid) {
        return GiphyApiFactory.getInstance().getTrending().map(new Function<Data, List<Gif>>() {
            @Override
            public List<Gif> apply(Data data) throws Exception {
                List<Gif> gifsList = new ArrayList<>();
                Integer countOfGifs = Integer.parseInt(data.getPagination().getCount().toString());
                for (int i = 0; i < countOfGifs; i++) {
                    String url = data.getData().get(i).getImages().getFixedHeightSmall().getUrl();
                    String originalUrl = data.getData().get(i).getImages().getFixedHeight().getUrl();
                    gifsList.add(new Gif(url, originalUrl));
                }
                return gifsList;
            }
        });
    }
}
