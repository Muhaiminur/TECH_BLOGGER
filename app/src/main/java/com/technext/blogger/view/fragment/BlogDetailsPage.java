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
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.technext.blogger.R;
import com.technext.blogger.databinding.BlogDetailsPageFragmentBinding;
import com.technext.blogger.library.KeyWord;
import com.technext.blogger.library.Utility;
import com.technext.blogger.model.Blog;
import com.technext.blogger.network.ApiService;
import com.technext.blogger.network.Controller;
import com.technext.blogger.view.activity.AddBlogPage;
import com.technext.blogger.viewmodel.BlogDetailsPageViewModel;

import javax.inject.Inject;

public class BlogDetailsPage extends Fragment {
    ApiService apiInterface = Controller.getBaseClient().create(ApiService.class);
    Gson gson = new Gson();
    Context context;
    Utility utility;
    BlogDetailsPageFragmentBinding binding;
    Blog blog;
    private BlogDetailsPageViewModel mViewModel;
    public static BlogDetailsPage newInstance() {
        return new BlogDetailsPage();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            try {
                binding = BlogDetailsPageFragmentBinding.inflate(inflater, container, false);
                context = getActivity();
                utility = new Utility(context);
                blog = gson.fromJson(getArguments().getString(KeyWord.BLOG_DETAILS), Blog.class);
                if (blog != null) {
                    binding.blogdetailsTittle.setText(blog.getTitle());
                    binding.blogdetailsSubtittle.setText(blog.getDescription());
                    binding.blogdetailsAuthorname.setText(blog.getAuthor().getName());
                    binding.blogdetailsAuthorpro.setText(blog.getAuthor().getProfession());
                    binding.blogdetailsCategory.setText(blog.getCategories().toString());
                    Glide.with(context).load(blog.getCoverPhoto()).placeholder(R.drawable.ic_loading).error(R.drawable.ic_default).into(binding.blogdetailsImage);
                    Glide.with(context).load(blog.getAuthor().getAvatar()).placeholder(R.drawable.ic_loading).error(R.drawable.ic_default).into(binding.blogdetailsAuthorimage);

                } else {
                    utility.showToast(context.getResources().getString(R.string.something_went_wrong));
                }
                binding.blogdetailsEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (blog != null) {
                            startActivity(new Intent(context, AddBlogPage.class).putExtra(KeyWord.BLOG_EDIT, gson.toJson(blog)));
                        }
                    }
                });
            } catch (Exception e) {
                Log.d("Error Line Number", Log.getStackTraceString(e));
            }
        }
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BlogDetailsPageViewModel.class);
        // TODO: Use the ViewModel
    }

}