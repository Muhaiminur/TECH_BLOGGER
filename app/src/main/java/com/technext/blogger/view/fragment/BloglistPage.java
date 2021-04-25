package com.technext.blogger.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.technext.blogger.adapter.BloglistAdapter;
import com.technext.blogger.dagger.MyApplication;
import com.technext.blogger.databinding.BloglistPageFragmentBinding;
import com.technext.blogger.library.Utility;
import com.technext.blogger.model.Blog;
import com.technext.blogger.view.activity.AddBlogPage;
import com.technext.blogger.viewmodel.BloglistPageViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class BloglistPage extends Fragment {

    List<Blog> blogListModels;
    BloglistAdapter bloglistAdapter;
    Context context;
    @Inject
    Utility utility;
    BloglistPageFragmentBinding binding;

    @Inject
    BloglistPageViewModel mViewModel;

    public static BloglistPage newInstance() {
        return new BloglistPage();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            try {
                binding = BloglistPageFragmentBinding.inflate(inflater, container, false);
                context = getActivity();
                //utility = new Utility(context);
                initial_list();
                binding.homepageAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(context, AddBlogPage.class));
                    }
                });
            } catch (Exception e) {
                Log.d("Error Line Number", Log.getStackTraceString(e));
            }
        }
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        ((MyApplication) getActivity().getApplicationContext()).viewModelComponent.injectblogviewmodel(this);
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //mViewModel = new ViewModelProvider(this).get(BloglistPageViewModel.class);
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
                utility.showToast("paisi");
                if (progressObserve) {
                    //utility.showProgress(false, context.getResources().getString(R.string.loading_string));
                } else {
                    //utility.hideProgress();
                }
            }
        });
    }

}