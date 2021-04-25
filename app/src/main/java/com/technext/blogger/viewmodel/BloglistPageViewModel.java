package com.technext.blogger.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.technext.blogger.model.Blog;
import com.technext.blogger.repository.OnlineBlogRepo;

import java.util.List;

import javax.inject.Inject;

public class BloglistPageViewModel extends AndroidViewModel {
    private OnlineBlogRepo repo;
    private LiveData<List<Blog>> blogData;
    private MutableLiveData<Boolean> progressbarObservable;

    @Inject
    public BloglistPageViewModel(@NonNull Application application) {
        super(application);
        repo = new OnlineBlogRepo(application);
    }

    public void init() {
        blogData = repo.getblogData();
        progressbarObservable = repo.getProgress();
        searchbloglist();
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