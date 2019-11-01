package com.grove.newsfeed.view.activity.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grove.newsfeed.R;
import com.grove.newsfeed.Utils.Constants;
import com.grove.newsfeed.Utils.ImageLoader;
import com.grove.newsfeed.databinding.AdapterNewsBinding;
import com.grove.newsfeed.service.model.GetNews;
import com.grove.newsfeed.view.activity.NewsDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {


    private List<GetNews.Article> newsFeeds=new ArrayList<>();
    private Activity activity;
    private ImageLoader imageLoader;

    public HomeAdapter(Activity activity){
        this.activity=activity;
        imageLoader=new ImageLoader(activity);
    }

    public void notifyList(List<GetNews.Article> newsFeeds) {
        this.newsFeeds=newsFeeds;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        AdapterNewsBinding binding;

        public ViewHolder(AdapterNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.ivNews.getRootView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(activity, NewsDetailsActivity.class);
                    intent.putExtra(Constants.url,newsFeeds.get(getLayoutPosition()).getUrl());
                    activity.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        AdapterNewsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.adapter_news, viewGroup, false);

        return new HomeAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder viewHolder, int pos) {
        viewHolder.binding.tvNewsTitle.setText(newsFeeds.get(pos).getTitle());
        imageLoader.DisplayImage(newsFeeds.get(pos).getUrlToImage(),viewHolder.binding.ivNews);

    }

    @Override
    public int getItemCount() {
        return newsFeeds.size();
    }
}
