package com.home.news.rssfeed.activities.main.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.home.news.rssfeed.R;
import com.home.news.rssfeed.network.data.Article;

import java.util.ArrayList;

public class MainFeedRecyclerAdapter extends RecyclerView.Adapter<MainFeedRecyclerAdapter.ViewHolder>  {

    private ArrayList<Article> mData;

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mHeader;
        private TextView mDescription;

        private ViewHolder(View view) {
            super(view);
            mHeader = view.findViewById(R.id.header_text);
            mDescription = view.findViewById(R.id.description_text);
        }
    }

    public MainFeedRecyclerAdapter(ArrayList<Article> data) {
        mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_feed_recycle_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article item = mData.get(position);
        holder.mHeader.setText(item.title);
        holder.mDescription.setText(item.description);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void updateData(ArrayList<Article> data){
        mData = data;
        notifyDataSetChanged();
    }
}