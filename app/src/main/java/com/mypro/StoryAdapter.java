package com.mypro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by lebang on 16/9/11.
 */
public class StoryAdapter extends BaseAdapter {

    private Context mContext;
    private List<TopStory> topStoryOfList;

    public  StoryAdapter(Context context, List<TopStory> objects){
        this.mContext = context;
        this.topStoryOfList = objects;
    }
    @Override
    public  int getCount(){
        return topStoryOfList.size();
    }
    @Override
    public Object getItem(int position) {
        return topStoryOfList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView (int position, View convertView, ViewGroup parent){
        TopStory topStory = topStoryOfList.get(position);
        final View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.story_item,null);
        ImageView img = (ImageView) view.findViewById(R.id.story_img);
        TextView title = (TextView) view.findViewById(R.id.story_title);
        Glide.with(mContext).load(topStory.getImage()).into(img);
        title.setText(topStory.getTitle());
        view.setTag(topStory.getId());
        return view;
    }

}

