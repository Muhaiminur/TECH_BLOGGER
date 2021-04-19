package com.technext.blogger.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.technext.blogger.R;
import com.technext.blogger.adapter.BloglistAdapter;
import com.technext.blogger.databinding.BloglistPageFragmentBinding;
import com.technext.blogger.library.Utility;
import com.technext.blogger.model.Blog;
import com.technext.blogger.model.GetBlogListModel;
import com.technext.blogger.network.ApiService;
import com.technext.blogger.network.Controller;
import com.technext.blogger.viewmodel.BloglistPageViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BloglistPage extends Fragment {

    List<Blog> blogListModels;
    BloglistAdapter bloglistAdapter;
    ApiService apiInterface = Controller.getBaseClient().create(ApiService.class);
    Gson gson = new Gson();
    Context context;
    Utility utility;
    BloglistPageFragmentBinding binding;

    private BloglistPageViewModel mViewModel;

    public static BloglistPage newInstance() {
        return new BloglistPage();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            try {
                binding = BloglistPageFragmentBinding.inflate(inflater, container, false);
                context = getActivity();
                utility = new Utility(context);
                initial_list();
            } catch (Exception e) {
                Log.d("Error Line Number", Log.getStackTraceString(e));
            }
        }
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BloglistPageViewModel.class);
        mViewModel.init();
        observeLogin();
        //initial_list();
        mViewModel.getVolumesResponseLiveData().observe(getViewLifecycleOwner(), new Observer<List<Blog>>() {
            @Override
            public void onChanged(List<Blog> volumesResponse) {
                if (volumesResponse != null) {
                    Log.d("item change", volumesResponse.size() + "");
                    blogListModels.clear();
                    blogListModels.addAll(volumesResponse);
                    bloglistAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initial_list() {
        try {
            //audio book adapter
            blogListModels = new ArrayList<>();
            bloglistAdapter = new BloglistAdapter(blogListModels, context);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            binding.homepageRecycler.setLayoutManager(mLayoutManager);
            binding.homepageRecycler.setItemAnimator(new DefaultItemAnimator());
            binding.homepageRecycler.setAdapter(bloglistAdapter);
            bloglistAdapter.notifyDataSetChanged();
            //get_blog_list();
        } catch (Exception e) {
            Log.d("Error Line Number", Log.getStackTraceString(e));
        }
    }
    private void observeLogin() {
        mViewModel.getProgressbar().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(final Boolean progressObserve) {
                if (progressObserve) {
                    utility.showProgress(false, context.getResources().getString(R.string.loading_string));
                } else {
                    utility.hideProgress();
                }
            }
        });
    }

}