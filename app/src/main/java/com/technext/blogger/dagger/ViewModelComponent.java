package com.technext.blogger.dagger;

import android.app.Application;
import android.content.Context;

import com.technext.blogger.library.Utility;
import com.technext.blogger.view.fragment.BloglistPage;

import dagger.Component;

@Component(modules = {ViewModelModule.class, ContextModule.class})
public interface ViewModelComponent {
    Application getApplication();


    Context context();

    Utility getuUtility();

    void injectblogviewmodel(BloglistPage bloglistPage);
}
