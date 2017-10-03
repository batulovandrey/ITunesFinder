package com.github.batulovandrey.itunesfinder.presenter;

import android.content.Intent;

import com.github.batulovandrey.itunesfinder.bean.TrackResponse;
import com.github.batulovandrey.itunesfinder.model.MainModel;
import com.github.batulovandrey.itunesfinder.view.MainView;

/**
 * @author Andrey Batulov on 02/10/2017
 */

public class MainPresenterImpl implements MainPresenter {

    private final MainView mMainView;
    private final MainModel mMainModel;

    public MainPresenterImpl(MainView mainView) {
        mMainView = mainView;
        mMainModel = new MainModel(this);
    }

    @Override
    public void showProgress() {
        mMainView.showProgress();
    }

    @Override
    public void hideProgress() {
        mMainView.hideProgress();
    }

    @Override
    public void showNoConnectionError() {
        mMainView.showNoConnectionError();
    }

    @Override
    public void showServiceUnavailableError() {
        mMainView.showServiceUnavailableError();
    }

    @Override
    public void showTracksFragment(TrackResponse trackResponse) {
        mMainView.showTracksFragment(trackResponse);
    }

    @Override
    public void onCreate(Intent intent) {
        mMainView.setToolbar();
        mMainView.setProgressColor();
        handleIntent(intent);
    }

    @Override
    public void handleIntent(Intent intent) {
        mMainModel.handleIntent(intent);
    }
}