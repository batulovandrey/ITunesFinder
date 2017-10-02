package com.github.batulovandrey.itunesfinder.dagger;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Andrey Batulov on 02/10/2017
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
}