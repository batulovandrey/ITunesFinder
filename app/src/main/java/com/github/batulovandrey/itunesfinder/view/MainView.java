package com.github.batulovandrey.itunesfinder.view;

import com.github.batulovandrey.itunesfinder.bean.TrackResponse;

/**
 * @author Andrey Batulov on 02/10/2017
 */

public interface MainView {

    void showProgress();

    void hideProgress();

    void setToolbar();

    void showNoConnectionError();

    void showServiceUnavailableError();

    void showTracksFragment(TrackResponse trackResponse);

    void setProgressColor();
}