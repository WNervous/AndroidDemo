package com.meevii.retroft.servcie;

import com.meevii.retroft.Repo;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("users/WNervous/repos")
    Call<List<Repo>> getRepos();

    @GET("users/WNervous/repos")
    Single<List<Repo>> getRepossss();

    @GET("users/WNervous/repos")
    Observable<List<Repo>> getReposssssss();
}
