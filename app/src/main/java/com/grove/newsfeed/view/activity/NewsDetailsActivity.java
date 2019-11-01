package com.grove.newsfeed.view.activity;

import android.os.Bundle;

import android.webkit.WebViewClient;

import com.grove.newsfeed.R;
import com.grove.newsfeed.Utils.Constants;
import com.grove.newsfeed.databinding.ActivityNewsDetailBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class NewsDetailsActivity extends AppCompatActivity {

    ActivityNewsDetailBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this, R.layout.activity_news_detail);
        if(getIntent().getStringExtra(Constants.url)!=null){
            mBinding.wbView.setWebViewClient(new WebViewClient());
            mBinding.wbView.loadUrl(getIntent().getStringExtra(Constants.url));
        }

    }
}
