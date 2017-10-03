package com.github.batulovandrey.itunesfinder.dagger;

import com.github.batulovandrey.itunesfinder.model.MainModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Andrey Batulov on 02/10/2017
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {

    void inject(MainModel model);
}