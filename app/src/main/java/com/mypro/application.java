package com.mypro;

import android.app.Application;
import android.os.Build;
import android.webkit.WebView;

/**
 * Created by lebang on 16/9/11.
 */
public class application extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        /**
         * 我是test分支,这个功能我开发好了。
         */
    }
}
