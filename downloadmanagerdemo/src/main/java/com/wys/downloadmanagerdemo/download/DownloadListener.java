package com.wys.downloadmanagerdemo.download;

public interface DownloadListener {

    void start();

    void progress(int current, int total, DownloadTask downloadTask);

    void failed();

    void success(String path);
}
