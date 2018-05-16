package com.home.news.rssfeed.activities.main.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.home.news.rssfeed.R;
import com.home.news.rssfeed.network.data.Article;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class MainFeedRecyclerAdapter extends RecyclerView.Adapter<MainFeedRecyclerAdapter.ViewHolder> {

    private ArrayList<Article> mData;
    private final PublishSubject<Article> onClickSubject = PublishSubject.create();

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mHeader;
        private final TextView mDescription;
        private final SimpleDraweeView mImage;

        private ViewHolder(View view) {
            super(view);
            mHeader = view.findViewById(R.id.header_text);
            mDescription = view.findViewById(R.id.description_text);
            mImage = view.findViewById(R.id.news_image);

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
        final Article item = mData.get(position);
        holder.mHeader.setText(item.title);
        holder.mDescription.setText(item.description);

        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(item.urlToImage))
                .setResizeOptions(new ResizeOptions(100, 100))
                .build();
        holder.mImage.setController(
                Fresco.newDraweeControllerBuilder()
                        .setOldController(holder.mImage.getController())
                        .setImageRequest(request)
                        .build());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSubject.onNext(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void updateData(ArrayList<Article> data) {
        UpdateCallback updateCallback = new UpdateCallback(mData, data);
        DiffUtil.DiffResult productDiffResult = DiffUtil.calculateDiff(updateCallback);
        mData = data;
        productDiffResult.dispatchUpdatesTo(this);
    }

    public Observable<Article> getPositionClicks() {
        return onClickSubject;
    }
}