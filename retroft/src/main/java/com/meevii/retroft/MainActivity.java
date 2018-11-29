package com.meevii.retroft;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.LruCache;

import com.meevii.retroft.servcie.ApiService;

import java.io.File;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.internal.io.FileSystem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        request();
        //lruCache
        LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>((int) Runtime.getRuntime().maxMemory() / 1024 / 8) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
        //Disk
        DiskLruCache diskLruCache = DiskLruCache.create(FileSystem.SYSTEM, new File(""), 1, 1, 100000);
        DiskLruCache.Editor editor = null;
        try {
            editor = diskLruCache.edit("ddd");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void request() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("").client(new OkHttpClient()).build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getData().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
