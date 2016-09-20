package com.mypro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by lebang on 16/9/11.
 */
public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";

    private WebView mWebView;
//    private RequestQueue mQueue;
    private String mDeatilNewsUrl = "http://daily.zhihu.com/story/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String newsId = intent.getStringExtra("news_id");
        mDeatilNewsUrl += newsId;

//        mQueue = Volley.newRequestQueue(DetailActivity.this);
        initWebView();
        mWebView.loadUrl(mDeatilNewsUrl);


    }

    /*private void getDataByUrl(String url) {
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        showNews(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailActivity.this, "checkout intent", Toast.LENGTH_SHORT).show();
                    }
                });
        mQueue.add(jsonRequest);
    }*/

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        mWebView = (WebView) findViewById(R.id.id_webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);

    }
    /*private void showNews(JSONObject data){

        String imgUrl = data.optString("image");

        String body = data.optString("body");
        String imgHtml = "<img height=\"300px\" src="+imgUrl+"><br/>";
//        if (mWebView != null && !TextUtils.isEmpty(body)) {
//            mWebView.loadData(imgHtml+body, "text/html; charset=utf-8", "utf-8");
//        }

//        String htmlData = "<link rel=\"stylesheet\" type=\"text/css\" href=\"detail_story.css\" />" + imgHtml+body;
//        mWebView.loadDataWithBaseURL("file:///android_asset/", imgHtml+body, "text/html", "UTF-8", null);



    }*/
}
