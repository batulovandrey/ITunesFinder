package com.github.batulovandrey.itunesfinder.presenter;

import android.content.Intent;

import com.github.batulovandrey.itunesfinder.bean.TrackResponse;

/**
 * @author Andrey Batulov on 02/10/2017
 */

public interface MainPresenter {

    void showProgress();

    void hideProgress();

    void showNoConnectionError();

    void showServiceUnavailableError();

    void showTracksFragment(TrackResponse trackResponse);

    void onCreate(Intent intent);

    void handleIntent(Intent intent);
}