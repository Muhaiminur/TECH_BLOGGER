package com.technext.blogger.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.technext.blogger.R;
import com.technext.blogger.databinding.ActivityAddBlogPageBinding;
import com.technext.blogger.library.Utility;
import com.technext.blogger.model.Author;
import com.technext.blogger.model.Blog;
import com.technext.blogger.viewmodel.AddBlogPageViewModel;
import com.technext.blogger.library.KeyWord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddBlogPage extends AppCompatActivity {
    Context context;
    Utility utility;
    ActivityAddBlogPageBinding binding;
    List<String> catlist = new ArrayList<>();
    AddBlogPageViewModel viewModel;
    Blog editblog;
    Gson gson = new Gson();
    int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_blog_page);
        try {
            context = this;
            utility = new Utility(context);
            viewModel = new ViewModelProvider(this).get(AddBlogPageViewModel.class);
            viewModel.init();
            observeadd();
            author_work();
            add_blog_work();
            editWork();
        } catch (Exception e) {
            Log.d("Error Line Number", Log.getStackTraceString(e));
        }
    }

    private void editWork() {
        try {
            editblog = gson.fromJson(getIntent().getStringExtra(KeyWord.BLOG_EDIT), Blog.class);
            if (editblog != null) {
                binding.addblogTittle.setText(editblog.getTitle());
                binding.addblogDescription.setText(editblog.getDescription());
                for (String s : editblog.getCategories()) {
                    if (s.equalsIgnoreCase(context.getResources().getString(R.string.catbus_string))) {
                        binding.addblogCatbusiness.setTextColor(context.getResources().getColor(R.color.app_red1));
                        catlist.add(s);
                    } else if (s.equalsIgnoreCase(context.getResources().getString(R.string.catlife_string))) {
                        binding.addblogCatlife.setTextColor(context.getResources().getColor(R.color.app_red1));
                        catlist.add(s);
                    } else if (s.equalsIgnoreCase(context.getResources().getString(R.string.catenter_string))) {
                        binding.addblogCatenter.setTextColor(context.getResources().getColor(R.color.app_red1));
                        catlist.add(s);
                    } else if (s.equalsIgnoreCase(context.getResources().getString(R.string.catpro_string))) {
                        binding.addblogCatpro.setTextColor(context.getResources().getColor(R.color.app_red1));
                        catlist.add(s);
                    }
                }
                binding.addblogNew.setText(context.getResources().getString(R.string.edit_string));
            }
        } catch (Exception e) {
            Log.d("Error Line Number", Log.getStackTraceString(e));
        }
    }

    private void add_blog_work() {
        try {
            binding.addblogNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        String tittle = binding.addblogTittle.getEditableText().toString();
                        String description = binding.addblogDescription.getEditableText().toString();
                        if (!TextUtils.isEmpty(tittle)) {
                            if (!TextUtils.isEmpty(description)) {
                                if (catlist.size() != 0) {
                                    if (editblog == null) {
                                        Blog blog = new Blog(count, tittle, description, "https://i.picsum.photos/id/579/200/300.jpg?hmac=9MD8EV4Jl9EqKLkTj5kyNdBUKQWyHk2m4pE4UCBGc8Q", catlist, new Author("John Doe", "https://i.pravatar.cc/250", "Content Writer"));
                                        utility.logger("blog add" + blog.toString());
                                        viewModel.add_into_blog(blog);
                                    } else {
                                        Blog blog = new Blog(editblog.getId(), tittle, description, editblog.getCoverPhoto(), catlist, editblog.getAuthor());
                                        utility.logger("blog edit" + blog.toString());
                                        viewModel.update_into_blog(blog);
                                    }
                                } else {
                                    utility.showToast(context.getResources().getString(R.string.cathint_string));
                                }
                            } else {
                                binding.addblogDescription.setError(context.getResources().getString(R.string.subtittlehint_string));
                                binding.addblogDescription.requestFocusFromTouch();
                            }
                        } else {
                            binding.addblogTittle.setError(context.getResources().getString(R.string.tittlehint_string));
                            binding.addblogTittle.requestFocusFromTouch();
                        }
                    } catch (Exception e) {
                        Log.d("Error Line Number", Log.getStackTraceString(e));
                    }
                }
            });
        } catch (Exception e) {
            Log.d("Error Line Number", Log.getStackTraceString(e));
        }
    }

    private void author_work() {
        try {
            binding.addblogAuthorname.setText("John Doe");
            binding.addblogAuthorpro.setText("Content Writer");
            Glide.with(context).load("https://i.pravatar.cc/250").placeholder(R.drawable.ic_loading).error(R.drawable.ic_default).into(binding.addblogAuthorimage);
            cat_work();
        } catch (Exception e) {
            Log.d("Error Line Number", Log.getStackTraceString(e));
        }
    }

    private void cat_work() {
        try {
            binding.addblogCatenter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String s = binding.addblogCatenter.getText().toString();
                    if (!catlist.contains(s)) {
                        binding.addblogCatenter.setTextColor(context.getResources().getColor(R.color.app_red1));
                        catlist.add(s);
                        utility.logger("paisi" + catlist.toString());
                    } else {
                        binding.addblogCatenter.setTextColor(context.getResources().getColor(R.color.app_dark));
                        catlist.remove(s);
                        utility.logger("paisi1" + catlist.toString());
                    }

                }
            });
            binding.addblogCatbusiness.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String s = binding.addblogCatbusiness.getText().toString();
                    if (!catlist.contains(s)) {
                        binding.addblogCatbusiness.setTextColor(context.getResources().getColor(R.color.app_red1));
                        catlist.add(s);
                        utility.logger("paisi" + catlist.toString());
                    } else {
                        binding.addblogCatbusiness.setTextColor(context.getResources().getColor(R.color.app_dark));
                        catlist.remove(s);
                        utility.logger("paisi1" + catlist.toString());
                    }
                }
            });
            binding.addblogCatpro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String s = binding.addblogCatpro.getText().toString();
                    if (!catlist.contains(s)) {
                        binding.addblogCatpro.setTextColor(context.getResources().getColor(R.color.app_red1));
                        catlist.add(s);
                        utility.logger("paisi" + catlist.toString());
                    } else {
                        binding.addblogCatpro.setTextColor(context.getResources().getColor(R.color.app_dark));
                        catlist.remove(s);
                        utility.logger("paisi1" + catlist.toString());
                    }
                }
            });
            binding.addblogCatlife.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String s = binding.addblogCatlife.getText().toString();
                    if (!catlist.contains(s)) {
                        binding.addblogCatlife.setTextColor(context.getResources().getColor(R.color.app_red1));
                        catlist.add(s);
                        utility.logger("paisi" + catlist.toString());
                    } else {
                        binding.addblogCatlife.setTextColor(context.getResources().getColor(R.color.app_dark));
                        catlist.remove(s);
                        utility.logger("paisi1" + catlist.toString());
                    }
                }
            });
        } catch (Exception e) {
            Log.d("Error Line Number", Log.getStackTraceString(e));
        }
    }

    private void observeadd() {
        viewModel.getProgressbar().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(final Boolean progressObserve) {
                if (progressObserve) {
                    utility.showProgress(false, context.getResources().getString(R.string.loading_string));
                } else {
                    utility.hideProgress();
                    if (editblog != null) {
                        startActivity(new Intent(context, HomePage.class));
                    } else {
                        finish();
                    }
                }
            }
        });

        viewModel.getVolumesResponseLiveData().observe(this, new Observer<List<Blog>>() {
            @Override
            public void onChanged(List<Blog> volumesResponse) {
                if (volumesResponse != null) {
                    count = volumesResponse.size()+1;
                }
            }
        });
    }

}