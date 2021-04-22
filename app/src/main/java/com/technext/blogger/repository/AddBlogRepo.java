package com.technext.blogger.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.technext.blogger.database.BLOG_DATABASE;
import com.technext.blogger.database.BlogDao;
import com.technext.blogger.model.Blog;
import com.technext.blogger.model.GetBlogListModel;
import com.technext.blogger.network.ApiService;
import com.technext.blogger.network.Controller;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBlogRepo {
    private LiveData<List<Blog>> blogkMutableLiveData;
    public MutableLiveData<Boolean> progressbarObservable = new MutableLiveData<>();
    Gson gson = new Gson();

    BlogDao blogDao;

    public AddBlogRepo(Application application) {
        BLOG_DATABASE db = BLOG_DATABASE.getDatabase(application);
        blogDao = db.blogDao();
        blogkMutableLiveData = blogDao.getAllblog();
    }

    public void add_blog(Blog b) {
        //new insertAsyncTask(blogDao).execute(b);
        try {
            progressbarObservable.postValue(true);
            String d = setblogData(b);
            Log.d("dara2", d.toString());
            if (d != null) {
                progressbarObservable.postValue(false);
            }
            //Log.d("dara", d.toString());
        } catch (Exception e) {
            Log.d("Error Line Number", Log.getStackTraceString(e));
        }
    }

    public void update_blog(Blog b) {
        //new insertAsyncTask(blogDao).execute(b);
        try {
            progressbarObservable.postValue(true);
            String d = updateblogData(b);
            Log.d("dara1", d.toString());
            if (d != null) {
                progressbarObservable.postValue(false);
            }
            //Log.d("dara", d.toString());
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


    private static class insertAsyncTask extends AsyncTask<Blog, Void, Void> {

        private BlogDao mAsyncTaskDao;

        insertAsyncTask(BlogDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Blog... params) {
            mAsyncTaskDao.insertblog(params[0]);
            return null;
        }
    }

    public String setblogData(Blog b) throws ExecutionException, InterruptedException {

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                String d = "done";
                try {
                    blogDao.insertblog(b);
                } catch (Exception e) {
                    Log.d("Error Line Number", Log.getStackTraceString(e));
                    d = "notdone";
                }
                return d;
            }
        };
        Future<String> future = Executors.newSingleThreadExecutor().submit(callable);
        return future.get();
    }

    public String updateblogData(Blog b) throws ExecutionException, InterruptedException {

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                String d = "done";
                try {
                    blogDao.updateblog(b);
                } catch (Exception e) {
                    Log.d("Error Line Number", Log.getStackTraceString(e));
                    d = "notdone";
                }
                return d;
            }
        };
        Future<String> future = Executors.newSingleThreadExecutor().submit(callable);
        return future.get();
    }
}