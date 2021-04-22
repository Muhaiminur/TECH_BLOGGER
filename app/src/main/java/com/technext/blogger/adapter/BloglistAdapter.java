package com.technext.blogger.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.technext.blogger.R;
import com.technext.blogger.databinding.RecyclerBlogBinding;
import com.technext.blogger.library.KeyWord;
import com.technext.blogger.library.Utility;
import com.technext.blogger.model.Blog;

import java.util.List;

public class BloglistAdapter extends RecyclerView.Adapter<BloglistAdapter.Todo_View_Holder> {
    Context context;
    List<Blog> list;
    Utility utility;

    public BloglistAdapter(List<Blog> to, Context c) {
        list = to;
        context = c;
        utility = new Utility(context);
    }

    public class Todo_View_Holder extends RecyclerView.ViewHolder {
        RecyclerBlogBinding binding;

        public Todo_View_Holder(RecyclerBlogBinding view) {
            super(view.getRoot());
            this.binding = view;
        }

        public void bind(Blog notificationModel) {
            binding.setModel(notificationModel);
            binding.executePendingBindings();
        }
    }

    @Override
    public BloglistAdapter.Todo_View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerBlogBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recycler_blog, parent, false);
        return new BloglistAdapter.Todo_View_Holder(binding);
    }

    @Override
    public void onBindViewHolder(final BloglistAdapter.Todo_View_Holder holder, int position) {
        final Blog bodyResponse = list.get(position);
        try {
            holder.bind(bodyResponse);
            holder.binding.blogImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        NavController navController = Navigation.findNavController(holder.binding.getRoot());
                        if (navController != null) {
                            Gson gson = new Gson();
                            Bundle bundle = new Bundle();
                            bundle.putString(KeyWord.BLOG_DETAILS, gson.toJson(bodyResponse));
                            navController.navigate(R.id.blogdetailspage_fragment, bundle);
                        }
                    } catch (Exception e) {
                        Log.d("Error Line Number", Log.getStackTraceString(e));
                    }
                }
            });
            Glide.with(context).load(bodyResponse.getCoverPhoto()).placeholder(R.drawable.ic_loading).error(R.drawable.ic_default).into(holder.binding.blogImage);
            holder.binding.blogTittle.setText(bodyResponse.getTitle());
            holder.binding.blogSubtittle.setText(context.getResources().getString(R.string.author_string) + ": " + bodyResponse.getAuthor().getName());
        } catch (Exception e) {
            Log.d("Error Line Number", Log.getStackTraceString(e));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}