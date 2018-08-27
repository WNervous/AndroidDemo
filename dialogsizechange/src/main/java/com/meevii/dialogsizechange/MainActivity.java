package com.meevii.dialogsizechange;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.meevii.dialogsizechange.dialog.GameCompleteFragment;

/**
 *问题：横屏使用dialog  时，如果 添加 android:configChanges="orientation|keyboardHidden|screenSize" ，
 * 当dialog出现，锁屏并重新打开时，dialog 不会重新创建。导致 dialog 的尺寸适配变化。
 *
 * 解决：1.去掉android:configChanges="orientation|keyboardHidden|screenSize"
 *      2.重写onConfigurationChanged 中，当再次转为横屏时，在设置为正确尺寸
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.showdialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameCompleteFragment gameCompleteFragment = GameCompleteFragment.newInstance();
                gameCompleteFragment.show(getSupportFragmentManager(), "GameCompleteFragment");
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
