package com.mypro;

import android.app.Application;
import android.os.Build;
import android.webkit.WebView;

/**
 * Created by lebang on 16/9/11.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }
}
