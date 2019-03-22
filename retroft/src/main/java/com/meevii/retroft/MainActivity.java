package com.meevii.retroft;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.meevii.retroft.servcie.ApiService;
import com.meevii.retroft.servcie.RxHelper;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        request();
    }

    private void request() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com/").addConverterFactory(GsonConverterFactory.create(gson)).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

        ApiService apiService = retrofit.create(ApiService.class);
//        apiService.getRepos().enqueue(new Callback<List<Repo>>() {
//            @Override
//            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
//                //                Log.d("MainActivity",gson.toJson(response.body()));
//            }
//
//            @Override
//            public void onFailure(Call<List<Repo>> call, Throwable t) {
//
//            }
//        });

//        Disposable subscribe = apiService.getRepossss().subscribeOn(RxHelper.applyIo()).observeOn(RxHelper.applyMainThread()).subscribe(new Consumer<List<Repo>>() {
//            @Override
//            public void accept(List<Repo> repos) {
//                Log.d("MainActivity", gson.toJson(repos));
//            }
//        });

        apiService.getReposssssss().subscribeOn(RxHelper.applyIo()).observeOn(RxHelper.applyMainThread()).subscribe(new Observer<List<Repo>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Repo> repos) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }


    private void okhttp() {
        Request.Builder builder = new Request.Builder().url("https://api.github.com/users/renwuxian/repo");
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(builder.build()).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) {

            }
        });

    }
}
