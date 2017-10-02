package com.github.batulovandrey.itunesfinder;

import android.app.Application;

import com.github.batulovandrey.itunesfinder.dagger.AppModule;
import com.github.batulovandrey.itunesfinder.dagger.DaggerNetComponent;
import com.github.batulovandrey.itunesfinder.dagger.NetComponent;
import com.github.batulovandrey.itunesfinder.dagger.NetModule;

/**
 * @author Andrey Batulov on 02/10/2017
 */

public class ItunesApp extends Application {

    private static final String BASE_URL = "https://itunes.apple.com/";
    private static NetComponent sNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(BASE_URL))
                .build();
    }

    public static NetComponent getNetComponent() {
        return sNetComponent;
    }
}