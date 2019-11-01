package com.grove.newsfeed.view.activity;

import android.os.Bundle;

import com.grove.newsfeed.R;
import com.grove.newsfeed.databinding.ActivityHomeBinding;
import com.grove.newsfeed.service.model.GetNews;
import com.grove.newsfeed.view.activity.adapter.HomeAdapter;
import com.grove.newsfeed.viewModel.HomeActivityVM;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;


public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding mBinding;
    HomeActivityVM homeActivityVM;
    HomeAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_home);
        homeActivityVM=new HomeActivityVM(getApplication());
        initView();
    }

    void initView(){
        homeAdapter=new HomeAdapter(this);
        mBinding.recyNews.setAdapter(homeAdapter);
        homeActivityVM.getNewsList().observe(this, new Observer<List<GetNews.Article>>() {
            @Override
            public void onChanged(List<GetNews.Article> articles) {
                if(articles!=null && articles.size()!=0){
                    homeAdapter.notifyList(articles);
                }
            }
        });
    }


}
