package com.mypro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private RequestQueue mQueue;
    private String URL = "http://news-at.zhihu.com/api/4/news/latest";//单一职责
    private List<TopStory> topStoryOfList = new ArrayList<TopStory>();
    private ListView mTopStoryListView;
    private StoryAdapter mStoryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getData();
    }

    private void initView() {
        mQueue = Volley.newRequestQueue(MainActivity.this);
        mTopStoryListView = (ListView) findViewById(R.id.story_list);

        mTopStoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                String newsId = (String) view.getTag();
                intent.putExtra("news_id", newsId);
                Log.d(TAG, "onItemClick: " + newsId);
                startActivity(intent);
            }
        });

    }

    private void getData() {
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            topStoryData(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "checkout intent", Toast.LENGTH_SHORT).show();
                    }
                });
        mQueue.add(jsonRequest);
    }

    private void topStoryData(JSONObject data) throws JSONException {
        JSONArray topData = data.optJSONArray("stories");
        if (topData == null || topData.length() < 0) {
            return;
        }
        for (int i = 0, len = topData.length(); i < len; i++) {
            if (topData.isNull(i)) return;
            JSONObject item = topData.optJSONObject(i);
            String id = item.optString("id");
            String title = item.optString("title");
            String image = (String) item.getJSONArray("images").get(0);

            if (!TextUtils.isEmpty(id) && !TextUtils.isEmpty(title) && !TextUtils.isEmpty(image)) {
                TopStory topStory = new TopStory(id, title, image);
                topStoryOfList.add(topStory);
            }
        }
        mStoryAdapter = new StoryAdapter(MainActivity.this, topStoryOfList);
        mTopStoryListView.setAdapter(mStoryAdapter);
    }
}
