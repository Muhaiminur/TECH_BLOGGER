package com.technext.blogger.repository;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.technext.blogger.model.Blog;
import com.technext.blogger.model.GetBlogListModel;
import com.technext.blogger.network.ApiService;
import com.technext.blogger.network.Controller;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnlineBlogRepo {
    ApiService apiInterface = Controller.getBaseClient().create(ApiService.class);
    private MutableLiveData<List<Blog>> bankMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> progressbarObservable = new MutableLiveData<>();
    Gson gson = new Gson();

    public void get_blog_list() {
        try {
            progressbarObservable.postValue(true);
            try {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        Call<JsonElement> call = apiInterface.get_bloglist();
                        call.enqueue(new Callback<JsonElement>() {
                            @Override
                            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                                progressbarObservable.postValue(false);
                                if (response.body() != null) {
                                    Log.d("from server", response.body() + "");
                                    GetBlogListModel pList = gson.fromJson(response.body(), GetBlogListModel.class);
                                    bankMutableLiveData.postValue(pList.getBlogs());
                                }
                            }

                            @Override
                            public void onFailure(Call<JsonElement> call, Throwable t) {
                                progressbarObservable.postValue(false);
                                Log.d("Error", t.toString());
                                //bankMutableLiveData.postValue(null);
                            }
                        });
                    }
                }, 5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            Log.d("Error Line Number", Log.getStackTraceString(e));
        }
    }

    public LiveData<List<Blog>> getblogData() {
        return bankMutableLiveData;
    }

    public MutableLiveData<Boolean> getProgress() {
        return progressbarObservable;
    }
}