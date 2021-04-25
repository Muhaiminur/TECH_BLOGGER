package com.technext.blogger.dagger;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelModule {
    private final Application application;

    public ViewModelModule(Application a) {
        this.application = a;
    }

    @Provides //scope is not necessary for parameters stored within the module
    public Application context() {
        return application;
    }
}
