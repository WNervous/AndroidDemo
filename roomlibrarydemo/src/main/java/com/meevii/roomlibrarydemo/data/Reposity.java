package com.meevii.roomlibrarydemo.data;

import android.content.Context;
import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Reposity {

    public void insert(final Context context, final Repo repo) {
        Observable.create(new ObservableOnSubscribe<Void>() {
            @Override
            public void subscribe(ObservableEmitter<Void> emitter) throws Exception {
                RepoDatabase
                        .getInstance(context)
                        .getRepoDao()
                        .insert(repo);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }

    public Observable<List<Repo>> getAll(final Context context) {
        return Observable.create(new ObservableOnSubscribe<List<Repo>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Repo>> emitter) {
                emitter.onNext(RepoDatabase
                        .getInstance(context).getRepoDao().getAllRepos());
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Repo> query(final Context context, final int repo) {
        return Observable.create(new ObservableOnSubscribe<Repo>() {
            @Override
            public void subscribe(ObservableEmitter<Repo> emitter) {
                Repo qurery = RepoDatabase.getInstance(context).getRepoDao().qurery(repo);
                if (qurery == null) {
                    Log.d("MainActivity", "this id:" + repo + "don't exist");
                    return;
                }
                emitter.onNext(qurery);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public void update(final Context context, final Repo repo) {
        Observable.create(new ObservableOnSubscribe<Void>() {
            @Override
            public void subscribe(ObservableEmitter<Void> emitter) {
                RepoDatabase.getInstance(context).getRepoDao().update(repo);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }
}
