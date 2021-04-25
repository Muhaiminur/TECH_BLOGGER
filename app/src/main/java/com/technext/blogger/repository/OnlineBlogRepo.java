package com.technext.blogger.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.technext.blogger.dagger.DaggerFixedComponent;
import com.technext.blogger.dagger.FixedComponent;
import com.technext.blogger.database.BLOG_DATABASE;
import com.technext.blogger.database.BlogDao;
import com.technext.blogger.model.Blog;
import com.technext.blogger.model.GetBlogListModel;
import com.technext.blogger.network.ApiService;
import com.technext.blogger.network.Controller;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnlineBlogRepo {
    FixedComponent fixedModulel = DaggerFixedComponent.create();
    Controller controller = fixedModulel.getController();
    ApiService apiInterface = controller.getBaseClient().create(ApiService.class);
    private LiveData<List<Blog>> blogkMutableLiveData;
    public MutableLiveData<Boolean> progressbarObservable = new MutableLiveData<>();
    Gson gson = new Gson();

    BlogDao blogDao;

    public OnlineBlogRepo(Application application) {
        BLOG_DATABASE db = BLOG_DATABASE.getDatabase(application);
        blogDao = db.blogDao();
        blogkMutableLiveData = blogDao.getAllblog();
    }

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
                                    //blogkMutableLiveData.postValue(pList.getBlogs());

                                    new insertallAsyncTask(blogDao).execute(pList.getBlogs());
                                    //blogkMutableLiveData = blogDao.getAllblog();
                                    //blogkMutableLiveData.postValue(blogDao.getAllblog());
                                }
                            }

                            @Override
                            public void onFailure(Call<JsonElement> call, Throwable t) {
                                progressbarObservable.postValue(false);
                                Log.d("Error", t.toString());
                                //blogkMutableLiveData.postValue(null);
                            }
                        });
                    }
                }, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            Log.d("Error Line Number", Log.getStackTraceString(e));
        }
    }

    public LiveData<List<Blog>> getblogData() {
        return blogkMutableLiveData;
    }

    public MutableLiveData<Boolean> getProgress() {
        return progressbarObservable;
    }


    private static class insertallAsyncTask extends AsyncTask<List<Blog>, Void, Void> {

        private BlogDao mAsyncTaskDao;

        insertallAsyncTask(BlogDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<Blog>... params) {
            mAsyncTaskDao.insertallblog(params[0]);
            return null;
        }
    }
}