package com.github.batulovandrey.itunesfinder.model;

import android.app.SearchManager;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.github.batulovandrey.itunesfinder.ItunesApp;
import com.github.batulovandrey.itunesfinder.bean.TrackResponse;
import com.github.batulovandrey.itunesfinder.net.TrackService;
import com.github.batulovandrey.itunesfinder.presenter.MainPresenter;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Andrey Batulov on 02/10/2017
 */

public class MainModel {

    @Inject
    ConnectivityManager mConnectivityManager;

    @Inject
    TrackService mService;

    private final MainPresenter mMainPresenter;

    public MainModel(MainPresenter mainPresenter) {
        ItunesApp.getNetComponent().inject(this);
        mMainPresenter = mainPresenter;
    }

    public void onQueryTextSubmit(String query) {
        if (hasConnection()) {
            mMainPresenter.showProgress();
            getData(query);
        } else {
            mMainPresenter.showNoConnectionError();
        }
    }

    public void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            onQueryTextSubmit(query);
        }
    }

    private boolean hasConnection() {
        boolean connected = false;
        if (mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        return connected;
    }

    private void getData(String query) {
        Call<TrackResponse> call = mService.getTracks(query);
        call.enqueue(new Callback<TrackResponse>() {
            @Override
            public void onResponse(@NonNull Call<TrackResponse> call,
                                   @NonNull Response<TrackResponse> response) {
                TrackResponse trackResponse = response.body();
                if (trackResponse != null) {
                    mMainPresenter.showTracksFragment(trackResponse);
                    mMainPresenter.hideProgress();
                }
            }

            @Override
            public void onFailure(@NonNull Call<TrackResponse> call, @NonNull Throwable t) {
                mMainPresenter.showServiceUnavailableError();
            }
        });
    }
}