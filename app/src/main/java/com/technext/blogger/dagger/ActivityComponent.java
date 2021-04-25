package com.technext.blogger.dagger;

import android.content.Context;

import com.technext.blogger.adapter.BloglistAdapter;
import com.technext.blogger.library.Utility;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {ContextModule.class})
@Singleton
public interface ActivityComponent {
    Context context();

    Utility getuUtility();

    //void injectblog(BloglistPage bloglistPage);

    void injectadapter(BloglistAdapter adapter);
}