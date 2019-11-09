package com.wys.downloadmanagerdemo.download;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DownloadUtils {
        private String                    userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36";
        private String                    cookie = null;
        private String                    url = "http://localhost:5000/%E5%A4%A9%E7%A9%BA%E4%B9%8B%E7%9C%BC.BD.720p.%E4%B8%AD%E8%8B%B1%E5%8F%8C%E5%AD%97%E5%B9%95.rmvb";
        // 文件总长度
        private long                      contentLength;
        // 当前下载长度
        private long                      currentLength;
        private long                      preLength;
        private String                    localPath = Environment.getExternalStorageDirectory().getPath() + "/AAAAImg";;
        private Map<String, List<String>> headers;

        public long getContentLength() {
            return contentLength;
        }

        public void setContentLength(long contentLength) {
            this.contentLength = contentLength;
        }

        public long getCurrentLength() {
            return currentLength;
        }

        public void setCurrentLength(long currentLength) {
            this.currentLength = currentLength;
        }

        public long getPreLength() {
            return preLength;
        }

        public void setPreLength(long preLength) {
            this.preLength = preLength;
        }

        public void download(String url) {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestProperty("User-Agent", userAgent);
                if(cookie != null) connection.setRequestProperty("Cookie", cookie);
                if(connection.getResponseCode() == 302) {
                    url = connection.getHeaderField("Location");
                    connection = (HttpURLConnection) new URL(url).openConnection();
                    connection.setRequestProperty("User-Agent", userAgent);
                    if(cookie != null) connection.setRequestProperty("Cookie", cookie);
                }
                setContentLength(connection.getContentLength());
                headers = connection.getHeaderFields();
                // 创建本地文件
                File file = new File(localPath);
                if (!file.exists()) file.mkdirs();
                file = new File(localPath + File.separator + getFileName(url));
                FileOutputStream fos = new FileOutputStream(file);
                // 在写文件之前调用统计方法
                // 拿到文件流
                InputStream is = connection.getInputStream();
                int len;
                byte[] b = new byte[1024];
                while ((len = is.read(b)) != -1) {
                    setCurrentLength(getCurrentLength() + len);
                    fos.write(b);
                    fos.flush();
                    countDownload();
                }
                fos.close();
                is.close();
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void countDownload() {
            while (getCurrentLength() < getContentLength()) {
                try {
                    Thread.sleep(1000);
                    BigDecimal bigDecimal = new BigDecimal((double) (getCurrentLength() * 100 / getContentLength()));
                    bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
                    System.out.println("下载完成：" + bigDecimal.doubleValue() +
                            "% 当前下载速度：" + formatLength(getCurrentLength() - getPreLength()) +
                            "/s 当前下载: " + formatLength(getCurrentLength()) +
                            " 文件大小: " + formatLength(getContentLength()));
                    setPreLength(getCurrentLength());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private String formatLength(long length) {
            if (length < 1024) {
                return length + "b";
            } else if (length > 1024 && length < 1024 * 1024) {
                BigDecimal bigDecimal = new BigDecimal((double) length / 1024);
                bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
                return bigDecimal.floatValue() + "kb";
            } else if (length > 1024 * 1024 && length < 1024 * 1024 * 1024) {
                BigDecimal bigDecimal = new BigDecimal((double) length / 1024 / 1024);
                bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
                return bigDecimal.floatValue() + "mb";
            } else {
                BigDecimal bigDecimal = new BigDecimal((double) length / 1024 / 1024 / 1024);
                bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
                return bigDecimal.floatValue() + "gb";
            }
        }

        private String getFileName(String url) throws UnsupportedEncodingException {
            String fileName = url.substring(url.lastIndexOf("/") + 1);
            if (fileName.contains(".")) {
                String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                if (suffix.length() > 4 || suffix.contains("?")) {
                    fileName = headers.get("Content-Disposition").get(0);
                    if (fileName == null || !fileName.contains("filename")) {
                        fileName = UUID.randomUUID().toString();
                    } else {
                        fileName = fileName.substring(fileName.lastIndexOf("filename") + 9);
                    }
                }
            }
            fileName = URLDecoder.decode(fileName, "UTF-8");
            return fileName;
        }
}
