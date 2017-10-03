package com.github.batulovandrey.itunesfinder.presenter;

/**
 * @author Andrey Batulov on 03/10/2017
 */

public interface PlayerPresenter {

    void onCreate();

    void onViewCreated();

    void onDestroyView();

    void onPlayButtonClick(String audioUrl);

    void onPauseButtonClick();

    void setSeekBarMax(int max);

    void setSeekBarProgress(int progress);

    void setPlayButtonEnabled(boolean enabled);

    void setPauseButtonEnabled(boolean enabled);
}