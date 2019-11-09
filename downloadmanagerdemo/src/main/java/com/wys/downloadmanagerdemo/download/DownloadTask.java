package com.wys.downloadmanagerdemo.download;

public class DownloadTask {

    private String id;
    private String loadUrl;
    private String type;

    private DownloadListener listener;

    public DownloadListener getListener() {
        return listener;
    }

    public void setListener(DownloadListener listener) {
        this.listener = listener;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoadUrl() {
        return loadUrl;
    }

    public void setLoadUrl(String loadUrl) {
        this.loadUrl = loadUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DownloadTask{" + "id='" + id + '\'' + ", loadUrl='" + loadUrl + '\'' + ", type='" + type + '\'' + '}';
    }
}
