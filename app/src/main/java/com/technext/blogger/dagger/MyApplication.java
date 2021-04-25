package com.technext.blogger.dagger;

import android.app.Application;

public class MyApplication extends Application {
    public ViewModelComponent viewModelComponent = DaggerViewModelComponent.builder().viewModelModule(new ViewModelModule(this)).contextModule(new ContextModule(this)).build();
}
