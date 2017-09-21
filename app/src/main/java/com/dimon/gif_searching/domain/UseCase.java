package com.dimon.gif_searching.domain;

import com.dimon.gif_searching.data.content.Gif;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dimon on 21.09.2017.
 */

public abstract class UseCase<InParam, OutParam> {
    private Disposable disposable;

    protected abstract Observable<OutParam> biuldUseCase(InParam param);

    public Gif execute(InParam param, DisposableObserver<OutParam> disposableObserver) {
        disposable = biuldUseCase(param)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribeWith(disposableObserver);

        return null;
    }

    public void dispose() {
        if(!disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}

