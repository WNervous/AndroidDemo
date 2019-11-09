package com.wys.downloadmanagerdemo.downloadtest;

import java.io.File;

public interface IOnDownloadListener {
    void updateNotification(int progress, int totalSize, File downFile);
}
