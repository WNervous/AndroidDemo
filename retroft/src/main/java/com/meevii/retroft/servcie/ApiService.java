package com.meevii.retroft.servcie;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("dda/dd")
    Call<ResponseBody> getData();
}
