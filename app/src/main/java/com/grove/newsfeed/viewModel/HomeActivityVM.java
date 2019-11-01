package com.grove.newsfeed.viewModel;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.grove.newsfeed.R;
import com.grove.newsfeed.Utils.Constants;
import com.grove.newsfeed.service.model.GetNews;
import com.grove.newsfeed.service.repository.ApiClient;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivityVM extends AndroidViewModel {

    MutableLiveData<List<GetNews.Article>> newsList=new MutableLiveData<>();

    public HomeActivityVM(@NonNull Application application) {
        super(application);
        getNews(application.getBaseContext());
    }

    private void getNews(final Context context){
        Call<GetNews> call= ApiClient.get().getHeadline(Constants.country,Constants.apiKey);
        call.enqueue(new Callback<GetNews>() {
            @Override
            public void onResponse(Call<GetNews> call, Response<GetNews> response) {
                if(response.body()!=null && response.body().getStatus().toLowerCase().equals(Constants.status)){
                    newsList.setValue(response.body().getArticles());
                }
            }

            @Override
            public void onFailure(Call<GetNews> call, Throwable t) {
                Toast.makeText(context,context.getString(R.string.check_internet_connection),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public MutableLiveData<List<GetNews.Article>> getNewsList(){
          return   newsList;
    }
}
