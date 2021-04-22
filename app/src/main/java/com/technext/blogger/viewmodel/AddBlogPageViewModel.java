package com.technext.blogger.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.technext.blogger.model.Blog;
import com.technext.blogger.repository.AddBlogRepo;
import com.technext.blogger.repository.OnlineBlogRepo;

import java.util.List;

public class AddBlogPageViewModel extends AndroidViewModel {
    private AddBlogRepo repo;
    private LiveData<List<Blog>> blogData;
    private MutableLiveData<Boolean> progressbarObservable;

    public AddBlogPageViewModel(@NonNull Application application) {
        super(application);
        repo = new AddBlogRepo(application);
    }

    public void init() {
        blogData = repo.getblogData();
        progressbarObservable = repo.getProgress();
    }

    public void add_into_blog(Blog v) {
        repo.add_blog(v);
    }

    public void update_into_blog(Blog v) {
        repo.update_blog(v);
    }

    public LiveData<List<Blog>> getVolumesResponseLiveData() {
        return blogData;
    }

    public MutableLiveData<Boolean> getProgressbar() {
        return progressbarObservable;
    }
}