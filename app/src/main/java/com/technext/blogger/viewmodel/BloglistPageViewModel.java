package com.technext.blogger.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.technext.blogger.model.Blog;
import com.technext.blogger.repository.OnlineBlogRepo;

import java.util.List;

public class BloglistPageViewModel extends AndroidViewModel {
    private OnlineBlogRepo repo;
    private LiveData<List<Blog>> blogData;
    private MutableLiveData<Boolean> progressbarObservable;

    public BloglistPageViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        repo = new OnlineBlogRepo();
        searchbloglist();
        blogData = repo.getblogData();
        progressbarObservable = repo.getProgress();
    }

    public void searchbloglist() {
        repo.get_blog_list();
    }

    public LiveData<List<Blog>> getVolumesResponseLiveData() {
        return blogData;
    }

    public MutableLiveData<Boolean> getProgressbar() {
        return progressbarObservable;
    }
}