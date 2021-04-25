package com.technext.blogger.dagger;

import com.technext.blogger.network.Controller;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component
public interface FixedComponent {
    Controller getController();
}
