package com.wys.webviewjs;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Set;

/**
 * 对于Android调用JS代码的方法有2种：
 * 1. 通过WebView的loadUrl（）
 * 2. 通过WebView的evaluateJavascript（）
 * <p>
 * 对于JS调用Android代码的方法有3种：
 * 1. 通过WebView的addJavascriptInterface（）进行对象映射
 * 2. 通过 WebViewClient 的shouldOverrideUrlLoading ()方法回调拦截 url
 * 3. 通过 WebChromeClient 的onJsAlert()、onJsConfirm()、onJsPrompt（）方法回调拦截JS对话框alert()、confirm()、prompt（） 消息
 */
public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.addJavascriptInterface(new AndroidToJs(), "ggggg"); //AndroidtoJS类对象映射到js的test对象
        webView.loadUrl("file:///android_asset/js.html");
        //////////////////////////////////////////////////////////////////////////////
        // todo Andorid  通过webview 调用js 代码
        //////////////////////////////////////////////////////////////////////////////

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 方法一
                // webView.loadUrl("javascript:callJS()");
                // 方法二
                webView.evaluateJavascript("javascript:callJS()", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        //                        Log.d("MainActivity", value);
                        //此处为 js 返回的结果
                    }
                });
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Toast.makeText(MainActivity.this, "onJsAlert", Toast.LENGTH_SHORT).show();
                return true; // 是否客户端要处理此事件
            }
        });

        //////////////////////////////////////////////////////////////////////////////
        //   js  通过webview 调用Android 代码
        //////////////////////////////////////////////////////////////////////////////
        /**
         * 方法一 ：通过 WebView的addJavascriptInterface（）进行对象映射
         * 缺点：存在严重的漏洞问题，
         * 有点简单 ：
         */

        /**
         * 方法二 ：通过 WebViewClient 的方法shouldOverrideUrlLoading ()回调拦截 url
         */
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri uri = request.getUrl();
                Log.d("MainActivity", uri.toString());
                // 就解析往下解析参数
                if (uri.getScheme().equals("js")) {

                    // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
                    // 所以拦截url,下面JS开始调用Android需要的方法
                    if (uri.getAuthority().equals("webview")) {

                        //  步骤3：
                        // 执行JS所需要调用的逻辑
                        System.out.println("js调用了Android的方法");
                        // 可以在协议上带有参数并传递到Android上
                        HashMap<String, String> params = new HashMap<>();
                        Set<String> collection = uri.getQueryParameterNames();
                        Log.d("MainActivity", collection.toString());
                    }

                    return true;
                }
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

    }
}
